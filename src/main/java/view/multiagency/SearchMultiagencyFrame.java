package view.multiagency;

import view.mainviews.ClientMainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchMultiagencyFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JScrollPane scrollPane;
    private JTable multiagencyTable;
    private JLabel searchByCityLabel;
    private JTextField inputSearchByCity;

    private JButton searchButton;

    private DefaultTableModel model;

    public SearchMultiagencyFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);

        this.createClientMenu();

        searchByCityLabel = new JLabel("Wpisz swoje miasto");
        searchByCityLabel.setSize(searchByCityLabel.getPreferredSize());
        searchByCityLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05)-20);
        this.add(searchByCityLabel);

        inputSearchByCity = new JTextField();
        inputSearchByCity.setSize((int) (frameWidth * 0.7), 40);
        inputSearchByCity.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05));
        this.add(inputSearchByCity);

        searchButton = new JButton("Szukaj");
        searchButton.setSize((int) (frameWidth*0.15), 40);
        searchButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.05));
        this.getRootPane().setDefaultButton(searchButton);
        this.add(searchButton);

        multiagencyTable = new JTable();
        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("LP");
        model.addColumn("Nazwa agencji");
        model.addColumn("Miasto");
        model.addColumn("Ulica i numer");
        multiagencyTable = new JTable(model);
        scrollPane = new JScrollPane(multiagencyTable);
        scrollPane.setSize((int) (frameWidth * 0.7), (int) (frameHeight * 0.7));
        scrollPane.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15));
        this.add(scrollPane);

        if(ClientMainFrame.bigText){
            setBiggerMenuSize();

            searchByCityLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
            searchByCityLabel.setSize(searchByCityLabel.getPreferredSize());

            inputSearchByCity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
            searchButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

            multiagencyTable.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        }


//        MultiagenciesPanel multiagenciesPanel = new MultiagenciesPanel();
//
//            multiagenciesPanel.setVisible(true);
//
//        this.add(multiagenciesPanel);
    }

    public String getInputSearchByCity() {
        return inputSearchByCity.getText();
    }

    public void setSearchByCityButton(ActionListener actionListener){

        searchButton.addActionListener(actionListener);

    }

    public void addColumnToMultiagencyTable(Object[] multiagencies) {

        model.addRow(multiagencies);
    }

    public void restartRowCount()
    {
        model.setRowCount(0);
    }

}
