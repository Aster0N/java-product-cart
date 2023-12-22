package services.db;

import classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserService {
    public String getUserIdByUsernameAndPassword(String login, String password) {
        DatabaseService dbService = new DatabaseService();
        String userUId = "";
        try {
            String sql = "select id from users where login = '" + login + "' and password = '" + password + "';";
            ResultSet resultSet = dbService.select(sql);
            if (resultSet.next()) {
                userUId = resultSet.getString("uid");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userUId;
    }

    public User getUserByUId(String uId) {
        try {
            DatabaseService dbService = new DatabaseService();
            String sql = "select * from users where uid='" + uId + "';";
            ResultSet resultSet = dbService.select(sql);
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("uid"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
            else {
                throw new IllegalArgumentException("[GET_USER_BY_ID_ERROR]: No user with id " + uId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean isUserExistsByLogin(String login) {
        DatabaseService dbService = new DatabaseService();
        try {
            String sql = "select count(*) from users where login='" + login + "';";
            ResultSet resultSet = dbService.select(sql);
            if (resultSet.next()) {
                return resultSet.getInt("count") != 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public String createUser(String login, String password) {
        DatabaseService dbService = new DatabaseService();
        String userUId = "";
        try {
            userUId = UUID.randomUUID().toString();;
            String sql = "insert into users (uid, login, password) values ('" +
                        userUId + "', '" +
                        login + "', '" +
                        password + "') returning uid;";
            ResultSet resultSet = dbService.select(sql);
            if (resultSet.next()) {
                return resultSet.getString("uid");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userUId;
    }
}