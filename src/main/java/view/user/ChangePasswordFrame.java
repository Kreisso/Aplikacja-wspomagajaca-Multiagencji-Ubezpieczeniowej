package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class ChangePasswordFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;
    double changePasswordButtonWidth = frameWidth * 0.25;
    JLabel labelOldPassword;
    JPasswordField inputOldPassword;
    JLabel labelNewPassword;
    JPasswordField inputNewPassword;
    JButton changePasswordButton;
    private JLabel errorMessageLabel;

    public ChangePasswordFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        createClientMenu();

        labelOldPassword = new JLabel("Obecne hasło:");
        labelOldPassword.setSize(labelOldPassword.getPreferredSize());
        labelOldPassword.setLocation(300, 130);
        this.add(labelOldPassword);

        inputOldPassword = new JPasswordField();
        inputOldPassword.setSize(400, 40);
        inputOldPassword.setLocation(300, 150);
        this.add(inputOldPassword);

        labelNewPassword = new JLabel("Nowe hasło:");
        labelNewPassword.setSize(labelNewPassword.getPreferredSize());
        labelNewPassword.setLocation(300, 220);
        this.add(labelNewPassword);

        inputNewPassword = new JPasswordField();
        inputNewPassword.setSize(400, 40);
        inputNewPassword.setLocation(300, 240);
        this.add(inputNewPassword);

        errorMessageLabel = new JLabel("");
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
        errorMessageLabel.setLocation(370, 300);
        errorMessageLabel.setForeground(Color.red);
        this.add(errorMessageLabel);
    }

    public void setChangeButton(ActionListener actionListener){
        changePasswordButton = new JButton("Zmień");
        changePasswordButton.setSize((int) changePasswordButtonWidth, 40);
        changePasswordButton.setLocation((int) ((frameWidth - changePasswordButtonWidth)/2),  (int) (frameHeight*0.6));
        changePasswordButton.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(changePasswordButton);
        this.add(changePasswordButton);
    }

    public char[] getInputOldPassword() {
        return inputOldPassword.getPassword();
    }

    public char[] getInputNewPassword() {
        return inputNewPassword.getPassword();
    }

    public void setErrorMessageLabel(String message){
        errorMessageLabel.setText(message);
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
    }
}
