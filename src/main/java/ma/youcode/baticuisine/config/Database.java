package ma.youcode.baticuisine.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static Connection connection;
    private static Database instance;
    private final String URL = "jdbc:postgresql://localhost:5432/baticuisine";
    private final String USER = "postgres";
    private final String PASSWORD = "password";

    private Database() {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connection Successful");
        }catch (SQLException e){
            System.out.println("Handle Queries SQL Exception ----> " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Postgres SQL JDBC Driver not found ----> " + e.getMessage());
        }
    }

    public  Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database();
            }
        }catch (SQLException e) {
            System.out.println("Failed to create instance from database class ----> " + e.getMessage());
        }
        return instance;
    }


}
