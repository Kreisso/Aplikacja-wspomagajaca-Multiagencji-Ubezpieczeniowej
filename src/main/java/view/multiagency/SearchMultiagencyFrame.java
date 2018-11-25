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

    private JButton searchCityButton;
    private JLabel searchCityLabel;
    private JTextField inputSearchCity;
    private JButton searchButton;

    public SearchMultiagencyFrame(String name) throws HeadlessException {
        super(name);

        this.setLocation(0, 0);
        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);

        searchCityLabel = new JLabel("Wpisz swoje miasto");
        searchCityLabel.setSize(searchCityLabel.getPreferredSize());
        searchCityLabel.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05)-20);
        this.add(searchCityLabel);

        inputSearchCity = new JTextField();
        inputSearchCity.setSize((int) (frameWidth * 0.7), 40);
        inputSearchCity.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.05));
        this.add(inputSearchCity);

        searchCityButton = new JButton("Szukaj");
        searchCityButton.setSize((int) (frameHeight * 0.25), 40);
        searchCityButton.setLocation((int) (frameWidth * 0.8), (int) (frameHeight * 0.05));
        this.add(searchCityButton);
    }

    public void setSearchCityButton(ActionListener actionListener){

        searchButton = new JButton("Szukaj");
        searchButton.setSize((int) (frameHeight*0.25), 40);
        searchButton.setLocation((int) (frameWidth*0.8),  (int) (frameHeight*0.05));
        searchButton.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(searchButton);
        this.add(searchButton);
    }
}
