package model;

public class Login {
    private String nick;
    private String password;
    private boolean status = false;

    public Login(){}

    public Login(String nick, String password) {
        this.nick = nick;
        this.password = password;

    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
