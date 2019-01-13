package controller;

import model.Server.Connectivity;
import view.mainviews.ClientMainFrame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class MessageController extends Thread {

    Connectivity con;
    ClientMainFrame view;
    int ukk;


    public MessageController(ClientMainFrame view, int ukk)
    {
        this.view = view;
        this.ukk = ukk;
        System.out.println("tworze kontroler wiadomości");
    }


    public void run()
    {
        while (true)
        {
            try {
                view.setMessageTextPane(getMessage());
                TimeUnit.SECONDS.sleep(3);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public String getMessage()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String message ="";
        String sql="select message from client where ukk = ?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setInt(1,ukk);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                message = resultSet.getString("message");
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
            con.close();
        }
        return message;
    }

    public void setView(ClientMainFrame view)
    {
        this.view = view;
    }
}
