package view.multiagency;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kreisso on 02.11.2018.
 */
public class MultiagencyFrame extends view.Frame {

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frameWidth = screenWidth;
    private int frameHeight = screenHeight;

    private JTable multiagencyTable;
    private JScrollPane scrollPane;


    public MultiagencyFrame(String name) throws HeadlessException {
        super(name);

        this.setLocation(0, 0);
        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);

        multiagencyTable = new JTable();
        String[] columnNames = {"Lp", "Multiagencja"};
        Object[][] multiagencies = {};

        multiagencyTable = new JTable(multiagencies, columnNames);
        multiagencyTable.getColumn("Lp").setWidth(20);
        scrollPane = new JScrollPane(multiagencyTable);
        scrollPane.setSize((int) (frameWidth * 0.7), (int) (frameHeight * 0.7));
        scrollPane.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15));
        this.add(scrollPane);
        this.add(new SearchMultiagencyFrame(""));
    }
}
