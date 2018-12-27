import controller.LoginController;
import model.Server.Login;
import view.loginpanel.LoginFrame;
import java.awt.EventQueue;

public class main {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginController(new Login(), new LoginFrame("Logowanie"));
            }
        });

    }
}
