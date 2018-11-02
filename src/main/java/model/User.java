package model;

/**
 * Created by kreisso on 02.11.2018.
 */
public abstract class User {
    public String login;
    public String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
