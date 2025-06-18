package restaurant;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

enum OrderStatus {
    PENDING,
    DELIVERED,
    CANCELLED
}

public class Order {

    private static int nextOrderId = 1;
    private int orderId;
    private int customerId;
    private LocalDate orderDate;
    private OrderStatus status;
    private HashMap<Food, Integer> orderedItems; // food and quantity in a list

    // Constructor
    public Order(int customerId, HashMap<Food, Integer> orderedItems) {
        if (orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Ordered items cannot be null or empty.");
        }
        this.orderId = nextOrderId++; 
        this.customerId = customerId;
        this.orderDate = LocalDate.now();
        //this.paymentMethod = paymentMethod;
        this.status = OrderStatus.PENDING;
        this.orderedItems = new HashMap<>(orderedItems);
    }


    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public HashMap<Food, Integer> getOrderedItems() {
        return new HashMap<>(orderedItems);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderedItems(HashMap<Food, Integer> orderedItems) {
        this.orderedItems = new HashMap<>(orderedItems);
    }

  
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Food, Integer> entry : orderedItems.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += food.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void displayInfo() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Order Date: " + orderDate);
        //System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Order Status: " + status);
        System.out.println("Ordered Items:");
        double totalPrice = 0.0;
        for (Map.Entry<Food, Integer> entry : orderedItems.entrySet()) {
                    Food food = entry.getKey();
                    int quantity = entry.getValue();
                    double Total = food.getPrice() * quantity;
                    System.out.println(food.getName() + " x " + quantity + " = " + Total + "\n");
                    totalPrice += Total;
                }

                System.out.println("-----------------------------------\n");
                System.out.println("Total Price: " + totalPrice + "\n");
    }
}
