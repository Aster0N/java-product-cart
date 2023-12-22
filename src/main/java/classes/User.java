package classes;

public class User {
    private int uId;
    private String login;
    private String password;

    public User(int uId, String login, String password) {
        this.uId = uId;
        this.login = login;
        this.password = password;
    }

    public int getUId() {
        return uId;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public void setUId(int uId) {
        this.uId = uId;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
