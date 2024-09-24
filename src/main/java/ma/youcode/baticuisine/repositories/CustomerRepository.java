package ma.youcode.baticuisine.repositories;

import ma.youcode.baticuisine.entities.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer save(Customer customer);
    Customer findByName(String customerName);
    void update(Customer customer);
    void delete(Customer customer);
    List<Customer> findAll();
}
