import view.loginpanel.LoginFrame;
import java.awt.EventQueue;

/**
 * Created by kreisso on 02.11.2018.
 */
public class main {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame("Logowanie");
            }
        });
    }

}
