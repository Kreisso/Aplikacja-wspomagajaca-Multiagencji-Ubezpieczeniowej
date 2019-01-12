package view.offer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SearchOfferFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private int currentOffsetRight = 20;
    private int elementWidth = 117;
    private int offsetRight = 20;

    private int currentOffsetTop = 20;
    private int elementHeight = 70;
    private int textHeight = 26;
    private int offsetTop = 60;

    private int howManyCompanyIsAdded = 0;

    private JLabel newText;
    private JButton newButton;
    private JPanel newPanel;

    public SearchOfferFrame(String name, int ukk) throws HeadlessException {
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
    }

    public void addCompanyToView(final String companyName, String name, String offerDescription, ActionListener buttonListener){
        newPanel = new JPanel() {
            private BufferedImage logoImage;

            @Override
            public void paintComponent(Graphics g) {
                //File imageFile = new File("/Users/RobertTrojan/Desktop/model.png");
                //String filename = "assets/img/logo.png";
                String workingDirectory = System.getProperty("user.dir");
                String path = "assets/img/" + companyName + ".png";
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
        newPanel.setSize(elementWidth, elementHeight);
        newPanel.setLocation(currentOffsetRight, currentOffsetTop);
        this.add(newPanel);

        newText = new JLabel();
//        newText.setBackground(this.getBackground());
        newText.setText(name);
        newText.setSize(elementWidth, textHeight);
        newText.setLocation(currentOffsetRight,currentOffsetTop+elementHeight+10);
        newText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(newText);

        newButton = new JButton("Zobacz");
        newButton.addActionListener(buttonListener);
        newButton.setSize(newButton.getPreferredSize());
        newButton.setLocation(currentOffsetRight+elementWidth/2-(newButton.getWidth()/2), currentOffsetTop+elementHeight+10+textHeight);
        this.add(newButton);

        if(howManyCompanyIsAdded%7 != 6) {
            currentOffsetRight += elementWidth + offsetRight;
        }
        else{
            currentOffsetRight = 20;
            currentOffsetTop += elementHeight + offsetTop + textHeight;
        }
        howManyCompanyIsAdded++;
    }
}
