package services;

import services.db.UserRepository;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
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

    public int auth(String username, String password) {
        String hashedPassword = hashPassword(password);
        return userRepository.getUserIdByUsernameAndPassword(username, hashedPassword);
    }
}
