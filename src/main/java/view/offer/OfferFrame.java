package view.offer;

import model.Offer;
import view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kreisso on 02.11.2018.
 */
public class OfferFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JLabel newText;
    private JButton newButton;
    private JPanel newPanel;

    public OfferFrame(String name, int ukk, final Offer offer) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        if(ukk>0) {
            this.createClientMenu();
        }
        else{
            this.createAgentMenu();
        }

        newPanel = new JPanel() {
            private BufferedImage logoImage;

            @Override
            public void paintComponent(Graphics g) {
                //File imageFile = new File("/Users/RobertTrojan/Desktop/model.png");
                //String filename = "assets/img/logo.png";
                String workingDirectory = System.getProperty("user.dir");
                String path = "assets/img/" + offer.getCompanyName() + ".png";
                File imageFile = new File(workingDirectory, path);
                try {
                    logoImage = ImageIO.read(imageFile);
                } catch (IOException e) {
                    System.err.println("Błąd wczytywania logo");
                }
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(logoImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        newPanel.setBackground(Color.white);
        newPanel.setSize(338, 149);
        newPanel.setLocation((frameWidth/2)-(338/2), 50);
        this.add(newPanel);
    }
}
