package view.mainviews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientMainFrame extends view.Frame{
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JTable policyTable;
    private JScrollPane scrollPane;
    private JPanel messagePanel;
    private JLabel messageSenderLabel;
    private JButton clearButton;
    private JCheckBox biggerTextCheckBox;

    public ClientMainFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        this.createClientMenu();

        policyTable = new JTable();
        String[] columnNames = {"Lp", "Numer polisy", "Rodzaj polisy", "Status"};
        Object[][] data = {};
        policyTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(policyTable);
        scrollPane.setLocation(10, 10);
        scrollPane.setSize(frameWidth*70/100, frameHeight-100);
        this.add(scrollPane);

        messagePanel = new JPanel(null);
        messagePanel.setLocation(scrollPane.getWidth()+20, 10);
        messagePanel.setSize(frameWidth-(scrollPane.getWidth()+30),frameHeight-100);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        messageSenderLabel = new JLabel("Brak wiadomości");
        messageSenderLabel.setSize(messageSenderLabel.getPreferredSize());
        messageSenderLabel.setLocation((messagePanel.getWidth()/2)-(messageSenderLabel.getWidth()/2), 5);
        messagePanel.add(messageSenderLabel);
        clearButton = new JButton("Usuń wiadomość");
        clearButton.setSize(clearButton.getPreferredSize());
        clearButton.setLocation((messagePanel.getWidth()/2)-(clearButton.getWidth()/2), messagePanel.getHeight()-clearButton.getHeight());
        messagePanel.add(clearButton);
        this.add(messagePanel);

        biggerTextCheckBox = new JCheckBox("Powiększony tekst");
        biggerTextCheckBox.setSize(biggerTextCheckBox.getPreferredSize());
        biggerTextCheckBox.setLocation(10,scrollPane.getHeight()+20);
        this.add(biggerTextCheckBox);
    }

    public void setClearButtonListener(ActionListener actionListener){
        clearButton.addActionListener(actionListener);
    }

    public void setBiggerTextCheckBoxListener(ActionListener actionListener){
        biggerTextCheckBox.addActionListener(actionListener);
    }

    public boolean getBiggerTextCheckBoxStatus(){
        return biggerTextCheckBox.isSelected();
    }
}