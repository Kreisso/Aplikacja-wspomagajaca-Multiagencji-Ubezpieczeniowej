package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

public class EditDataFrame extends view.Frame{
    private JCheckBox checkBoxNazwisko;
    private JCheckBox checkBoxMiasto;
    private JCheckBox checkBoxStreet;

    public JCheckBox getCheckBoxNazwisko() {
        return checkBoxNazwisko;
    }

    public void setCheckBoxNazwisko(JCheckBox checkBoxNazwisko) {
        this.checkBoxNazwisko = checkBoxNazwisko;
    }

    public JCheckBox getCheckBoxMiasto() {
        return checkBoxMiasto;
    }

    public void setCheckBoxMiasto(JCheckBox checkBoxMiasto) {
        this.checkBoxMiasto = checkBoxMiasto;
    }

    public JCheckBox getCheckBoxStreet() {
        return checkBoxStreet;
    }

    public void setCheckBoxStreet(JCheckBox checkBoxStreet) {
        this.checkBoxStreet = checkBoxStreet;
    }

    public JCheckBox getCheckBoxPostCode() {
        return checkBoxPostCode;
    }

    public void setCheckBoxPostCode(JCheckBox checkBoxPostCode) {
        this.checkBoxPostCode = checkBoxPostCode;
    }

    public JCheckBox getCheckBoxImie() {
        return checkBoxImie;
    }

    public void setCheckBoxImie(JCheckBox checkBoxImie) {
        this.checkBoxImie = checkBoxImie;
    }

    public JCheckBox getCheckBoxPhone() {
        return checkBoxPhone;
    }

    public void setCheckBoxPhone(JCheckBox checkBoxPhone) {
        this.checkBoxPhone = checkBoxPhone;
    }

    private JCheckBox checkBoxPostCode;
    private JCheckBox checkBoxImie;
    private JCheckBox checkBoxPhone;
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

    List<JCheckBox> checkboxes;

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


        checkBoxImie = new JCheckBox("Imię:");
        checkBoxImie.setSize(checkBoxImie.getPreferredSize());
        checkBoxImie.setLocation(30, firstLabelHeight);
        checkBoxImie.addItemListener(new ItemListener(){
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
        this.add(checkBoxImie);

        checkBoxNazwisko = new JCheckBox("Nazwisko:");
        checkBoxNazwisko.setSize(checkBoxNazwisko.getPreferredSize());
        checkBoxNazwisko.setLocation(halfFieldWidth + 100, firstLabelHeight);
        checkBoxNazwisko.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    inputSurname.setEnabled(true);
                    inputSurname.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    inputSurname.setEnabled(false);
                    inputSurname.setText("");
                }
                validate();
                repaint();
            }
        });
        this.add(checkBoxNazwisko);

        checkBoxMiasto = new JCheckBox("Miasto:");
        checkBoxMiasto.setSize(checkBoxMiasto.getPreferredSize());
        checkBoxMiasto.setLocation(30, firstLabelHeight+ spaceBetween);
        checkBoxMiasto.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    inputCity.setEnabled(true);
                    inputCity.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    inputCity.setEnabled(false);
                    inputCity.setText("");
                }
                validate();
                repaint();
            }
        });
        this.add(checkBoxMiasto);


        checkBoxStreet = new JCheckBox("Ulica i numer budynku:");
        checkBoxStreet.setSize(checkBoxStreet.getPreferredSize());
        checkBoxStreet.setLocation(halfFieldWidth + 100, firstLabelHeight+spaceBetween);
        checkBoxStreet.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    inputStreetAndNo.setEnabled(true);
                    inputStreetAndNo.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    inputStreetAndNo.setEnabled(false);
                    inputStreetAndNo.setText("");
                }
                validate();
                repaint();
            }
        });
        this.add(checkBoxStreet);

        checkBoxPostCode = new JCheckBox("Kod pocztowy:");
        checkBoxPostCode.setSize(checkBoxPostCode.getPreferredSize());
        checkBoxPostCode.setLocation(30, firstLabelHeight+(2*spaceBetween));
        checkBoxPostCode.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    inputPostCode.setEnabled(true);
                    inputPostCode.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    inputPostCode.setEnabled(false);
                    inputPostCode.setText("");
                }
                validate();
                repaint();
            }
        });
        this.add(checkBoxPostCode);

        checkBoxPhone = new JCheckBox("Numer telefonu:");
        checkBoxPhone.setSize(checkBoxPhone.getPreferredSize());
        checkBoxPhone.setLocation(halfFieldWidth + 100, firstLabelHeight+(2*spaceBetween));
        checkBoxPhone.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    inputPhoneNo.setEnabled(true);
                    inputPhoneNo.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    inputPhoneNo.setEnabled(false);
                    inputPhoneNo.setText("");
                }
                validate();
                repaint();
            }
        });
        this.add(checkBoxPhone);




        checkboxes = new LinkedList();
        checkboxes.add(checkBoxImie);
        checkboxes.add(checkBoxNazwisko);
        checkboxes.add(checkBoxMiasto);
        checkboxes.add(checkBoxStreet);
        checkboxes.add(checkBoxPostCode);
        checkboxes.add(checkBoxPhone);

        inputSurname = new JTextField();
        inputSurname.setSize(halfFieldWidth, 40);
        inputSurname.setLocation(halfFieldWidth + 100, firstFieldHeight);
        inputSurname.setEnabled(false);
        this.add(inputSurname);


        inputCity = new JTextField();
        inputCity.setSize(halfFieldWidth, 40);
        inputCity.setLocation(50, firstFieldHeight+spaceBetween);
        inputCity.setEnabled(false);
        this.add(inputCity);


        inputStreetAndNo = new JTextField();
        inputStreetAndNo.setSize(halfFieldWidth, 40);
        inputStreetAndNo.setLocation(halfFieldWidth + 100, firstFieldHeight+spaceBetween);
        inputStreetAndNo.setEnabled(false);
        this.add(inputStreetAndNo);


        inputPostCode = new JTextField();
        inputPostCode.setSize(halfFieldWidth, 40);
        inputPostCode.setLocation(50, firstFieldHeight+(2*spaceBetween));
        inputPostCode.setEnabled(false);
        this.add(inputPostCode);


        inputPhoneNo = new JTextField();
        inputPhoneNo.setSize(halfFieldWidth, 40);
        inputPhoneNo.setLocation(halfFieldWidth + 100, firstFieldHeight+(2*spaceBetween));
        inputPhoneNo.setEnabled(false);
        this.add(inputPhoneNo);

    }

    public void setSaveButton(ActionListener actionListener){
        saveButton = new JButton("Zapisz");
        saveButton.setSize(200,50);
        saveButton.setLocation(frameWidth - 250,firstFieldHeight+(3*spaceBetween));
        saveButton.addActionListener(actionListener);
        this.getRootPane().setDefaultButton(saveButton);
        this.add(saveButton);
    }

    public void setErrorLabel(String errorMessage){
        JOptionPane.showMessageDialog(this,
                errorMessage,
                "Błędne dane",
                JOptionPane.ERROR_MESSAGE);
    }
}
