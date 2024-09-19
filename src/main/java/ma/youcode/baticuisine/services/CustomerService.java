package ma.youcode.baticuisine.services;

import ma.youcode.baticuisine.entities.Customer;

import java.util.Optional;

public interface CustomerService {

    void addCustomer(Customer customer);
    Optional<Customer> getCustomerByName(String customerName);
}
