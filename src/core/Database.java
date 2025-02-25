package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection =null;
    private static Database instance = null;
    private final String DB_URL ="jdbc:postgresql://localhost:5432/emreturism";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "130440";

    // Database connection process.

    public Database() {
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}
