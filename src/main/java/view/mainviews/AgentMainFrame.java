package view.mainviews;

import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class AgentMainFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JTable policyTable;
    private JScrollPane scrollPane;
    private JLabel messageSenderLabel;
    private JTextPane messageTextPane;
    private JButton clearButton;
    private JCheckBox biggerTextCheckBox;

    private DefaultTableModel model;


    public AgentMainFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        this.createAgentMenu();

        policyTable = new JTable();

        model = new DefaultTableModel();
        model.addColumn("LP");
        model.addColumn("Numer polisy");
        model.addColumn("UKK");
        model.addColumn("Rodzaj polisy");
        model.addColumn("Data rozpoczęcia");
        model.addColumn("Data zakończenia");
        model.addColumn("Status");
        policyTable = new JTable(model);
        scrollPane = new JScrollPane(policyTable);
        scrollPane.setLocation(10, 10);
        scrollPane.setSize(frameWidth-20, frameHeight-100);
        this.add(scrollPane);


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



    public void setMessageTextPane(String message){
        messageTextPane.setText(message);
    }

}
