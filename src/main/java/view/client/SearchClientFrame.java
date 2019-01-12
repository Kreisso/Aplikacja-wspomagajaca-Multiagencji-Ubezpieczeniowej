package view.client;

import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class SearchClientFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JLabel searchByUkkLabel;
    private JTextField inputSearchByUkk;
    private JLabel foundClientLabel;

    private JButton searchButton;
    private JButton moreInfoButton;
    private JButton sendMessageButton;

    public SearchClientFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);

        this.createClientMenu();

        searchByUkkLabel = new JLabel("Wpisz ukk klienta");
        searchByUkkLabel.setSize(searchByUkkLabel.getPreferredSize());
        searchByUkkLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05)-20);
        this.add(searchByUkkLabel);

        inputSearchByUkk = new JTextField();
        inputSearchByUkk.setSize((int) (frameWidth * 0.7), 40);
        inputSearchByUkk.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05));
        this.add(inputSearchByUkk);

        searchButton = new JButton("Szukaj");
        searchButton.setSize((int) (frameWidth*0.15), 40);
        searchButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.05));
        this.getRootPane().setDefaultButton(searchButton);
        this.add(searchButton);

        foundClientLabel = new JLabel("");
        foundClientLabel.setSize(foundClientLabel.getPreferredSize());
        foundClientLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.2)+15);
        this.add(foundClientLabel);

        moreInfoButton = new JButton("Więcej informacji");
        moreInfoButton.setSize((int) (frameWidth*0.15), 40);
        moreInfoButton.setLocation((int) (frameWidth*0.4),  (int) (frameHeight*0.2));
        moreInfoButton.setVisible(false);
        this.add(moreInfoButton);

        sendMessageButton = new JButton("Wyślij wiadomość");
        sendMessageButton.setSize((int) (frameWidth*0.15), 40);
        sendMessageButton.setLocation((int) (frameWidth*0.6),  (int) (frameHeight*0.2));
        sendMessageButton.setVisible(false);
        this.add(sendMessageButton);
    }

    public String getInputSearchByUkk() {
        return inputSearchByUkk.getText();
    }

    public void setFoundClientLabel(String foundClient) {
        foundClientLabel.setText(foundClient);
        foundClientLabel.setSize(foundClientLabel.getPreferredSize());
    }

    public void setMoreInfoButtonVisible(){
        moreInfoButton.setVisible(true);
    }

    public void setSendMessageButtonVisible(){
        sendMessageButton.setVisible(true);
    }

    public void setSearchByUkkButton(ActionListener actionListener){
        searchButton.addActionListener(actionListener);
    }
    public void setMoreInfoButton(ActionListener actionListener){
        moreInfoButton.addActionListener(actionListener);
    }
    public void setSendMessageButton(ActionListener actionListener){
        sendMessageButton.addActionListener(actionListener);
    }
}
