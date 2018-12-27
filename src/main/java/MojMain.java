import controller.LoginController;
import model.Server.Login;
import view.loginpanel.LoginFrame;
import view.loginpanel.RegisterFrame;
import view.multiagency.SearchMultiagencyFrame;
import view.user.ChangePasswordFrame;

import java.awt.*;

public class MojMain {

        public static void main(String[] args){

//            EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    new RegisterFrame("Rejestracja");
//                }
//            });

//            EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    LoginController loginController = new LoginController(new Login(), new LoginFrame("Logowanie"));
//                }
//            });

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new ChangePasswordFrame("Zmień hasło");
                }
            });

        }
}
