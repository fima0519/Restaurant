package restaurant;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderManager {

    private ArrayList<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    void addOrder(Order order, Customer customer) {
        orders.add(order);
        customer.placeOrder(order);
    }

    public synchronized void placeOrder(Inventory inventory, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Food, Integer> foods = new HashMap<>();
        String choice;
        if (customer != null) {
            do {
                System.out.print("Enter food name: ");
                String foodName = scanner.nextLine();

                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                Food food = inventory.findFoodByName(foodName);

                if (food != null) {
                    foods.put(food, quantity);
                } else {
                    System.out.println("Food item not found!");
                }

                System.out.print("Do you want to add more items? (yes/no): ");
                choice = scanner.nextLine();
            } while (choice.equalsIgnoreCase("yes"));

            for (HashMap.Entry<Food, Integer> entry : foods.entrySet()) {
                Food food = entry.getKey();
                int quantity = entry.getValue();

                if (food.getQuantity() >= quantity) {
                    food.setQuantity(food.getQuantity() - quantity);
                } else {
                    System.out.println("Not enough stock for " + food.getName());
                    System.out.println("Stock for " + food.getName() + " is " + food.getQuantity());
                    return;
                }
            }

            Order order = new Order(customer.getId(), foods);
            orders.add(order);
            System.out.println("Order placed successfully!");
            order.displayInfo();
            customer.placeOrder(order);

            generateReceipt(order.getOrderId());
        } else {
            System.out.println("Enter a valid Customer or Create one.");
        }

    }

    public void generateReceipt(int orderId) {
        try {
            Order order = findOrderById(orderId);
            try ( FileWriter writer = new FileWriter("Receipt_Order_" + order.getOrderId() + ".txt")) {
                writer.write("---------- Order Receipt ----------\n");
                writer.write("Order ID: " + order.getOrderId() + "\n");
                writer.write("Customer ID: " + order.getCustomerId() + "\n");
                writer.write("Order Date: " + order.getOrderDate() + "\n");
                writer.write("Foods:\n");
                double totalPrice = 0;

                for (Map.Entry<Food, Integer> entry : order.getOrderedItems().entrySet()) {
                    Food food = entry.getKey();
                    int quantity = entry.getValue();
                    double Total = food.getPrice() * quantity;
                    writer.write(food.getName() + " x " + quantity + " = " + Total + "\n");
                    totalPrice += Total;
                }

                writer.write("-----------------------------------\n");
                writer.write("Total Price: " + totalPrice + "\n");
                writer.write("Thank you for your order!\n");
            }
            System.out.println("Receipt generated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating the receipt: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Order not found: " + e.getMessage());
        }
    }

    public boolean removeOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                boolean result = orders.remove(order);
                if (result) {
                    System.out.println("Order ID " + order.getOrderId() + " has got removed.");
                } else {
                    System.out.println("Failed to remove order id " + orderId + ".");
                }
                return result;
            }
        }
        System.out.println("Not Founded Order ID " + orderId + ".");
        return false;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> ordersByCustomer = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerId() == customerId) {
                ordersByCustomer.add(order);
            }
        }
        return ordersByCustomer;
    }

    public int getTotalOrders() {
        return orders.size();
    }

    public void clearAllOrders() {
        orders.clear();
    }

    public Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
    }

    public boolean updateOrderStatus(int orderId, OrderStatus newStatus) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    public List<Order> getOrdersWithStatus(OrderStatus status) {
        List<Order> ordersWithStatus = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == status) {
                ordersWithStatus.add(order);
            }
        }
        return ordersWithStatus;
    }

    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> ordersWithinDateRange = new ArrayList<>();
        for (Order order : orders) {
            LocalDate orderDate = order.getOrderDate();
            if (orderDate != null && !orderDate.isBefore(startDate) && !orderDate.isAfter(endDate)) {
                ordersWithinDateRange.add(order);
            }
        }
        return ordersWithinDateRange;
    }

}
