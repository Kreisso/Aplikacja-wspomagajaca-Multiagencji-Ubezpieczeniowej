package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditDataFrame extends view.Frame{
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frameWidth = 1000;
    private int frameHeight = 600;
    private int halfFieldWidth = (frameWidth - 150) / 2;
    private int firstLabelHeight = 40;
    private int firstFieldHeight = 70;
    private int spaceBetween = 100;
    private JButton saveButton;
    private JTextField inputName;
    private JTextField inputSurname;
    private JTextField inputCity;
    private JTextField inputStreetAndNo;
    private JTextField inputPostCode;
    private JTextField inputPhoneNo;
    private JCheckBox isNameEdited;

    public String getInputName() {
        return inputName.getText();
    }

    public String getInputSurname() {
        return inputSurname.getText();
    }

    public String getInputCity() {
        return inputCity.getText();
    }

    public String getInputStreetAndNo() {
        return inputStreetAndNo.getText();
    }

    public String getInputPostCode() {
        return inputPostCode.getText();
    }

    public String getInputPhoneNo() {
        return inputPhoneNo.getText();
    }

    public JCheckBox getIsNameEdited() {return isNameEdited; }

    public EditDataFrame(String name) throws HeadlessException {
        super(name);

        this.setLocation((screenWidth- frameWidth)/2, (screenHeight- frameHeight)/2);

        this.setSize(frameWidth, frameHeight);

        this.setLayout(null);

        createClientMenu();

        inputName = new JTextField();
        inputName.setSize(halfFieldWidth, 40);
        inputName.setLocation(50, firstFieldHeight);
        inputName.setEnabled(false);
        this.add(inputName);

        isNameEdited = new JCheckBox("Imię:");
        isNameEdited.setSize(isNameEdited.getPreferredSize());
        isNameEdited.setLocation(30, firstLabelHeight);

        isNameEdited.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    inputName.setEnabled(true);
                    inputName.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    inputName.setEnabled(false);
                    inputName.setText("");
                }
                validate();
                repaint();
            }
        });
        this.add(isNameEdited);

        JLabel labelSurname = new JLabel("Nazwisko:");
        labelSurname.setSize(labelSurname.getPreferredSize());
        labelSurname.setLocation(halfFieldWidth + 100, firstLabelHeight);
        this.add(labelSurname);

        inputSurname = new JTextField();
        inputSurname.setSize(halfFieldWidth, 40);
        inputSurname.setLocation(halfFieldWidth + 100, firstFieldHeight);
        inputSurname.setEnabled(false);
        this.add(inputSurname);

        JLabel labelCity = new JLabel("Miasto:");
        labelCity.setSize(labelCity.getPreferredSize());
        labelCity.setLocation(50, firstLabelHeight+spaceBetween);
        this.add(labelCity);

        inputCity = new JTextField();
        inputCity.setSize(halfFieldWidth, 40);
        inputCity.setLocation(50, firstFieldHeight+spaceBetween);
        inputCity.setEnabled(false);
        this.add(inputCity);

        JLabel labelStreetAndNo = new JLabel("Ulica i numer budynku:");
        labelStreetAndNo.setSize(labelStreetAndNo.getPreferredSize());
        labelStreetAndNo.setLocation(halfFieldWidth + 100, firstLabelHeight+spaceBetween);
        this.add(labelStreetAndNo);

        inputStreetAndNo = new JTextField();
        inputStreetAndNo.setSize(halfFieldWidth, 40);
        inputStreetAndNo.setLocation(halfFieldWidth + 100, firstFieldHeight+spaceBetween);
        inputStreetAndNo.setEnabled(false);
        this.add(inputStreetAndNo);

        JLabel labelPostCode = new JLabel("Kod pocztowy:");
        labelPostCode.setSize(labelPostCode.getPreferredSize());
        labelPostCode.setLocation(50, firstLabelHeight+(2*spaceBetween));
        this.add(labelPostCode);

        inputPostCode = new JTextField();
        inputPostCode.setSize(halfFieldWidth, 40);
        inputPostCode.setLocation(50, firstFieldHeight+(2*spaceBetween));
        inputPostCode.setEnabled(false);
        this.add(inputPostCode);

        JLabel labelPhoneNo = new JLabel("Numer telefonu:");
        labelPhoneNo.setSize(labelPhoneNo.getPreferredSize());
        labelPhoneNo.setLocation(halfFieldWidth + 100, firstLabelHeight+(2*spaceBetween));
        this.add(labelPhoneNo);

        inputPhoneNo = new JTextField();
        inputPhoneNo.setSize(halfFieldWidth, 40);
        inputPhoneNo.setLocation(halfFieldWidth + 100, firstFieldHeight+(2*spaceBetween));
        inputPhoneNo.setEnabled(false);
        this.add(inputPhoneNo);

        saveButton = new JButton("Zapisz");
        saveButton.setSize(200,50);
        saveButton.setLocation(frameWidth - 250,firstFieldHeight+(3*spaceBetween));
        this.getRootPane().setDefaultButton(saveButton);
        this.add(saveButton);
    }

    public void setSaveButton(ActionListener actionListener){
        saveButton = new JButton("Zapisz");
        saveButton.setSize(200,50);
        saveButton.setLocation(frameWidth - 250,firstFieldHeight+(3*spaceBetween));
        saveButton.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(saveButton);
        this.add(saveButton);
    }

    public void setIsNameEditedButton(ItemListener itemListener){
        isNameEdited = new JCheckBox("Imię:");
        isNameEdited.setSize(isNameEdited.getPreferredSize());
        isNameEdited.setLocation(30, firstLabelHeight);
        isNameEdited.addItemListener(itemListener);
        this.add(isNameEdited);
    }

    public void setErrorLabel(String errorMessage) {
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.WARNING_MESSAGE);
    }
}
