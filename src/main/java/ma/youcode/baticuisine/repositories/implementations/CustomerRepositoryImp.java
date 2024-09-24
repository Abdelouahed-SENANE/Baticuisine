package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.repositories.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerRepositoryImp implements CustomerRepository {

    private final Connection connection;
    public CustomerRepositoryImp() {
        connection = Database.getInstance().getConnection();
    }


    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (customer_name, address , phone_number, is_professional) VALUES (?, ? , ? , ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS )) {

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getPhone());
            pstmt.setBoolean(4, customer.getProfessional());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                customer.setCustomerId(rs.getObject("customer_id" , UUID.class));
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findByName(String customerName) {
        Customer customer = null;
        String query = "SELECT * FROM customers WHERE customer_name = ?";

        try ( PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getObject("customer_id" , UUID.class));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone_number"));
                customer.setProfessional(rs.getBoolean("is_professional"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        String SQL = "UPDATE customers SET customer_name = ? , address = ? , phone_number = ? , is_professional = ? WHERE customer_id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getAddress());
            pstmt.setString(3, customer.getPhone());
            pstmt.setBoolean(4, customer.getProfessional());
            pstmt.setObject(5, customer.getCustomerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Customer customer) {
        String SQL = "DELETE FROM customers WHERE customer_id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setObject(1, customer.getCustomerId());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        String SQL = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.executeQuery();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getObject("customer_id" , UUID.class));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone_number"));
                customer.setProfessional(rs.getBoolean("is_professional"));
                customers.add(customer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }
}
