package controller;

import model.Server.*;
import view.loginpanel.LoginFrame;
import view.loginpanel.RegisterFrame;
import view.mainviews.AgentMainFrame;
import view.mainviews.ClientMainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Login model;
    private LoginFrame view;
    private Connectivity con;
    private int ukk, id;

    public LoginController(Login model, LoginFrame view) {
        this.model = model;
        this.view = view;
        setViewLoginButtonEvent();
        setViewGoSignUpButtonEvent();
        setViewGoToTutorialButtonEvent();
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
                view.setErrorMessageLabel("");
            }
        });
    }

    private void setViewGoToTutorialButtonEvent()
    {
        view.setButtonGoToTutorial(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                playVideo();

            }
        });
    }

    private void getIdOrUkk()
    {

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

                 preparedStatement = null;
                 resultSet = null;


                 sql="select ukk from client where login=? ";
                  ukk = getIdFromDatabase(sql, "ukk");
                  System.out.println("Klient zalogowany o UKK: "+ukk);
                if(ukk > 0) {
                    view.dispose();
                    new MainClientController(new ClientMain(), new ClientMainFrame("Panel klienta"),
                            view, ukk, -1, con);
                }
                else {

                    sql="select id from agent where login=? ";
                    id =  getIdFromDatabase(sql, "id");


                    System.out.println("Agent zalogowany o id: "+ id);
                    view.dispose();
                    new MainAgentController(new AgentMain(), new AgentMainFrame("Polisy klientów"),
                            view, id, -1, con);
                }
                view.setVisible(false);

            }
            else {
                //TODO add label with error
                view.setErrorMessageLabel("Błędny login lub hasło");
                con.close();

            }
        }
    }

    private int getIdFromDatabase(String sql, String type) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int result = -1;
        try {

            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, getModelNick());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getInt(type);


            } else {

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            return result;
        }
    }

    private void playVideo()
    {

        System.out.println("video");
        String filename = "/assets/video/Szkolenie_dla_Klienta.mov";
        String workingDirectory = System.getProperty("user.dir");
        //text file, should be opening in default text editor
        File file = new File(workingDirectory+filename);

        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
