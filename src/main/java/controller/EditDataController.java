package controller;

import model.Server.Connectivity;
import model.Server.EditData;
import view.Frame;
import view.user.EditDataFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditDataController {
    private EditData model;
    private EditDataFrame view;
    private Connectivity con;
    private Frame previouesView;
    private int ukk;

    public EditDataController(EditData editData, EditDataFrame editDataFrame, int ukk, Frame previouesView) {
        this.model = editData;
        this.view = editDataFrame;
        this.ukk = ukk;
        this.previouesView = previouesView;
        setViewButtonSave();
    }

    public EditDataController(EditData editData, EditDataFrame editDataFrame, int ukk, Frame previouesView, Connectivity con) {
        this.model = editData;
        this.view = editDataFrame;
        this.con = con;
        this.ukk = ukk;
        this.previouesView = previouesView;
        setViewButtonSave();
    }

    public String getName() {
        return model.getName();
    }

    public void setName(String name) {
        model.setName(name);
    }

    public String getSurname() {
        return model.getSurname();
    }

    public void setSurname(String surname) {
        model.setSurname(surname);
    }

    public String getCity() {
        return model.getCity();
    }

    public void setCity(String city) {
        model.setCity(city);
    }

    public String getStreetAndNumber() {
        return model.getStreetAndNumber();
    }

    public void setStreetAndNumber(String streetAndNumber) {
        model.setStreetAndNumber(streetAndNumber);
    }

    public String getPostCode() {
        return model.getPostCode();
    }

    public void setPostCode(String postCode) {
        model.setPostCode(postCode);
    }

    public String getPhoneNumber() {
        return model.getPhone();
    }

    public void setPhoneNumber(String phone) {
        model.setPhone(phone);
    }

    private String getErrorMessage()
    {
        return model.getErrorMessage();
    }

    private void setErrorMessage(String errorMessage)
    {
        model.setErrorMessage(errorMessage);
    }

    private void setViewButtonSave()
    {
        view.setSaveButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click save button");
                saveData();
            }
        });
    }

//TODO: uniwersalna metoda do sprawdzania checkboxów
    private void setCheck()
    {
        final JCheckBox checkBox = view.getIsNameEdited();
        view.setIsNameEditedButton(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    checkBox.setEnabled(true);
                    checkBox.setText("");
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    checkBox.setEnabled(false);
                    checkBox.setText("");
                }
                checkBox.validate();
                checkBox.repaint();
            }
        });
    }

    private void saveData() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setErrorMessage("");
        String err = "";

        //name
        String name = String.valueOf(view.getInputName());
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            err += "Nie wpisano imienia lub wpisano niepoprawne - imię może składać się tylko z liter alfabetu\n";
        }
        //surname
        String surname = String.valueOf(view.getInputSurname());
        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(surname);
        if (!matcher.matches()) {
            err += "Nie wpisano nazwiska lub wpisano niepoprawne - nazwisko może składać się tylko z liter alfabetu\n";
        }
        //City
        String city = String.valueOf(view.getInputCity());
        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(city);
        if (!matcher.matches()) {
            err += "Nie wpisano miasta lub wpisano niepoprawne - miasto może składać się tylko z liter alfabetu\n";
        }
        //street
        String street = String.valueOf(view.getInputStreetAndNo());
        pattern = Pattern.compile("[a-zA-Z]+ [a-zA-Z0-9/]+");
        matcher = pattern.matcher(street);
        if (!matcher.matches()) {
            err += "Nie wpisano ulicy i numeru lub wpisano niepoprawne - miasto może składać się tylko z liter alfabetu, a po spacji musi znaleźć się numer budynku\n";
        }
        //postcode
        String postCode = String.valueOf(view.getInputPostCode());
        pattern = Pattern.compile("\\d{2}-\\d{3}");
        matcher = pattern.matcher(postCode);
        if (!matcher.matches()) {
            err += "Nie wpisano kodu pocztowego lub wpisano niepoprawny - kod pocztowy musi miec format xx-xxx\n";
        }
        //phoneNumber
        String phoneNumber = String.valueOf(view.getInputPhoneNo());
        pattern = Pattern.compile("\\d{3}[ -]*\\d{3}[ -]*\\d{3}|\\d{2}[ -]*\\d{3}[ -]*\\d{2}[ -]*\\d{2}"); //komorkowy | stacjonarny
        matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            err += "Nie wpisano numeru telefonu lub wpisano niepoprawny - telefon stacjonarny musi zawierać numer kierunkowy\n";
        }

/**
 *  1 Login
 *  2 Pass
 *  3 Pesel
 *  4 Name
 *  5 Surname
 *  6 City
 *  7 Street and number
 *  8 PostCode
 *  9 phoneNumber
 *
 *
 */
        if (err == "") {
            String sql = "{call addClient(?,?,?,?,?,?,?,?,?)}";
            try {
                con =  new Connectivity();
                preparedStatement = con.getConn().prepareStatement(sql);
                preparedStatement.setString(1,String.valueOf(ukk));
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    setName(view.getInputName());
                    setSurname(view.getInputSurname());
                    setCity(view.getInputCity());
                    setStreetAndNumber(view.getInputStreetAndNo());
                    setPostCode(view.getInputPostCode());
                    setPhoneNumber(view.getInputPhoneNo());

                    preparedStatement = con.getConn().prepareStatement(sql);

                    preparedStatement.setString(1, getName());
                    preparedStatement.setString(2, getSurname());
                    preparedStatement.setString(3, getCity());
                    preparedStatement.setString(4, getStreetAndNumber());
                    preparedStatement.setString(5, getPostCode());
                    preparedStatement.setString(6, getPhoneNumber());

                    resultSet = preparedStatement.executeQuery();
                    //TODO: sprawdzić
//                    if (isConnected(ukk, con)) {
//                        view.dispose();
//                        previouesView.setVisible(true);
//
//                    } else {
//
//                        view.setErrorLabel(err);
//                    }
                }

                if (err.length() > 1) {
                    setErrorMessage(err);
                    System.out.println(getErrorMessage());


                }


            } catch (SQLException ex) {
                System.out.println(ex);
                con.close();
            } catch (Exception e) {
                System.out.println(e);
                con.close();
            } finally {
                con.close();
            }

        }
        else
        {
            view.setErrorLabel(err);
            System.out.println("Wiadomość o błędach \n"+err);
        }
    }

}
