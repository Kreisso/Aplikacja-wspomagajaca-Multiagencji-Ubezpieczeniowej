package view.client;

import view.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
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
    private JTextPane inputMessage;
    private JLabel foundClientLabel;
    private JLabel errorMessageLabel;

    private JButton searchButton;
    private JButton moreInfoButton;
    private JButton sendMessageButton;
    private JButton sendMessageFinishButton;
    private DefaultTableModel model;
    private JTable addressTable;
    private JScrollPane scrollPane;



    public SearchClientFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);

        this.createAgentMenu();

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
//        this.getRootPane().setDefaultButton(searchButton);
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

        errorMessageLabel = new JLabel("");
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
        errorMessageLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.4));
        errorMessageLabel.setForeground(Color.red);
        this.add(errorMessageLabel);


        addressTable = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Miasto");
        model.addColumn("Ulica i numer budynku");
        model.addColumn("Kod pocztowy");
        model.addColumn("Numer telefonu");
        model.addColumn("Przypisany Agent");

        addressTable = new JTable(model);
        scrollPane = new JScrollPane(addressTable);
        scrollPane.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15) +100);
        scrollPane.setSize((int) (frameWidth * 0.9), 50);
        scrollPane.setVisible(false);
        this.add(scrollPane);

        inputMessage = new JTextPane();
        inputMessage.setSize((int) (frameWidth * 0.9), 100);
        inputMessage.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15) +250);
        inputMessage.setMargin(new Insets(10,10,10,10));

        inputMessage.setVisible(false);
        this.add(inputMessage);

        sendMessageFinishButton = new JButton("Wyślij");
        sendMessageFinishButton.setSize((int) (frameWidth*0.15), 40);
        sendMessageFinishButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight * 0.15) +360);
        sendMessageFinishButton.setVisible(false);
//        this.getRootPane().setDefaultButton(searchButton);
        this.add(sendMessageFinishButton);

    }

    public String getInputSearchByUkk() {
        return inputSearchByUkk.getText();
    }

    public void setFoundClientLabel(String foundClient) {
        foundClientLabel.setText(foundClient);
        foundClientLabel.setSize(foundClientLabel.getPreferredSize());
    }

    public void setMoreInfoButtonVisibility(boolean isVisible){
        moreInfoButton.setVisible(isVisible);
    }

    public void setSendMessageButtonVisibility(boolean isVisible){
        sendMessageButton.setVisible(isVisible);
    }

    public void setAddressTableVisibility(boolean isVisible){
        scrollPane.setVisible(isVisible);
    }

    public void setInputMessageVisibility(boolean isVisible){
        inputMessage.setVisible(isVisible);
    }
    public void setButtonMessageFinishfVisibility(boolean isVisible){
        sendMessageFinishButton.setVisible(isVisible);
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
    public void setSendMessageFinishButton(ActionListener actionListener){
        sendMessageFinishButton.addActionListener(actionListener);
    }

    public void setErrorMessageLabel(String errorMessage) {
        errorMessageLabel.setText(errorMessage);
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
    }

    public void addColumnToInfoTable(Object[] info) {

        model.setRowCount(0);
        model.addRow(info);
    }

    public void setinputMessage(String s)
    {
        inputMessage.setText(s);
    }
    public String getInputMessage() {
        return inputMessage.getText();
    }
    public void setMessageLabel(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "AWMU",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
