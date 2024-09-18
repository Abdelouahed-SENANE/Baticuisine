package ma.youcode.baticuisine.repositories;

import ma.youcode.baticuisine.entities.Customer;

public interface CustomerRepository {

    void save(Customer customer);
    Customer findByName(String customerName);
}
