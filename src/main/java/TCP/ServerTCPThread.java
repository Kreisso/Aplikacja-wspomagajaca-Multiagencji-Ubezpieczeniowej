package TCP;

import controller.LoginController;
import controller.MainAgentController;
import controller.MainClientController;
import model.Server.AgentMain;
import model.Server.ClientMain;
import model.Server.Connectivity;
import model.Server.Login;
import view.mainviews.AgentMainFrame;
import view.mainviews.ClientMainFrame;

import java.io.*;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static TCP.ClientTCP.loginTCP;


public class ServerTCPThread extends Thread {
    private File file;
    //private Multimap<String,String> multimap;
    Login login;
    Socket mySocket;

    public ServerTCPThread(Socket socket)
    {
        super();
        mySocket = socket;

    }

    public void login(Login model)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql="select * from user where login=? and password=?";
        try{
            Connectivity con =  new Connectivity();

            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, model.getNick());
            preparedStatement.setString(2, model.getPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                model.setStatus(true);

            }
            else
            {
                model.setStatus(false);
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }


    }


    public void run()
    {
        String sample ="";
        try {


            while(true){

                InputStream is = mySocket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                login = (Login) ois.readObject();

                //is.close();
                if (login!=null){
                //is.close();
                break;
                }

            }
//                sc.close();
//            System.out.println("zamykamy socket" );
//            mySocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
//                sc.close();

        login(login);
        System.out.println(login.getNick());
        System.out.println(login.getPassword());
        System.out.println(login.isStatus());
        OutputStream os = null;
        try {
            os = mySocket.getOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(login);

            oos.close();

            os.close();
            mySocket.close();
            System.out.println("zamykamy socket" );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}