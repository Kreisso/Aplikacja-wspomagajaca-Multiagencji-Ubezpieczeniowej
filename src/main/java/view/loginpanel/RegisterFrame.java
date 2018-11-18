package view.loginpanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kreisso on 02.11.2018.
 */
public class RegisterFrame extends view.Frame {

    public RegisterFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int frameWidth = 700;
        int frameHeight = 950;
        this.setLocation((screenWidth- frameWidth)/2, (screenHeight- frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        JPanel logoPanel = new JPanel() {
            private BufferedImage logoImage;

            @Override
            public void paintComponent(Graphics g) {
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
        logoPanel.setLocation((frameWidth /2)-(logoPanel.getWidth()/2), 50);
        this.add(logoPanel);

        int halfFieldWidth = (frameWidth - 150) / 2;
        int firstLabelHeight = 170;
        int firstFieldHeight = 190;
        int spaceBetween = 90;

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setSize(labelLogin.getPreferredSize());
        labelLogin.setLocation(50, firstLabelHeight);
        this.add(labelLogin);

        JTextField inputLogin = new JTextField();
        inputLogin.setSize(frameWidth -100, 40);
        inputLogin.setLocation(50, firstFieldHeight);
        this.add(inputLogin);

        inputLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel labelPassword = new JLabel("Hasło:");
        labelPassword.setSize(labelPassword.getPreferredSize());
        labelPassword.setLocation(50, firstLabelHeight+spaceBetween);
        this.add(labelPassword);

        JTextField inputPassword = new JPasswordField();
        inputPassword.setSize(frameWidth -100, 40);
        inputPassword.setLocation(50, firstFieldHeight+spaceBetween);
        this.add(inputPassword);

        JLabel labelRepeatPassword = new JLabel("Powtórz hasło:");
        labelRepeatPassword.setSize(labelRepeatPassword.getPreferredSize());
        labelRepeatPassword.setLocation(50, firstLabelHeight+(2*spaceBetween));
        this.add(labelRepeatPassword);

        JTextField inputRepeatPassword = new JPasswordField();
        inputRepeatPassword.setSize(frameWidth -100, 40);
        inputRepeatPassword.setLocation(50, firstFieldHeight+(2*spaceBetween));
        this.add(inputRepeatPassword);

        JLabel labelPesel = new JLabel("Pesel:");
        labelPesel.setSize(labelPesel.getPreferredSize());
        labelPesel.setLocation(50, firstLabelHeight+(3*spaceBetween));
        this.add(labelPesel);

        JTextField inputPesel = new JTextField();
        inputPesel.setSize(frameWidth -100, 40);
        inputPesel.setLocation(50, firstFieldHeight+(3*spaceBetween));
        this.add(inputPesel);

        JLabel labelName = new JLabel("Imię:");
        labelName.setSize(labelName.getPreferredSize());
        labelName.setLocation(50, firstLabelHeight+(4*spaceBetween));
        this.add(labelName);

        JTextField inputName = new JTextField();
        inputName.setSize(halfFieldWidth, 40);
        inputName.setLocation(50, firstFieldHeight+(4*spaceBetween));
        this.add(inputName);

        JLabel labelSurname = new JLabel("Nazwisko:");
        labelSurname.setSize(labelSurname.getPreferredSize());
        labelSurname.setLocation(halfFieldWidth + 100, firstLabelHeight+(4*spaceBetween));
        this.add(labelSurname);

        JTextField inputSurname = new JTextField();
        inputSurname.setSize(halfFieldWidth, 40);
        inputSurname.setLocation(halfFieldWidth + 100, firstFieldHeight+(4*spaceBetween));
        this.add(inputSurname);

        JLabel labelCity = new JLabel("Miasto:");
        labelCity.setSize(labelCity.getPreferredSize());
        labelCity.setLocation(50, firstLabelHeight+(5*spaceBetween));
        this.add(labelCity);

        JTextField inputCity = new JTextField();
        inputCity.setSize(halfFieldWidth, 40);
        inputCity.setLocation(50, firstFieldHeight+(5*spaceBetween));
        this.add(inputCity);

        JLabel labelStreetAndNo = new JLabel("Ulica i numer budynku:");
        labelStreetAndNo.setSize(labelStreetAndNo.getPreferredSize());
        labelStreetAndNo.setLocation(halfFieldWidth + 100, firstLabelHeight+(5*spaceBetween));
        this.add(labelStreetAndNo);

        JTextField inputStreetAndNo = new JTextField();
        inputStreetAndNo.setSize(halfFieldWidth, 40);
        inputStreetAndNo.setLocation(halfFieldWidth + 100, firstFieldHeight+(5*spaceBetween));
        this.add(inputStreetAndNo);

        JLabel labelPostCode = new JLabel("Kod pocztowy:");
        labelPostCode.setSize(labelPostCode.getPreferredSize());
        labelPostCode.setLocation(50, firstLabelHeight+(6*spaceBetween));
        this.add(labelPostCode);

        JTextField inputPostCode = new JTextField();
        inputPostCode.setSize(halfFieldWidth, 40);
        inputPostCode.setLocation(50, firstFieldHeight+(6*spaceBetween));
        this.add(inputPostCode);

        JLabel labelPhoneNo = new JLabel("Numer telefonu:");
        labelPhoneNo.setSize(labelPhoneNo.getPreferredSize());
        labelPhoneNo.setLocation(halfFieldWidth + 100, firstLabelHeight+(6*spaceBetween));
        this.add(labelPhoneNo);

        JTextField inputPhoneNo = new JTextField();
        inputPhoneNo.setSize(halfFieldWidth, 40);
        inputPhoneNo.setLocation(halfFieldWidth + 100, firstFieldHeight+(6*spaceBetween));
        this.add(inputPhoneNo);

        JLabel labelGoToLogin = new JLabel("Logowanie");
        labelGoToLogin.setSize(labelGoToLogin.getPreferredSize());
        labelGoToLogin.setLocation(50, 850);
        labelGoToLogin.setForeground(Color.blue);
        this.add(labelGoToLogin);

        JButton buttonSignUp = new JButton("Zarejestruj");
        buttonSignUp.setSize(200,50);
        buttonSignUp.setLocation(frameWidth - 250,830);
        this.add(buttonSignUp);


    }

}
