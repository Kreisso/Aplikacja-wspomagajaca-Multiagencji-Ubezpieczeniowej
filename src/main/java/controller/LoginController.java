package controller;

import model.Connectivity;
import model.Login;
import model.Register;
import view.loginpanel.LoginFrame;
import view.loginpanel.RegisterFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Login model;
    private LoginFrame view;
    private Connectivity con;

    public LoginController(Login model, LoginFrame view) {
        this.model = model;
        this.view = view;
        setViewLoginButtonEvent();
        setViewGoSignUpButtonEvent();
    }

    private void setModelNick(String nick)
    {
        model.setNick(nick);
    }

    private void setModelPassword(String password)
    {
        model.setPassword(password);
    }

    private void setModelStatus(boolean status)
    {
        model.setStatus(status);
    }

    private String getModelNick()
    {
        return model.getNick();
    }

    private String getModelPassword()
    {
        return model.getPassword();
    }


    private String getViewNick()
    {
        return view.getLogin();
    }

    private String getViewPassword()
    {
        return String.valueOf(view.getPassword());
    }

    private void setViewLoginButtonEvent()
    {
        view.setButtonLogIn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click login button");
                login();
            }
        });
    }

    private void setViewGoSignUpButtonEvent()
    {
        view.setButtonGoToSignUp(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click go sign up button");
                // TODO
                RegisterController registerController = new RegisterController(
                        new Register(), new RegisterFrame("Rejestracja"));
                view.setVisible(false);
            }
        });
    }

    private void login()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql="select * from user where login=? and password=?";
        try{
            con =  new Connectivity();
            setModelNick(getViewNick());
            setModelPassword(getViewPassword());

            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, getModelNick());
            preparedStatement.setString(2, getModelPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                setModelStatus(true);

            }
            else
            {
                setModelStatus(false);
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            if (model.isStatus()) {
                System.out.println("login");
                //TODO
            }
            else {
                //TODO add label with error
                con.close();
            }
        }
    }


}
