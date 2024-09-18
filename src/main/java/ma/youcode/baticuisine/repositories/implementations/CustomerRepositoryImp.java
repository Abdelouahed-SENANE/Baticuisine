package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.Custumer;
import ma.youcode.baticuisine.repositories.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepositoryImp implements CustomerRepository {

    private final Database db;

    public CustomerRepositoryImp() {
        db = Database.getInstance();
    }


    @Override
    public void save(Custumer custumer) {
        String sql = "INSERT INTO customers (customer_name, address , phone_number, is_professional) VALUES (?, ? , ? , ?)";

        try (Connection cn = db.getConnection(); PreparedStatement pstmt = cn.prepareStatement(sql)) {

            pstmt.setString(1, custumer.getCustomerName());
            pstmt.setString(2, custumer.getAddress());
            pstmt.setString(3, custumer.getPhone());
            pstmt.setBoolean(4, custumer.getProfessional());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
