package restaurant;

import java.util.*;

public class TestCustomerEmployee {
    // Testing Customer, Employee, Customer Manager, Employee Manager.
    public static void main(String[] args) {
    EmployeeManager employeeManager = new EmployeeManager();
    CustomerManager customerManager = new CustomerManager();
    OrderManager orderManager = new OrderManager();
    Menu dessertMenu = new Menu("Dessert");

    Inventory inventory = new Inventory();
    Food cake = new Food("Cake", 80.00, 5,"dessert");
    Food iceCream = new Food("Ice Cream", 60.00, 40,"dessert");
    inventory.addFood(iceCream);
    inventory.addFood(cake);

    Employee emp1 = new Employee("A", "A@gmail.com", "Manager", 50000.00);
    Employee emp2 = new Employee("B", "b@gmail.com", "Chef", 40000.00);
    employeeManager.addEmployee(emp1);
    employeeManager.addEmployee(emp2);

    System.out.println("\nAll Employees:");
    for (Employee emp : employeeManager.getEmployees()) {
        emp.displayInfo();
    }

    System.out.println("\nEmployees with the Highest Salary:");
    List<Employee> highestSalaryEmployees = employeeManager.getEmployeesWithHighestSalary();
    for (Employee emp : highestSalaryEmployees) {
        emp.displayInfo();
    }


    Customer cust1 = new Customer("A", "12345");
    Customer cust2 = new Customer("B", "4324235");
    customerManager.addCustomer(cust1);
    customerManager.addCustomer(cust2);

    System.out.println("\nAll Customers:");
    for (Customer customer : customerManager.customerList) {
        customer.displayInfo();
    }

    
    System.out.println("\nPlace order by customer:");
    dessertMenu.showMenu();
    HashMap<Food, Integer> orderedItems = new HashMap<>();
    orderedItems.put(cake, 2); 
    orderedItems.put(iceCream, 3); 
    Order order = new Order(1, orderedItems); 
    orderManager.addOrder(order, cust1);


    int customerId = 1; 
    Customer customer = customerManager.findCustomerById(customerId);
    if (customer != null) {
        System.out.println("\nOrder Details for Customer ID " + customerId + ":");
        customer.viewOrderHistory();
    } else {
        System.out.println("Customer not found!");
    }

   
    int Id = 2; 
    String newContact = "1122334455"; 
    boolean contactUpdated = customerManager.updateCustomerContact(Id, newContact);
    if (contactUpdated) {
        System.out.println("Contact updated successfully!");
        Customer updatedCustomer = customerManager.findCustomerById(Id);
        if (updatedCustomer != null) {
            updatedCustomer.displayInfo();
        }
    } else {
        System.out.println("Customer not found!");
    }
}
}
