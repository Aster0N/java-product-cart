package services.db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseService {
    public Connection getConnect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Dotenv dotenv = Dotenv.load();
        final String url = dotenv.get("DB_URL");
        final String user = dotenv.get("DB_USER");
        final String password = dotenv.get("DB_PASSWORD");
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ResultSet select(String sql){
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            return statement.executeQuery(sql); // returning result set
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
    }

    public boolean insert(String sql) {
        boolean isSuccessful = false;
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            statement.executeUpdate(sql);
            isSuccessful = true;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }

}
