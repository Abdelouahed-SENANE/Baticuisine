package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.repositories.CustomerRepository;
import ma.youcode.baticuisine.repositories.implementations.CustomerRepositoryImp;
import ma.youcode.baticuisine.services.CustomerService;

import java.util.Optional;

public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp() {
        this.customerRepository = new CustomerRepositoryImp();
    }

    @Override
    public Optional<Customer> addCustomer(Customer customer) {

        Customer addedCustomer = this.customerRepository.save(customer);
        return Optional.ofNullable(addedCustomer);

    }
    public Optional<Customer> getCustomerByName(String name) {
        Customer customer = this.customerRepository.findByName(name);
        return Optional.ofNullable(customer);
    }
}
