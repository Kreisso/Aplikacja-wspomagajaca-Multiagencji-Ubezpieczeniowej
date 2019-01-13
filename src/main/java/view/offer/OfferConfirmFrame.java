package view.offer;

import model.Offer;
import view.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class OfferConfirmFrame extends Frame{
    private int frameWidth = 1000;
    private int frameHeight = 600;
    private double clientDiscount;

    private JPanel newPanel;
    private JLabel labelTitle;
    private JLabel labelClientUkk;
    private JLabel labelPrice;
    private JLabel labelPriceAfterDiscount;
    private JLabel labelHowLong;
    private JComboBox comboBoxHowLong;
    private JButton buttonAccept;

    private Offer offer;

    public OfferConfirmFrame(String name, int ukk, final Offer offer, int ukkToBuyPolice, double clientDiscount){
        super("name");
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        this.createAgentMenu();

        this.offer = offer;
        this.clientDiscount = clientDiscount;

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
        newPanel.setLocation((frameWidth/2)+50, 100);
        this.add(newPanel);

        labelTitle = new JLabel("Dostosuj ofertę");
        labelTitle.setFont(labelTitle.getFont().deriveFont(32f));
        labelTitle.setSize(labelTitle.getPreferredSize());
        labelTitle.setLocation((frameWidth/2)-(labelTitle.getWidth()/2), 40);
        this.add(labelTitle);

        labelClientUkk = new JLabel("Oferta spersonalizowana dla klienta o ukk: "+ukkToBuyPolice);
        labelClientUkk.setSize(labelClientUkk.getPreferredSize());
        labelClientUkk.setLocation(50, 100);
        this.add(labelClientUkk);

        NumberFormat formatter = new DecimalFormat("###.00");
        labelPrice = new JLabel("Cena regularna skłądki: "+formatter.format(offer.getPrice())+ "zł");
        labelPrice.setSize(labelPrice.getPreferredSize());
        labelPrice.setLocation(50, 130);
        this.add(labelPrice);

        labelPriceAfterDiscount = new JLabel("Cena skłądki po zniżce: "+formatter.format(offer.getPrice()*clientDiscount) + "zł");
        labelPriceAfterDiscount.setSize(labelPriceAfterDiscount.getPreferredSize());
        labelPriceAfterDiscount.setLocation(50, 160);
        this.add(labelPriceAfterDiscount);

        labelHowLong = new JLabel("Wybierz czas trwania oferty:");
        labelHowLong.setSize(labelHowLong.getPreferredSize());
        labelHowLong.setLocation(50, 190);
        this.add(labelHowLong);

        comboBoxHowLong = new JComboBox();
        comboBoxHowLong.addItem("1 miesiąc");
        comboBoxHowLong.addItem("3 miesiące");
        comboBoxHowLong.addItem("6 miesięcy");
        comboBoxHowLong.addItem("12 miesięcy");
        comboBoxHowLong.setSize(comboBoxHowLong.getPreferredSize());
        comboBoxHowLong.setLocation(50+labelHowLong.getWidth(), 186);
        this.add(comboBoxHowLong);

        buttonAccept = new JButton("Kup polisę");
        buttonAccept.setSize(buttonAccept.getPreferredSize());
        buttonAccept.setLocation(50, 240);
        this.add(buttonAccept);
    }

    public void addComboBoxHowLongListener(ActionListener actionListener){
        comboBoxHowLong.addActionListener(actionListener);
    }

    public int getComboBoxHowLongSelectedIndex(){
        return comboBoxHowLong.getSelectedIndex();
    }

    public void addButtonAcceptListener(ActionListener acceptListener){
        buttonAccept.addActionListener(acceptListener);
    }

    public void setLabelPriceAfterDiscountDiscount(double addictionalDiscount){
        NumberFormat formatter = new DecimalFormat("###.00");
        labelPriceAfterDiscount.setText("Cena skłądki po zniżce: "+formatter.format((offer.getPrice()*clientDiscount)*addictionalDiscount) + "zł");
        labelPriceAfterDiscount.setSize(labelPriceAfterDiscount.getSize());
    }

    public void showErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.ERROR_MESSAGE);
    }

    public int showSuccessMessage(String message){
        return JOptionPane.showOptionDialog(this,
                message,
                "AWMU",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
    }
}
