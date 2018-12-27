package view.multiagency;

import view.*;

import java.awt.*;

/**
 * Created by kreisso on 02.11.2018.
 */
public class SearchMultiagencyFrame extends view.Frame {
    public SearchMultiagencyFrame(String name) throws HeadlessException {
        super(name);
<<<<<<< Updated upstream
=======

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
        model = new DefaultTableModel();
        model.addColumn("LP");
        model.addColumn("Nazwa agencji");
        model.addColumn("Miasto");
        model.addColumn("Ulica i numer");
        multiagencyTable = new JTable(model);
        scrollPane = new JScrollPane(multiagencyTable);
        scrollPane.setSize((int) (frameWidth * 0.7), (int) (frameHeight * 0.7));
        scrollPane.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15));
        this.add(scrollPane);


//        MultiagenciesPanel multiagenciesPanel = new MultiagenciesPanel();
//
//            multiagenciesPanel.setVisible(true);
//
//        this.add(multiagenciesPanel);
>>>>>>> Stashed changes
    }
}
