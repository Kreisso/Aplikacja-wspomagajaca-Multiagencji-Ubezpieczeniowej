package TCP;

import model.Server.Connectivity;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientTCP {
//    private Multimap<String,String> multimap;


   // public ClientTCP(String sample, Multimap<String, String> m)
    public ClientTCP(String sample)
    {
       // multimap = m;
        String args[] = new String[2];
        args[0]= getServerIp();
        args[1]= "12367";
        if (args.length < 2)
            System.out.println("Wprowadź adres serwera TCP oraz numer portu");
        else{
            int port = 0;
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Wprowadź poprawny numer portu: " + e);
                return;
            }
            try {
                Socket socket = new Socket(InetAddress.getByName(args[0]), port);

                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//                Scanner sc = new Scanner(System.in);
                String str;
                socket.setTcpNoDelay(true);

                str = sample ;//sc.nextLine();
                out.println(str);
                out.flush();

                while(true){

                    System.out.println("test");
                    InputStream is = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                   // multimap = (ArrayListMultimap)ois.readObject();
                   // System.out.println(multimap);
//                    System.out.println((String)ois.readObject());
                    is.close();
                   // if (multimap!=null){
                    //    System.out.println(multimap.size());
                        is.close();
                        break;
                   // }
//                    if(str.equals("exit")) break;
                }
//                sc.close();
                System.out.println("zamykamy socket" );
                socket.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }
//
//    public Multimap<String, String> getMultimap() {
//        System.out.println(multimap.size());
//        return multimap;
//    }

    public String getServerIp()
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
