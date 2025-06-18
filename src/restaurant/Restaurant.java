package restaurant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Restaurant {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addFoodsFromFile("Food.txt");
        
        System.out.println("\nInventory List:");
        for (Food food : inventory.getFoods()) {
            System.out.println(food.toString());
        }
        Menu.showFoodsByMenuTime(inventory);
        OrderManager orderManager = new OrderManager();
        CustomerManager customerManager = new CustomerManager();

        //Customer customer1 = new Customer("A", "024248524");
        //Customer customer2 = new Customer("B", "042424332");
        //customerManager.addCustomer(customer1);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\nRestaurant Management System");
            System.out.println("0. Add a Customer");
            System.out.println("1. Place an Order");
            System.out.println("2. View Orders by Customer ID");
            System.out.println("3. View All Orders");
            System.out.println("4. Update Order Status");
            System.out.println("5. Generate Receipt for an Order");
            System.out.println("6. View Orders by Status");
            System.out.println("7. View Orders by Date Range");
            System.out.println("8. Update Inventory");
            System.out.println("9. Remove an Order");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Enter customer information:");
                    System.out.print("ID : ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name : ");
                    String customerName = scanner.nextLine();
                    System.out.print("Contact : ");
                    String customerContact = scanner.nextLine();
                    Customer customer = new Customer(customerName, customerContact);
                    customerManager.addCustomer(customer);
                    break;

                case 1:
                    System.out.println("Enter Custmer Id:");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Customer selectedCustomer = customerManager.findCustomerById(searchId);
                    orderManager.placeOrder(inventory, selectedCustomer);
                    break;

                case 2:
                    System.out.println("Enter Customer ID : ");
                    int custId = scanner.nextInt();
                    scanner.nextLine();

                    List<Order> customerOrders = orderManager.getOrdersByCustomerId(custId);
                    if (customerOrders.isEmpty()) {
                        System.out.println("No orders found for this customer.");
                    } else {
                        System.out.println("Orders for Customer ID: " + custId);
                        for (Order order : customerOrders) {
                            order.displayInfo();
                        }
                    }
                    break;

                case 3:
                    List<Order> allOrders = orderManager.getOrders();
                    if (allOrders.isEmpty()) {
                        System.out.println("No orders have been placed yet.");
                    } else {
                        System.out.println("All Orders:");
                        for (Order order : allOrders) {
                            order.displayInfo();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter Order ID to update: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter new status (PENDING, DELIVERED, CANCELLED): ");
                    String status = scanner.nextLine().toUpperCase();

                    try {
                        OrderStatus newStatus = OrderStatus.valueOf(status);
                        boolean updated = orderManager.updateOrderStatus(orderId, newStatus);
                        if (updated) {
                            System.out.println("Order status updated successfully.");
                        } else {
                            System.out.println("Order not found.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status entered.");
                    }
                    break;

                case 5:
                    System.out.println("Enter Order ID to generate receipt: ");
                    int receiptOrderId = scanner.nextInt();
                    scanner.nextLine();

                    orderManager.generateReceipt(receiptOrderId);
                    break;

                case 6:
                    System.out.println("Enter status to search orders (PENDING, DELIVERED, CANCELLED): ");
                    String filterStatus = scanner.nextLine().toUpperCase();

                    try {
                        OrderStatus orderStatus = OrderStatus.valueOf(filterStatus);
                        List<Order> filteredOrders = orderManager.getOrdersWithStatus(orderStatus);

                        if (filteredOrders.isEmpty()) {
                            System.out.println("No orders found with status: " + filterStatus);
                        } else {
                            System.out.println("Orders with status: " + filterStatus);
                            for (Order order : filteredOrders) {
                                order.displayInfo();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status entered.");
                    }
                    break;

                case 7:
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    List<Order> dateRangeOrders = orderManager.getOrdersByDateRange(startDate, endDate);
                    if (dateRangeOrders.isEmpty()) {
                        System.out.println("No orders found within the given date range.");
                    } else {
                        for (Order order : dateRangeOrders) {
                            order.displayInfo();
                        }
                    }
                    break;

                case 8:
                    System.out.println("\n--- Inventory Menu ---");
                    System.out.println("1. Display all foods");
                    System.out.println("2. Find food by ID");
                    System.out.println("3. Find food by name");
                    System.out.println("4. Remove food by ID");
                    System.out.println("5. Remove food by name");
                    System.out.println("6. Check food availability");
                    System.out.println("7. Update food quantity");
                    System.out.println("8. Get total inventory value");
                    System.out.println("9. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int inventoryChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (inventoryChoice) {
                        case 1:
                            System.out.println("\nAll foods in the inventory:");
                            inventory.displayFoods(inventory.getFoods());
                            break;

                        case 2:
                            System.out.print("\nEnter food ID to find: ");
                            int foodIdToFind = scanner.nextInt();
                            Food foundById = inventory.findFoodById(foodIdToFind);
                            System.out.println(foundById != null ? foundById : "Food not found.");
                            break;

                        case 3:
                            System.out.print("\nEnter food name to find: ");
                            String foodNameToFind = scanner.nextLine();
                            Food foundByName = inventory.findFoodByName(foodNameToFind);
                            System.out.println(foundByName != null ? foundByName : "Food not found.");
                            break;

                        case 4:
                            System.out.print("\nEnter food ID to remove: ");
                            int foodIdToRemove = scanner.nextInt();
                            boolean removedById = inventory.removeFoodById(foodIdToRemove);
                            System.out.println(removedById ? "Food removed successfully." : "Food not found.");
                            break;

                        case 5:
                            System.out.print("\nEnter food name to remove: ");
                            String foodNameToRemove = scanner.nextLine();
                            boolean removedByName = inventory.removeFoodByName(foodNameToRemove);
                            System.out.println(removedByName ? "Food removed successfully." : "Food not found.");
                            break;

                        case 6:
                            System.out.print("\nEnter food ID to check availability: ");
                            int foodIdToCheck = scanner.nextInt();
                            System.out.print("Enter quantity to check: ");
                            int quantityToCheck = scanner.nextInt();
                            boolean isAvailable = inventory.checkFoodAvailability(foodIdToCheck, quantityToCheck);
                            System.out.println(isAvailable ? "Food is available." : "Food is not available.");
                            break;

                        case 7:
                            System.out.print("\nEnter food ID to update quantity: ");
                            int foodIdToUpdate = scanner.nextInt();
                            System.out.print("Enter new quantity: ");
                            int newQuantity = scanner.nextInt();
                            boolean updated = inventory.updateFoodQuantity(foodIdToUpdate, newQuantity);
                            System.out.println(updated ? "Quantity updated successfully." : "Failed to update quantity.");
                            break;

                        case 8:
                            System.out.println("\nTotal inventory value:");
                            double totalValue = inventory.getTotalInventoryValue();
                            System.out.printf("Total value: $%.2f%n", totalValue);
                            break;

                        case 9:
                            System.out.println("Returning to the Main Menu...");
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 9:
                    System.out.print("Enter Order ID to remove: ");
                    int removeOrderId = scanner.nextInt();
                    boolean removed = orderManager.removeOrder(removeOrderId);
                    if (removed) {
                        System.out.println("Order removed successfully.");
                    } else {
                        System.out.println("Order ID not found.");
                    }
                    break;

                case 10:
                    // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
