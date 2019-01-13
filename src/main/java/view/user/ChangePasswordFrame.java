package view.user;

import view.mainviews.ClientMainFrame;

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
    JLabel labelNewPassword;
    JPasswordField inputNewPassword;
    JLabel labelRepeatNewPassword;
    JPasswordField inputRepeatNewPassword;
    JButton changePasswordButton;
    private JLabel errorMessageLabel;

    public ChangePasswordFrame(String name, int ukk) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        if(ukk>0) {
            createClientMenu();
        }
        else {
            createAgentMenu();
        }

        labelNewPassword = new JLabel("Nowe hasło:");
        labelNewPassword.setSize(labelNewPassword.getPreferredSize());
        labelNewPassword.setLocation(300, 130);
        this.add(labelNewPassword);

        inputNewPassword = new JPasswordField();
        inputNewPassword.setSize(400, 40);
        inputNewPassword.setLocation(300, 150);
        this.add(inputNewPassword);

        labelRepeatNewPassword = new JLabel("Powtórz nowe hasło:");
        labelRepeatNewPassword.setSize(labelRepeatNewPassword.getPreferredSize());
        labelRepeatNewPassword.setLocation(300, 220);
        this.add(labelRepeatNewPassword);

        inputRepeatNewPassword = new JPasswordField();
        inputRepeatNewPassword.setSize(400, 40);
        inputRepeatNewPassword.setLocation(300, 240);
        this.add(inputRepeatNewPassword);

        errorMessageLabel = new JLabel("");
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
        errorMessageLabel.setLocation(450, 300);
        errorMessageLabel.setForeground(Color.red);
        this.add(errorMessageLabel);

        changePasswordButton = new JButton("Zmień");

        if(ClientMainFrame.bigText && ukk>0){
            setBiggerMenuSize();
            labelNewPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
            labelNewPassword.setSize(labelNewPassword.getPreferredSize());

            labelRepeatNewPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
            labelRepeatNewPassword.setSize(labelRepeatNewPassword.getPreferredSize());

            changePasswordButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        }
    }

    public void setChangeButton(ActionListener actionListener){
        changePasswordButton.setSize((int) changePasswordButtonWidth, 40);
        changePasswordButton.setLocation((int) ((frameWidth - changePasswordButtonWidth)/2),  (int) (frameHeight*0.6));
        changePasswordButton.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(changePasswordButton);
        this.add(changePasswordButton);
    }

    public char[] getInputNewPassword() {
        return inputNewPassword.getPassword();
    }

    public char[] getInputRepeatNewPassword() {
        return inputRepeatNewPassword.getPassword();
    }

    public void setErrorMessageLabel(String message){
        errorMessageLabel.setText(message);
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
    }
}
