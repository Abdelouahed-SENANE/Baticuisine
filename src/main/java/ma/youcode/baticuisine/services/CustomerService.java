package ma.youcode.baticuisine.services;

import ma.youcode.baticuisine.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> addCustomer(Customer customer);
    Optional<Customer> getCustomerByName(String customerName);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    List<Customer> getAllCustomers();
}
