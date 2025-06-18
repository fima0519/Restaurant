
package restaurant;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class CustomerManager {
   
    public List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("Customer Added Successfully.");
    }

    public int getTotalCustomers() {
        return customerList.size();
    }
  
    public Customer findCustomerById(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        System.out.println("Customer Not Found.");
        return null;
    }

    public boolean updateCustomerContact(int id, String newContact) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customer.setContact(newContact);
            System.out.println("Updated Successfully.");
            return true;
        }
        return false;
    }

    public boolean removeCustomer(int id) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customerList.remove(customer);
            System.out.println("Customer Removed Successfully.");
            return true;
        }
        return false;
    }
}