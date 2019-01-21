package TCP;

import java.io.*;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ServerTCPThread extends Thread {
    private File file;
    //private Multimap<String,String> multimap;
    Socket mySocket;

    public ServerTCPThread(Socket socket)
    {
        super();
        mySocket = socket;
       // multimap = ArrayListMultimap.create();

    }

    private void getFiles(String sample)
    {
//    String path = "/Users/kreisso/Desktop/";
        String path = System.getProperty("user.dir");
//        String path = System.getProperty(a"home.dir");
        file = new File(path);

        BlockingQueue<File> arrayBlockingQueue = new ArrayBlockingQueue<File>(5);
       // new Thread(new PoszukiwaczSciezek(arrayBlockingQueue, file)).start();;

        for (int i = 0; i < 50; i++);
          //  new Thread(new PrzeszukiwaczPliku(arrayBlockingQueue, sample, multimap)).start();

    }

    public void run()
    {
        String sample ="";
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

            while (!(sample = in.readLine()).equals("exit")) {
              //  multimap.clear();
                System.out.println(sample.length());
                if (sample.length() > 0) getFiles(sample);

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    while (true) {
                        try {
                            break;
                        } catch (ConcurrentModificationException e1) {
                            System.out.println("test");

                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e2) {
                                e1.printStackTrace();
                            }

                        } finally {



                        }
                    }
                }
             //   System.out.println(multimap.size());
              //  System.out.println(multimap);
                OutputStream os = mySocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
             //   oos.writeObject(multimap);
                oos.close();
                os.close();            }

            mySocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}