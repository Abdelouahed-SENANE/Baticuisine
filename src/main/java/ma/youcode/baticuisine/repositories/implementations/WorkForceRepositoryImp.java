package ma.youcode.baticuisine.repositories.implementations;


import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.entities.WorkForce;
import ma.youcode.baticuisine.repositories.WorkForceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class WorkForceRepositoryImp implements WorkForceRepository {

    private Connection connection = Database.getInstance().getConnection();

    public WorkForceRepositoryImp(){}
    @Override
    public void save(WorkForce workForce) {
        String SQL = "insert into workforces (component_name , vat , hourly_rate , work_hours, worker_productivity_coefficient, project_id, workforce_type) VALUES (?, ?, ?, ?, ?, ? , ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(SQL);){

            pstmt.setString(1, workForce.getComponentName());
            pstmt.setDouble(2, workForce.getVat());
            pstmt.setDouble(3, workForce.getHourlyRate());
            pstmt.setDouble(4, workForce.getWorkHours());
            pstmt.setDouble(5, workForce.getWorkerProductivityCoefficient());
            pstmt.setObject(6 , workForce.getProject().getProjectId() , Types.OTHER);
            pstmt.setObject(7, workForce.getWorkForceType() , Types.OTHER);

            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
