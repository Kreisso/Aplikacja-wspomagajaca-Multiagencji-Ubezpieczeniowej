package view.policy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class SearchPolicyFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JScrollPane scrollPane;
    private JScrollPane scrollPaneMoreInfo;
    private JLabel searchByIdLabel;
    private JTextField inputSearchById;
    private JTable policyTable;
    private JTable policyTableMoreInfo;
    private JLabel errorMessageLabel;

    private JButton searchButton;
    private JButton moreInfoButton;
    private JButton savePolicyButton;

    private DefaultTableModel model;
    private DefaultTableModel modelMoreInfo;

    public SearchPolicyFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);

        this.createAgentMenu();

        searchByIdLabel = new JLabel("Wpisz ID polisy");
        searchByIdLabel.setSize(searchByIdLabel.getPreferredSize());
        searchByIdLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05)-20);
        this.add(searchByIdLabel);

        inputSearchById = new JTextField();
        inputSearchById.setSize((int) (frameWidth * 0.7), 40);
        inputSearchById.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05));
        this.add(inputSearchById);

        searchButton = new JButton("Szukaj");
        searchButton.setSize((int) (frameWidth*0.15), 40);
        searchButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.05));
        this.getRootPane().setDefaultButton(searchButton);
        this.add(searchButton);

        moreInfoButton = new JButton("Więcej informacji");
        moreInfoButton.setSize((int) (frameWidth*0.15), 40);
        moreInfoButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.2));
        moreInfoButton.setVisible(false);
        this.add(moreInfoButton);

        savePolicyButton = new JButton("Zapisz do pliku");
        savePolicyButton.setSize((int) (frameWidth*0.15), 40);
        savePolicyButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.2)+60);
        savePolicyButton.setVisible(false);
        this.add(savePolicyButton);

        errorMessageLabel = new JLabel("");
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
        errorMessageLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15));
        errorMessageLabel.setForeground(Color.red);
        this.add(errorMessageLabel);

        setPolicyTable();
        setPolicyTableMoreInfo();
    }

    private void setPolicyTable() {
        policyTable = new JTable();
        model = new DefaultTableModel();
        model.addColumn("LP");
        model.addColumn("Numer polisy");
        model.addColumn("UKK");
        model.addColumn("Rodzaj polisy");
        model.addColumn("Status");
        policyTable = new JTable(model);
        scrollPane = new JScrollPane(policyTable);
        scrollPane.setLocation(50, (int) (frameHeight*0.2));
        scrollPane.setSize((int) (frameWidth*0.7), 100);
        policyTable.setVisible(false);
        scrollPane.setVisible(false);
        this.add(scrollPane);
    }

    private void setPolicyTableMoreInfo() {
        policyTableMoreInfo = new JTable();
        modelMoreInfo = new DefaultTableModel();
        modelMoreInfo.addColumn("LP");
        modelMoreInfo.addColumn("Numer polisy");
        modelMoreInfo.addColumn("UKK");
        modelMoreInfo.addColumn("Rodzaj polisy");
        modelMoreInfo.addColumn("Status");
        modelMoreInfo.addColumn("ID agenta");
        modelMoreInfo.addColumn("ID oferty");
        modelMoreInfo.addColumn("Data rozpoczęcia");
        modelMoreInfo.addColumn("Data zakończenia");
        modelMoreInfo.addColumn("Cena");
        policyTableMoreInfo = new JTable(modelMoreInfo);
        scrollPaneMoreInfo = new JScrollPane(policyTableMoreInfo);
        scrollPaneMoreInfo.setLocation(50, (int) (frameHeight*0.5));
        scrollPaneMoreInfo.setSize((int) (frameWidth*0.7), 100);
        policyTableMoreInfo.setVisible(false);
        scrollPaneMoreInfo.setVisible(false);
        this.add(scrollPaneMoreInfo);
    }

    public String getInputSearchById() {
        return inputSearchById.getText();
    }
    public void setMoreInfoButtonVisibility(boolean isVisible){
        moreInfoButton.setVisible(isVisible);
    }
    public void setSavePolicyButtonVisibility(boolean isVisible){
        savePolicyButton.setVisible(isVisible);
    }
    public void setPolicyTableVisibility(boolean isVisible){
        policyTable.setVisible(isVisible);
    }
    public void setScrollPaneVisibility(boolean isVisible){
        scrollPane.setVisible(isVisible);
    }

    public void setPolicyTableMoreInfoVisibility(boolean isVisible){
        policyTableMoreInfo.setVisible(isVisible);
    }
    public void setScrollPaneMoreInfoVisibility(boolean isVisible){
        scrollPaneMoreInfo.setVisible(isVisible);
    }
    public void setSearchByIdButton(ActionListener actionListener){
        searchButton.addActionListener(actionListener);
    }
    public void setMoreInfoButton(ActionListener actionListener){
        moreInfoButton.addActionListener(actionListener);
    }
    public void setSavePolicyButton(ActionListener actionListener){
        savePolicyButton.addActionListener(actionListener);
    }
    public void setErrorMessageLabel(String errorMessage) {
        errorMessageLabel.setText(errorMessage);
        errorMessageLabel.setSize(errorMessageLabel.getPreferredSize());
    }
    public void addColumnToPolicyTable(Object[] polices) {
        model.addRow(polices);
    }
    public void addColumnToPolicyTableMoreInfo(Object[] polices) {
        modelMoreInfo.addRow(polices);
    }

    public void restartRowCount()
    {
        model.setRowCount(0);
    }
    public void restartRowCountMoreInfo()
    {
        modelMoreInfo.setRowCount(0);
    }
}
