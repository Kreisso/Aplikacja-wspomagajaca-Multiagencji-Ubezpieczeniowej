package TCP;

import controller.LoginController;
import model.Server.Connectivity;
import model.Server.Login;
import view.loginpanel.LoginFrame;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientTCP {
    static Login login;

    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                login = new Login();
                 new LoginController(login, new LoginFrame("Logowanie"));

            }
        });

    }

    public static Login loginTCP(Login loginSample) {
        Login response = null;
        String args[] = new String[2];
        args[0]= getServerIp();
        args[1]= "12367";
        if (args.length < 2)
            System.out.println("Wprowadź adres serwera TCP oraz numer portu");
        else{
            int port = 0;
            try {
                port = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                System.err.println("Wprowadź poprawny numer portu: " + e);
            }
            try {
                Socket socket = new Socket(InetAddress.getByName(args[0]), port);
                socket.setTcpNoDelay(true);

                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(loginSample);
                while(true){

                    InputStream is = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    response = (Login) ois.readObject();
                    System.out.println("test ;"+response.isStatus());
                    if (response!=null){
                        is.close();
                        break;
                    }
                }


                System.out.println("zamykamy socket" );
                socket.close();
                return response;
            }
            catch (Exception e) {
                //System.err.println(e);
            }finally {
                System.out.println(login.isStatus());

            }
        }

        return response;
    }


    public static String getServerIp()
    {
        String ip = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql="select ip from server_ip where id = 1";
        Connectivity con = new Connectivity();
        try{

            preparedStatement = con.getConn().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                ip = resultSet.getString("ip");

            }
            else
            {
                System.out.println("Brak ip servera");
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
            return ip;
        }
    }
}
