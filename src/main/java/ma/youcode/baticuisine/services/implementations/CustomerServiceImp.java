package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.entities.Custumer;
import ma.youcode.baticuisine.repositories.CustomerRepository;
import ma.youcode.baticuisine.repositories.implementations.CustomerRepositoryImp;
import ma.youcode.baticuisine.services.CustomerService;

public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp() {
        this.customerRepository = new CustomerRepositoryImp();
    }

    @Override
    public void addCustumer(Custumer custumer) {
        this.customerRepository.save(custumer);
    }
}
