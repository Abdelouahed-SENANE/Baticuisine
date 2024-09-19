package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.enums.ProjectStatus;
import ma.youcode.baticuisine.repositories.ProjectRepository;

import java.sql.*;
import java.util.UUID;

public class ProjectRepositoryImp implements ProjectRepository {
    private final Connection connection;
    public ProjectRepositoryImp() {
        connection = Database.getInstance().getConnection();
    }

    @Override
    public UUID save(Project project) {
        UUID uuid = null;
        String SQL = "INSERT INTO projects (project_name , profit_margin , project_status , owner_customer_id) VALUES (?, ?, ?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, project.getProjectName());
            pstmt.setDouble(2, project.getProfitMargin());
            pstmt.setObject(3, ProjectStatus.ENCOURS, Types.OTHER);
            pstmt.setObject(4, project.getCustomer().getCustomerId(), Types.OTHER);

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                uuid = rs.getObject("project_id", UUID.class);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return uuid;
    }
}
