package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.*;
import ma.youcode.baticuisine.enums.ComponentType;
import ma.youcode.baticuisine.enums.ProjectStatus;
import ma.youcode.baticuisine.enums.WorkForceType;
import ma.youcode.baticuisine.repositories.ProjectRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

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
        Map<UUID, Project> projects = new HashMap<>();
        String query = "SELECT p.*,cu.* , e.*, c.*,m.*, w.*  " +
                "FROM projects p " +
                "INNER JOIN customers cu ON cu.customer_id = p.owner_customer_id " +
                "INNER JOIN estimates e ON e.project_id = p.project_id " +
                "LEFT JOIN materials m ON m.project_id = p.project_id " +
                "LEFT JOIN workforces w ON w.project_id = p.project_id " +
                "LEFT JOIN components c ON (m.component_id = c.component_id OR w.component_id = c.component_id) " +
                "ORDER BY p.project_id";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UUID projectId = rs.getObject("project_id", UUID.class);
                Project project = projects.computeIfAbsent(projectId, id -> getProject(rs));

                ComponentType componentType = ComponentType.valueOf(rs.getString("component_type"));
                if (componentType.equals(ComponentType.valueOf("Material"))) {
                    Material material = getMaterial(rs);
                    project.addComponent(material);
                }else if (componentType.equals(ComponentType.valueOf("WorkForce"))) {
                    WorkForce workForce = getWorkForce(rs);
                    project.addComponent(workForce);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(projects.values());
    }



    @Override
    public Optional<Project> findById(UUID id) {
//        Project project = null;
//        String query = "SELECT p.*,co.* , e.*, c.*,m.*, w.* "
//                + "FROM projects p "
//                + "INNER JOIN customers co ON co.customer_id = p.owner_customer_id "
//                + "INNER JOIN estimates e ON e.project_id = p.project_id " +
//                " INNER JOIN components c ON c.project_id = p.project_id "
//                + "LEFT JOIN materials m ON m.project_id = p.project_id "
//                + "LEFT JOIN workforces w ON w.project_id = p.project_id "
//                + "WHERE p.project_id = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
//            pstmt.setObject(1, id);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                // Create project and customer
//                project = new Project();
//                project.setProjectId(rs.getObject("project_id", UUID.class));
//                project.setProjectName(rs.getString("project_name"));
//                project.setProfitMargin(rs.getDouble("profit_margin"));
//                project.setDiscount(rs.getDouble("discount"));
//                project.setProjectStatus(ProjectStatus.valueOf(rs.getString("project_status")));
//
//                Customer customer = new Customer();
//                customer.setCustomerId(rs.getObject("customer_id", UUID.class));
//                customer.setCustomerName(rs.getString("customer_name"));
//                customer.setAddress(rs.getString("address"));
//                customer.setProfessional(rs.getBoolean("is_professional"));
//                project.setCustomer(customer);
//
//                // Create estimate
//                Estimate estimate = new Estimate();
//                estimate.setEstimateId(rs.getObject("estimate_id", UUID.class));
//                estimate.setIssueAt(rs.getObject("issue_at", LocalDate.class));
//                estimate.setValidateAt(rs.getObject("validate_at", LocalDate.class));
//                project.setEstimate(estimate);
//
//                do {
//                    if (rs.getDouble("quantity") != 0) {
//                        Material material = new Material();
//                        material.setQualityCoefficient(rs.getDouble("quality_coefficient"));
//                        material.setQuantity(rs.getDouble("quantity"));
//                        material.setPricePerUnit(rs.getDouble("price_per_unit"));
//                        material.setComponentName(rs.getString("component_name"));
//                        material.setVat(rs.getDouble("vat"));
//                        material.setTransportationCost(rs.getDouble("transportation_cost"));
//                        material.setComponentId(rs.getObject("component_id", UUID.class));
//                        project.addComponent(material);
//                    } else if (rs.getDouble("hourly_rate") != 0) {
//                        WorkForce workforce = new WorkForce();
//                        workforce.setWorkerProductivityCoefficient(rs.getDouble("worker_productivity_coefficient"));
//                        workforce.setWorkHours(rs.getDouble("work_hours"));
//                        workforce.setHourlyRate(rs.getDouble("hourly_rate"));
//                        workforce.setComponentName(rs.getString("component_name"));
//                        workforce.setVat(rs.getDouble("vat"));
//                        workforce.setWorkForceType(WorkForceType.valueOf(rs.getString("workforce_type")));
//                        workforce.setComponentId(rs.getObject("component_id", UUID.class));
//                        project.addComponent(workforce);
//                    }
//                } while (rs.next());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
        return Optional.empty();
    }

    private Project getProject(ResultSet rs) {
        try{
            Project project = new Project();
            project.setProjectId(rs.getObject("project_id", UUID.class));
            project.setProjectName(rs.getString("project_name"));
            project.setProfitMargin(rs.getDouble("profit_margin"));
            project.setDiscount(rs.getDouble("discount"));
            project.setProjectStatus(ProjectStatus.valueOf(rs.getString("project_status")));

            Customer customer = new Customer();
            customer.setCustomerId(rs.getObject("customer_id", UUID.class));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setAddress(rs.getString("address"));
            customer.setProfessional(rs.getBoolean("is_professional"));
            project.setCustomer(customer);

            Estimate estimate = new Estimate();
            estimate.setEstimateId(rs.getObject("estimate_id", UUID.class));
            estimate.setIssueAt(rs.getObject("issue_at", LocalDate.class));
            estimate.setValidateAt(rs.getObject("validate_at", LocalDate.class));
            project.setEstimate(estimate);

            return project;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Material getMaterial(ResultSet rs) {
        try {
            Material material = new Material();
            material.setComponentId(rs.getObject("component_id", UUID.class));
            material.setComponentName(rs.getString("component_name"));
            material.setVat(rs.getDouble("vat"));
            material.setPricePerUnit(rs.getDouble("price_per_unit"));
            material.setQuantity(rs.getDouble("quantity"));
            material.setTransportationCost(rs.getDouble("transportation_cost"));
            material.setQualityCoefficient(rs.getDouble("quality_coefficient"));
            return material;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Handle nulls as needed
        }
    }

    private WorkForce getWorkForce(ResultSet rs) {
        try {
            WorkForce workforce = new WorkForce();
            workforce.setComponentId(rs.getObject("component_id", UUID.class));
            workforce.setComponentName(rs.getString("component_name"));
            workforce.setVat(rs.getDouble("vat"));
            workforce.setHourlyRate(rs.getDouble("hourly_rate"));
            workforce.setWorkHours(rs.getDouble("work_hours"));
            workforce.setWorkForceType(WorkForceType.valueOf(rs.getString("workforce_type")));
            workforce.setWorkerProductivityCoefficient(rs.getDouble("worker_productivity_coefficient"));
            return workforce;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Handle nulls as needed
        }
    }
}
