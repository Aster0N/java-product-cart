package classes;

public class User {
    private String uId;
    private String login;
    private String password;

    public User(String uId, String login, String password) {
        this.uId = uId;
        this.login = login;
        this.password = password;
    }

    public String getUId() {
        return uId;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public void setUId(String uId) {
        this.uId = uId;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
