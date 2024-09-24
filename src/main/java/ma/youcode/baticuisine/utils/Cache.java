package ma.youcode.baticuisine.utils;

import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.entities.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Cache {
    private final static Map<String, Customer> customerCache = new HashMap<>();
    private  static Optional<Customer> authUser = Optional.empty();
    public static Optional<Customer> getCustomer(String name) {
        return Optional.ofNullable(customerCache.get(name));
    }

    public static void setCustomer(String name, Customer customer) {
        customerCache.put(name, customer);
    }

    public static Optional<Customer> getAuthUser() {
        return authUser;
    }

    public static void setAuthUser(Customer customer) {
        authUser = Optional.ofNullable(customer);
    }

}
