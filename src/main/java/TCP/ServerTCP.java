package TCP;

import model.Server.Connectivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerTCP {

    public static void main(String args[]) throws UnknownHostException{

        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(ip);
        /**/
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql="select * from server_ip where id = 1";
        Connectivity con = new Connectivity();
        try{

            preparedStatement = con.getConn().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                System.out.println(ip);
                sql = "UPDATE server_ip SET ip = ? WHERE id = 1 ";
                preparedStatement = con.getConn().prepareStatement(sql);
                preparedStatement.setString(1,ip);
                preparedStatement.executeUpdate();

            }
            else
            {
                sql = "INSERT INTO server_ip (id, ip) VALUES (1,?)";
                preparedStatement = con.getConn().prepareStatement(sql);
                preparedStatement.setString(1,ip);

                preparedStatement.executeUpdate();
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


            int port = 0;
            try {

                port = 12367;

            } catch (NumberFormatException e) {
                System.err.println("Wprowadź poprawny numer portu: " + e);
                return;
            }

            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                while (true) {

                    Socket socket = serverSocket.accept();
                    (new ServerTCPThread(socket)).start();
                }
            } catch (Exception e) {
                System.err.println(e);
            }finally{
                if(serverSocket != null)
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }







    }



}