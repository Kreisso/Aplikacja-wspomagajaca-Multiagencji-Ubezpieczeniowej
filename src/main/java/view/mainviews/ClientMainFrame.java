package view.mainviews;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientMainFrame extends view.Frame{
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JTable policyTable;
    private JScrollPane scrollPane;
    private JPanel messagePanel;
    private JLabel messageSenderLabel;
    private JTextPane messageTextPane;
    private JButton clearButton;
    private JCheckBox biggerTextCheckBox;

    private DefaultTableModel model;

    public ClientMainFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        this.createClientMenu();

        policyTable = new JTable();
        //String[] columnNames = {"Lp", "Numer polisy", "Rodzaj polisy", "Status"};
        //Object[][] data = {};
        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("LP");
        model.addColumn("Numer polisy");
        model.addColumn("Rodzaj polisy");
        model.addColumn("Status");
        policyTable = new JTable(model);
        scrollPane = new JScrollPane(policyTable);
        scrollPane.setLocation(10, 10);
        scrollPane.setSize(frameWidth*70/100, frameHeight-100);
        this.add(scrollPane);

        messagePanel = new JPanel(null);
        messagePanel.setBackground(Color.white);
        messagePanel.setLocation(scrollPane.getWidth()+20, 10);
        messagePanel.setSize(frameWidth-(scrollPane.getWidth()+30),frameHeight-100);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        messageSenderLabel = new JLabel("Wiadomości");
        messageSenderLabel.setSize(messageSenderLabel.getPreferredSize());
        messageSenderLabel.setLocation((messagePanel.getWidth()/2)-(messageSenderLabel.getWidth()/2), 5);
        messagePanel.add(messageSenderLabel);
        messageTextPane = new JTextPane();
        messageTextPane.setSize(messagePanel.getWidth()-20, 430);
        messageTextPane.setLocation(10,30);
        messageTextPane.setEditable(false);
        messageTextPane.setBackground(Color.white);
        messagePanel.add(messageTextPane);
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

    public void addColumnToPolicyTable(Object[] policyInfo) {

        model.addRow(policyInfo);
    }

    public void setMessageSenderLabel(String sender){
        messageSenderLabel.setText(sender);
        messageSenderLabel.setSize(messageSenderLabel.getPreferredSize());
        messageSenderLabel.setLocation((messagePanel.getWidth()/2)-(messageSenderLabel.getWidth()/2), 5);
    }

    public void setMessageTextPane(String message){
        messageTextPane.setText(message);
    }

    public void setBiggerTextSize(){
        setBiggerMenuSize();
        policyTable.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        messageSenderLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        messageSenderLabel.setSize(messageSenderLabel.getPreferredSize());
        messageSenderLabel.setLocation((messagePanel.getWidth()/2)-(messageSenderLabel.getWidth()/2), 5);

        messageTextPane.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

        clearButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        clearButton.setSize(clearButton.getPreferredSize());
        clearButton.setLocation((messagePanel.getWidth()/2)-(clearButton.getWidth()/2), messagePanel.getHeight()-clearButton.getHeight());

        biggerTextCheckBox.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        biggerTextCheckBox.setSize(biggerTextCheckBox.getPreferredSize());
    }

    public void setNormalTextSize(){
        setNormalMenuSize();
        policyTable.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

        messageSenderLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        messageSenderLabel.setSize(messageSenderLabel.getPreferredSize());
        messageSenderLabel.setLocation((messagePanel.getWidth()/2)-(messageSenderLabel.getWidth()/2), 5);

        messageTextPane.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

        clearButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        clearButton.setSize(clearButton.getPreferredSize());
        clearButton.setLocation((messagePanel.getWidth()/2)-(clearButton.getWidth()/2), messagePanel.getHeight()-clearButton.getHeight());

        biggerTextCheckBox.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        biggerTextCheckBox.setSize(biggerTextCheckBox.getPreferredSize());
    }
}