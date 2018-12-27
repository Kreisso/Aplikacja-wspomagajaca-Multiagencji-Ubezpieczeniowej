package view.loginpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class RegisterFrame extends view.Frame {
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frameWidth = 700;
    private int frameHeight = 700;
    private int halfFieldWidth = (frameWidth - 150) / 2;
    private int firstLabelHeight = 50;
    private int firstFieldHeight = 70;
    private int spaceBetween = 70;
    private JButton buttonSignUp;
    private JButton buttonGoToLogin;
    private JTextField inputLogin;
    private JPasswordField inputPassword;
    private JPasswordField inputRepeatPassword;
    private JTextField inputPesel;
    private JTextField inputName;
    private JTextField inputSurname;
    private JTextField inputCity;
    private JTextField inputStreetAndNo;
    private JTextField inputPostCode;
    private JTextField inputPhoneNo;

    public String getInputLogin() {
        return inputLogin.getText();
    }

    public char[] getInputPassword() {
        return inputPassword.getPassword();
    }

    public char[] getInputRepeatPassword() {
        return inputRepeatPassword.getPassword();
    }

    public String getInputPesel() {
        return inputPesel.getText();
    }

    public String getInputName() {
        return inputName.getText();
    }

    public String getInputSurname() {
        return inputSurname.getText();
    }

    public String getInputCity() {
        return inputCity.getText();
    }

    public String getInputStreetAndNo() {
        return inputStreetAndNo.getText();
    }

    public String getInputPostCode() {
        return inputPostCode.getText();
    }

    public String getInputPhoneNo() {
        return inputPhoneNo.getText();
    }

    public RegisterFrame(String name) throws HeadlessException {
        super(name);

        this.setLocation((screenWidth- frameWidth)/2, (screenHeight- frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setSize(labelLogin.getPreferredSize());
        labelLogin.setLocation(50, firstLabelHeight);
        this.add(labelLogin);

        inputLogin = new JTextField();
        inputLogin.setSize(frameWidth -100, 40);
        inputLogin.setLocation(50, firstFieldHeight);
        this.add(inputLogin);

        JLabel labelPassword = new JLabel("Hasło:");
        labelPassword.setSize(labelPassword.getPreferredSize());
        labelPassword.setLocation(50, firstLabelHeight+spaceBetween);
        this.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setSize(frameWidth -100, 40);
        inputPassword.setLocation(50, firstFieldHeight+spaceBetween);
        this.add(inputPassword);

        JLabel labelRepeatPassword = new JLabel("Powtórz hasło:");
        labelRepeatPassword.setSize(labelRepeatPassword.getPreferredSize());
        labelRepeatPassword.setLocation(50, firstLabelHeight+(2*spaceBetween));
        this.add(labelRepeatPassword);

        inputRepeatPassword = new JPasswordField();
        inputRepeatPassword.setSize(frameWidth -100, 40);
        inputRepeatPassword.setLocation(50, firstFieldHeight+(2*spaceBetween));
        this.add(inputRepeatPassword);

        JLabel labelPesel = new JLabel("Pesel:");
        labelPesel.setSize(labelPesel.getPreferredSize());
        labelPesel.setLocation(50, firstLabelHeight+(3*spaceBetween));
        this.add(labelPesel);

        inputPesel = new JTextField();
        inputPesel.setSize(frameWidth -100, 40);
        inputPesel.setLocation(50, firstFieldHeight+(3*spaceBetween));
        this.add(inputPesel);

        JLabel labelName = new JLabel("Imię:");
        labelName.setSize(labelName.getPreferredSize());
        labelName.setLocation(50, firstLabelHeight+(4*spaceBetween));
        this.add(labelName);

        inputName = new JTextField();
        inputName.setSize(halfFieldWidth, 40);
        inputName.setLocation(50, firstFieldHeight+(4*spaceBetween));
        this.add(inputName);

        JLabel labelSurname = new JLabel("Nazwisko:");
        labelSurname.setSize(labelSurname.getPreferredSize());
        labelSurname.setLocation(halfFieldWidth + 100, firstLabelHeight+(4*spaceBetween));
        this.add(labelSurname);

        inputSurname = new JTextField();
        inputSurname.setSize(halfFieldWidth, 40);
        inputSurname.setLocation(halfFieldWidth + 100, firstFieldHeight+(4*spaceBetween));
        this.add(inputSurname);

        JLabel labelCity = new JLabel("Miasto:");
        labelCity.setSize(labelCity.getPreferredSize());
        labelCity.setLocation(50, firstLabelHeight+(5*spaceBetween));
        this.add(labelCity);

        inputCity = new JTextField();
        inputCity.setSize(halfFieldWidth, 40);
        inputCity.setLocation(50, firstFieldHeight+(5*spaceBetween));
        this.add(inputCity);

        JLabel labelStreetAndNo = new JLabel("Ulica i numer budynku:");
        labelStreetAndNo.setSize(labelStreetAndNo.getPreferredSize());
        labelStreetAndNo.setLocation(halfFieldWidth + 100, firstLabelHeight+(5*spaceBetween));
        this.add(labelStreetAndNo);

        inputStreetAndNo = new JTextField();
        inputStreetAndNo.setSize(halfFieldWidth, 40);
        inputStreetAndNo.setLocation(halfFieldWidth + 100, firstFieldHeight+(5*spaceBetween));
        this.add(inputStreetAndNo);

        JLabel labelPostCode = new JLabel("Kod pocztowy:");
        labelPostCode.setSize(labelPostCode.getPreferredSize());
        labelPostCode.setLocation(50, firstLabelHeight+(6*spaceBetween));
        this.add(labelPostCode);

        inputPostCode = new JTextField();
        inputPostCode.setSize(halfFieldWidth, 40);
        inputPostCode.setLocation(50, firstFieldHeight+(6*spaceBetween));
        this.add(inputPostCode);

        JLabel labelPhoneNo = new JLabel("Numer telefonu:");
        labelPhoneNo.setSize(labelPhoneNo.getPreferredSize());
        labelPhoneNo.setLocation(halfFieldWidth + 100, firstLabelHeight+(6*spaceBetween));
        this.add(labelPhoneNo);

        inputPhoneNo = new JTextField();
        inputPhoneNo.setSize(halfFieldWidth, 40);
        inputPhoneNo.setLocation(halfFieldWidth + 100, firstFieldHeight+(6*spaceBetween));
        this.add(inputPhoneNo);
    }

    public void setButtonSignUp(ActionListener actionListener){

        buttonSignUp = new JButton("Zarejestruj");
        buttonSignUp.setSize(200,50);
        buttonSignUp.setLocation(frameWidth - 250,firstFieldHeight+(6*spaceBetween)+100);
        buttonSignUp.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(buttonSignUp);
        this.add(buttonSignUp);
    }

    public void setButtonGoToLogIn(ActionListener actionListener){

        buttonGoToLogin = new JButton("Logowanie");
        buttonGoToLogin.setFocusPainted(false);
        buttonGoToLogin.setContentAreaFilled(false);
        buttonGoToLogin.setMargin(new Insets(0, 0, 0, 0));
        buttonGoToLogin.setBorderPainted(false);
        buttonGoToLogin.setOpaque(false);
        buttonGoToLogin.setSize(buttonGoToLogin.getPreferredSize());
        buttonGoToLogin.setLocation(50, firstFieldHeight+(6*spaceBetween)+120);
        buttonGoToLogin.setForeground(Color.blue);
        buttonGoToLogin.addActionListener(actionListener);
        this.add(buttonGoToLogin);
    }

    public void setErrorLabel(String errorMessage){
        JLabel errorLabel = new JLabel(errorMessage);
        errorLabel.setSize(errorLabel.getPreferredSize());
        errorLabel.setLocation(50, firstLabelHeight+(6*spaceBetween)+70);
        errorLabel.setForeground(Color.red);
        this.add(errorLabel);
    }
}