package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.repositories.CustomerRepository;
import ma.youcode.baticuisine.repositories.implementations.CustomerRepositoryImp;
import ma.youcode.baticuisine.services.CustomerService;
import ma.youcode.baticuisine.utils.Cache;

import java.util.List;
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
        Optional<Customer> cachedCustomer = Cache.getCustomer(name);
        if (cachedCustomer.isPresent()) {
            return Cache.getCustomer(name);
        }
        Customer customer = this.customerRepository.findByName(name);
        if (customer != null) {
            Cache.setCustomer(name, customer);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }
}
