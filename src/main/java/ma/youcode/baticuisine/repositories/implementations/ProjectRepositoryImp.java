package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.entities.Estimate;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.enums.ProjectStatus;
import ma.youcode.baticuisine.repositories.ProjectRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImp implements ProjectRepository {
    private final Connection connection;
    public ProjectRepositoryImp() {
        connection = Database.getInstance().getConnection();
    }

    @Override
    public UUID save(Project project) {
        UUID uuid = null;
        String SQL = "INSERT INTO projects (project_name , profit_margin , project_status , owner_customer_id , discount) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(SQL , Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, project.getProjectName());
            pstmt.setDouble(2, project.getProfitMargin());
            pstmt.setObject(3, ProjectStatus.ENCOURS, Types.OTHER);
            pstmt.setObject(4, project.getCustomer().getCustomerId(), Types.OTHER);

            pstmt.setDouble(5, project.getDiscount());

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


    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects "
                + "INNER JOIN customers ON customers.customer_id = projects.owner_customer_id "
                + "INNER JOIN estimates ON estimates.project_id = projects.project_id ";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.executeQuery();
            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getObject("project_id", UUID.class));
                project.setProjectName(rs.getString("project_name"));
                project.setProfitMargin(rs.getDouble("profit_margin"));
                project.setProjectStatus(ProjectStatus.valueOf(rs.getString("project_status")));
                Customer customer = new Customer();
                customer.setCustomerId(rs.getObject("customer_id", UUID.class));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setAddress(rs.getString("address"));
                customer.setProfessional(rs.getBoolean("is_professional"));
                project.setCustomer(customer);
                Estimate estimate  = new Estimate();
                estimate.setEstimateId(rs.getObject("estimate_id", UUID.class));
                estimate.setIssueAt(rs.getObject("issue_at", LocalDate.class));
                estimate.setValidateAt(rs.getObject("validate_at", LocalDate.class));
                project.setEstimate(estimate);
                projects.add(project);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
