package view.multiagency;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kreisso on 02.11.2018.
 */
public class MultiagenciesPanel extends JPanel {

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frameWidth = screenWidth;
    private int frameHeight = screenHeight;

    private JTable multiagencyTable;
    private JScrollPane scrollPane;




    public MultiagenciesPanel() throws HeadlessException {
        super();

        this.setLocation(0, 0);
        this.setSize(frameWidth, frameHeight);
        this.setLayout(null);
        this.setVisible(false);

        multiagencyTable = new JTable();
        String[] columnNames = {"Lp", "Multiagencja"};
        Object[][] multiagencies = {};

        multiagencyTable = new JTable(multiagencies, columnNames);
        multiagencyTable.getColumn("Lp").setWidth(20);
        scrollPane = new JScrollPane(multiagencyTable);
        scrollPane.setSize((int) (frameWidth * 0.7), (int) (frameHeight * 0.7));
        scrollPane.setLocation((int) (frameWidth * 0.05), (int) (frameHeight * 0.15));
        this.add(scrollPane);
    }
}
