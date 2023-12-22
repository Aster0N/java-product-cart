package services;

import services.db.UserRepository;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public int auth(String username, String password) {
        return userRepository.getUserIdByUsernameAndPassword(username, password);
    }
}
