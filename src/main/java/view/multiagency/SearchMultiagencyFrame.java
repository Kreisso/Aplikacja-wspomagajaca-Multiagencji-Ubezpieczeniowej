package view.multiagency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kreisso on 02.11.2018.
 */
public class SearchMultiagencyFrame extends view.Frame {
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frameWidth = screenWidth;
    private int frameHeight = screenHeight;

    private JButton searchByCityButton;
    private JLabel searchByCityLabel;
    private JTextField inputSearchByCity;

    private JButton searchButton;

    public SearchMultiagencyFrame(String name) throws HeadlessException {
        super(name);

        this.setLocation(0, 0);
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

        searchByCityButton = new JButton("Szukaj");
        searchByCityButton.setSize((int) (frameHeight * 0.25), 40);
        searchByCityButton.setLocation((int) (frameWidth * 0.8), (int) (frameHeight * 0.05));
        this.add(searchByCityButton);

        MultiagenciesPanel multiagenciesPanel = new MultiagenciesPanel();

            multiagenciesPanel.setVisible(true);

        this.add(multiagenciesPanel);
    }

    public String getInputSearchByCity() {
        return inputSearchByCity.getText();
    }

    public void setSearchByCityButton(ActionListener actionListener){

        searchButton = new JButton("Szukaj");
        searchButton.setSize((int) (frameHeight*0.25), 40);
        searchButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.05));
        searchButton.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(searchButton);
        this.add(searchButton);
    }

}
