package view.offer;

import model.Offer;
import view.*;

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
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class OfferFrame extends view.Frame {
    private int frameWidth = 1000;
    private int frameHeight = 600;

    private JPanel newPanel;
    private JLabel labelTitle;
    private JTextPane textPaneDescription;
    private JButton buttonBuy;
    private JTextField inputUKK;
    private JLabel labelUKK;
    private JButton buttonAccept;

    private Offer offer;
    private int ukk;

    public OfferFrame(String name, int ukk, final Offer offer) throws HeadlessException {
        super(name);

        this.offer = offer;
        this.ukk = ukk;

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

        labelTitle = new JLabel(offer.getName());
        labelTitle.setFont(labelTitle.getFont().deriveFont(32f));
        labelTitle.setSize(labelTitle.getPreferredSize());
        labelTitle.setLocation((frameWidth/2)-(labelTitle.getWidth()/2), 20);
        this.add(labelTitle);

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
        newPanel.setSize(338, 149);
        newPanel.setLocation((frameWidth/2)-(338/2), 70);
        this.add(newPanel);


        textPaneDescription = new JTextPane();
        //centre
        StyledDocument doc = textPaneDescription.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        //end centre
        NumberFormat formatter = new DecimalFormat("###.00");
        textPaneDescription.setText(offer.getDescription()+"\n\nTyp: "+offer.getType()+"\nCena: "+formatter.format(offer.getPrice())+"zł\nTowarzystwo ubezpieczeniowe: "+offer.getCompanyName());
        textPaneDescription.setSize(frameWidth-100, 200);
        textPaneDescription.setLocation(50,230);
        textPaneDescription.setBackground(this.getBackground());
        textPaneDescription.setEditable(false);
        this.add(textPaneDescription);

        if(this.ukk<0) {
            buttonBuy = new JButton("Kup");
            buttonBuy.setSize(buttonBuy.getPreferredSize());
            buttonBuy.setLocation(50, 440);
            this.add(buttonBuy);

            labelUKK = new JLabel("Wpisz ukk");
            this.add(labelUKK);

            inputUKK = new JTextField();
            this.add(inputUKK);

            buttonAccept = new JButton("ok");
            this.add(buttonAccept);
        }
    }

    public void addButtonBuyListener(ActionListener actionListener){
        if(this.ukk<0) {
            buttonBuy.addActionListener(actionListener);
        }
    }

    public void createUKKInput(){
        buttonBuy.setEnabled(false);

        labelUKK.setSize(labelUKK.getPreferredSize());
        labelUKK.setLocation(150, 446);

        inputUKK.setSize(100,30);
        inputUKK.setLocation(215, 440);

        buttonAccept.setSize(buttonBuy.getPreferredSize());
        buttonAccept.setLocation(315, 440);
        this.getRootPane().setDefaultButton(buttonAccept);
    }

    public void addButtonAcceptListener(ActionListener actionListener){
        buttonAccept.addActionListener(actionListener);
    }

    public String getInputUKK() {
        return inputUKK.getText();
    }

    public int showSuccessMessage(String message){
        return JOptionPane.showOptionDialog(this,
                message,
                "Polisa została zakupiona",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
    }

    public void showErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.ERROR_MESSAGE);
    }
}
