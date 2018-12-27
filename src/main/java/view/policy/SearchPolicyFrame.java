package view.policy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kreisso on 02.11.2018.
 */
public class SearchPolicyFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private int currentOffsetRight = 20;
    private int elementWidth = 177;
    private int offsetRight = 20;

    private int currentOffsetTop = 20;
    private int elementHeight = 106;
    private int offsetTop = 40;

    private int howManyCompanyIsAdded = 0;

    private JButton button;

    public SearchPolicyFrame(String name) throws HeadlessException {
        super(name);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        this.createClientMenu();
    }

    public void addCompanyToView(){
        JPanel newPanel = new JPanel() {
            private BufferedImage logoImage;

            @Override
            public void paintComponent(Graphics g) {
                //File imageFile = new File("/Users/RobertTrojan/Desktop/model.png");
                String filename = "assets/img/logo.png";
                String workingDirectory = System.getProperty("user.dir");

                File imageFile = new File(workingDirectory, filename);
                System.out.println("Final filepath : " + imageFile.getAbsolutePath());
                try {
                    logoImage = ImageIO.read(imageFile);
                } catch (IOException e) {
                    System.err.println("Błąd wczytywania logo");
                }
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(logoImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        newPanel.setSize(elementWidth, elementHeight);
        newPanel.setLocation(currentOffsetRight, currentOffsetTop);
        if(howManyCompanyIsAdded%5 != 4) {
            currentOffsetRight += elementWidth + offsetRight;
        }
        else{
            currentOffsetRight = 20;
            currentOffsetTop += elementHeight + offsetTop;
        }
        howManyCompanyIsAdded++;
        this.add(newPanel);
    }
}
