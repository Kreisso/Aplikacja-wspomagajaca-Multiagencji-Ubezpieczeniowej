package view.loginpanel;

import view.*;

import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends view.Frame {
    private int frameWidth = 500;
    private int frameHeight = 500;

    private JPanel logoPanel;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JLabel labelGoToSignUp;
    private JButton buttonLogIn;
    private JTextField inputLogin;
    private JPasswordField inputPassword;

    public LoginFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        logoPanel = new JPanel() {
            private BufferedImage logoImage;

            @Override
            public void paintComponent(Graphics g) {
                //File imageFile = new File("/Users/RobertTrojan/Desktop/model.png");
                String filename = "assets/img/logo.png";
                String workingDirectory = System.getProperty("user.dir");

                File imageFile = new File(workingDirectory, filename);
                System.out.println("Final filepath : " + imageFile.getAbsolutePath());
                try {
                    logoImage = ImageIO.read(imageFile);
                } catch (IOException e) {
                    System.err.println("Błąd wczytywania logo");
                }
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(logoImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        logoPanel.setSize(200, 100);
        logoPanel.setLocation((frameWidth/2)-(logoPanel.getWidth()/2), 50);
        this.add(logoPanel);

        labelLogin = new JLabel("Login:");
        labelLogin.setSize(labelLogin.getPreferredSize());
        labelLogin.setLocation(50, 200);
        this.add(labelLogin);

        inputLogin = new JTextField();
        inputLogin.setSize(400, 40);
        inputLogin.setLocation(50, 220);
        this.add(inputLogin);

        labelPassword = new JLabel("Hasło:");
        labelPassword.setSize(labelPassword.getPreferredSize());
        labelPassword.setLocation(50, 290);
        this.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setSize(400, 40);
        inputPassword.setLocation(50, 310);
        this.add(inputPassword);

        labelGoToSignUp = new JLabel("Rejestracja");
        labelGoToSignUp.setSize(labelGoToSignUp.getPreferredSize());
        labelGoToSignUp.setLocation(50, 400);
        labelGoToSignUp.setForeground(Color.blue);
        this.add(labelGoToSignUp);

        buttonLogIn = new JButton("Zaloguj");
        buttonLogIn.setSize(100,50);
        buttonLogIn.setLocation(450-100,380);
        this.add(buttonLogIn);
    }
}