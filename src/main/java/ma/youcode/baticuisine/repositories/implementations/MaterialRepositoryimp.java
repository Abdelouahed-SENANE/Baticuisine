package ma.youcode.baticuisine.repositories.implementations;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.Material;
import ma.youcode.baticuisine.repositories.MaterialRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class MaterialRepositoryimp implements MaterialRepository {
    private Connection connection = Database.getInstance().getConnection();
    public MaterialRepositoryimp() {}
    @Override
    public void save(Material material) {
        String SQL = "insert into materials (component_name , vat , price_per_unit , quantity, transportation_cost, quality_coefficient, project_id) VALUES (? , ? , ? , ? , ? , ? , ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(SQL);){

            pstmt.setString(1, material.getComponentName());
            pstmt.setDouble(2, material.getVat());
            pstmt.setDouble(3, material.getPricePerUnit());
            pstmt.setDouble(4, material.getQuantity());
            pstmt.setDouble(5, material.getTransportationCost());
            pstmt.setDouble(6, material.getQualityCoefficient());
            pstmt.setObject(7 , material.getProject().getProjectId() , Types.OTHER);

            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
