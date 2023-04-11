package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    // The first String is the email of customer


    public Map<String, Customer> customers = new HashMap<String, Customer>();

    // we use singleton pattern, lazy initialization
    private static CustomerService instance;
    private CustomerService(){};
    public static CustomerService getInstance(){
        if(instance == null){
            instance = new CustomerService();
        }
        return instance;
    }
    public void addCustomer(String email, String firstName, String lastName){
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }



}
