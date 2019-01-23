package view.loginpanel;

import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class LoginFrame extends view.Frame implements Serializable {
    private int frameWidth = 500;
    private int frameHeight = 500;

    private JPanel logoPanel;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JButton buttonGoToSignUp;
    private JButton buttonLogIn;
    private JButton buttonGoToTutorial;
    private JTextField inputLogin;
    private JPasswordField inputPassword;
    private JLabel errorMessageLabel;

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
                String filename = "src/main/assets/img/logo.png";
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
        logoPanel.setSize(177, 106);
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

        buttonLogIn = new JButton("Zaloguj");
        buttonLogIn.setSize(100,50);
        buttonLogIn.setLocation(450-100,380);
        this.getRootPane().setDefaultButton(buttonLogIn);
        this.add(buttonLogIn);

        buttonGoToSignUp = new JButton("Rejestracja");
        buttonGoToSignUp.setBorder(null);
        buttonGoToSignUp.setSize(buttonGoToSignUp.getPreferredSize());
        buttonGoToSignUp.setLocation(50, 400);
        buttonGoToSignUp.setForeground(Color.blue);
        this.add(buttonGoToSignUp);

        buttonGoToTutorial = new JButton("Instruktaż");
        buttonGoToTutorial.setBorder(null);
        buttonGoToTutorial.setSize(buttonGoToTutorial.getPreferredSize());
        buttonGoToTutorial.setLocation((frameWidth-100)/2,400);
        buttonGoToTutorial.setForeground(Color.blue);
        this.add(buttonGoToTutorial);

        errorMessageLabel = new JLabel("");
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
        errorMessageLabel.setLocation(50, 360);
        errorMessageLabel.setForeground(Color.red);
        this.add(errorMessageLabel);
    }

    public void setButtonLogIn(ActionListener actionListener){
        buttonLogIn.addActionListener(actionListener);
    }

    public void setButtonGoToSignUp(ActionListener actionListener){
        buttonGoToSignUp.addActionListener(actionListener);
    }

    public void setButtonGoToTutorial(ActionListener actionListener){
        buttonGoToTutorial.addActionListener(actionListener);
    }

    public char[] getPassword() {
        return inputPassword.getPassword();
    }

    public String getLogin() {
        return inputLogin.getText();
    }

    public void setErrorMessageLabel(String message){
        errorMessageLabel.setText(message);
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
    }
}