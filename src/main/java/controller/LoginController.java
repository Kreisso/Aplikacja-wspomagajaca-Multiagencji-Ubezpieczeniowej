package controller;

import model.Server.ClientMain;
import model.Server.Connectivity;
import model.Server.Login;
import model.Server.Register;
import view.loginpanel.LoginFrame;
import view.loginpanel.RegisterFrame;
import view.mainviews.ClientMainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Login model;
    private LoginFrame view;
    private Connectivity con;
    private int ukk;

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
                        new Register(), new RegisterFrame("Rejestracja"), view );
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
                System.out.println(ukk);

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

                 preparedStatement = null;
                 resultSet = null;


                 sql="select ukk from client where login=? ";
                try{
                    //con =  new Connectivity();


                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, getModelNick());
                    resultSet = preparedStatement.executeQuery();

                    if(resultSet.next())
                    {
                        ukk = resultSet.getInt("ukk");


                    }
                    else
                    {

                    }
                }
                catch(SQLException ex)
                {
                    System.out.println(ex);
                }
                catch (Exception e){
                    System.out.println(e);
                }


                new MainClientController(new ClientMain(), new ClientMainFrame("Panel klienta"),
                        view, ukk, con );
                view.setVisible(false);
            }
            else {
                //TODO add label with error
                con.close();
            }
        }
    }


}
