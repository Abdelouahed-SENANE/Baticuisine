package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.Estimate;
import ma.youcode.baticuisine.repositories.EstimateRepository;

import java.sql.*;
import java.util.UUID;

public class EstimateRepositoryImp implements EstimateRepository {
    private final Connection connection;
    public EstimateRepositoryImp() {
        connection = Database.getInstance().getConnection();
    }
    @Override
    public void save(Estimate estimate) {
        String sql = "INSERT INTO estimates (issue_at, validate_at , is_accepted , project_id) VALUES (?, ? , ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(estimate.getIssueAt()));
            pstmt.setDate(2, Date.valueOf(estimate.getValidateAt()));
            pstmt.setBoolean(3, estimate.getAccepted());
            pstmt.setObject(4 , estimate.getProject().getProjectId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
