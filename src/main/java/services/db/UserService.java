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

    public String getUserIdByUsernameAndPassword(String login, String password) {
        Connection conn = dataBaseService.getConnect();
        String userUId = "";
        try {
            String sql = "select id from users where login = '" + login + "' and password = '" + password + "';";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userUId = resultSet.getString("uid");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userUId;
    }
}