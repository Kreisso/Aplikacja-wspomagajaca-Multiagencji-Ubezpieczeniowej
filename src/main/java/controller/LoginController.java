package controller;

import model.Login;
import view.loginpanel.LoginFrame;

public class LoginController {
    private Login model;
    private LoginFrame view;

    public LoginController(Login model, LoginFrame view) {
        this.model = model;
        this.view = view;
    }

    public void setModelNick(String nick)
    {
        model.setNick(nick);
    }

    public void setModelPassword(String password)
    {
        model.setPassword(password);
    }

    public String getModelNick()
    {
        return model.getNick();
    }

    public String getModelPassword()
    {
        return model.getPassword();
    }

    public String getViewNick()
    {
        return view.getLogin();
    }

    public String getViewPassword()
    {
        return String.valueOf(view.getPassword());
    }




}
