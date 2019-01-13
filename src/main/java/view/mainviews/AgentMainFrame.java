package view.mainviews;

import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AgentMainFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JTable policyTable;
    private JScrollPane scrollPane;

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
    }

    public void addColumnToPolicyTable(Object[] policyInfo) {

        model.addRow(policyInfo);
    }

}
