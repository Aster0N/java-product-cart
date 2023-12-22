package services.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private final DatabaseService dataBaseService;
    public UserService() {
        this.dataBaseService = new DatabaseService();
    }

    public int getUserIdByUsernameAndPassword(String login, String password) {
        Connection conn = dataBaseService.getConnect();
        int userId = -1;
        try {
            String sql = "select id from users where login = " + login + " and password = " + password + ";";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }
}