package TCP;

import controller.LoginController;

import java.io.*;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ServerTCPThread extends Thread {
    private File file;
    //private Multimap<String,String> multimap;
    LoginController loginController;
    Socket mySocket;

    public ServerTCPThread(Socket socket)
    {
        super();
        mySocket = socket;

    }



    public void run()
    {
        String sample ="";
        try {


            while(true){

                System.out.println("test");
                InputStream is = mySocket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                loginController = (LoginController)ois.readObject();

                is.close();
                if (loginController!=null){
                is.close();
                break;
                }

            }
//                sc.close();
            System.out.println("zamykamy socket" );
            mySocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
//                sc.close();
            System.out.println("zamykamy socket" );

        OutputStream os = null;
        try {
            os = mySocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(true);
            oos.close();
            os.close();
            mySocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}