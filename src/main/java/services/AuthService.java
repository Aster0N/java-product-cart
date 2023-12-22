package services;

import services.db.UserService;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private final UserService userService;

    public AuthService() {
        this.userService = new UserService();
    }

    public String hashPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger hashInt = new BigInteger(1, hashBytes);
        return hashInt.toString(16);
    }

    public String signIn(String login, String password) {
        String hashedPassword = hashPassword(password);
        return userService.getUserIdByUsernameAndPassword(login, hashedPassword);
    }

    public String signUp(String login, String password) {
        if (userService.isUserExistsByLogin(login)) {
            return "";
        }
        String hashedPassword = hashPassword(password);
        return userService.createUser(login, hashedPassword);
    }
}
