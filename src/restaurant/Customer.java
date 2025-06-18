package restaurant;

import java.util.ArrayList;
import java.util.List;



public class Customer extends Person {
    private static int nextId = 1;
    private int Id;
    private List<Order> orderList; 

    public Customer(String name, String contact) {
        super(name, contact);
        this.Id = nextId++;
        this.orderList = new ArrayList<>();
    }

    @Override
    public void displayInfo() {
       System.out.println("Customer ID: " + getId() + ", Name: " + getName() + ", Contact: " + getContact());
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    

    public void placeOrder(Order order) {
        orderList.add(order);
    }

    public List<Order> getOrders() {
        return orderList;
    }
    
    public void viewOrderHistory() {
        System.out.println("Order History for Customer: " + this.getName());
        for (Order order : orderList) {
            order.displayInfo();
        }
    }
}


