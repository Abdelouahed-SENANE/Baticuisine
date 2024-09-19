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
    public void addCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }
    public Optional<Customer> getCustomerByName(String name) {
        Customer customer = this.customerRepository.findByName(name);
        return Optional.ofNullable(customer);
    }
}
