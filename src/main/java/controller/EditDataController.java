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

public class EditDataController extends Controller{
    private EditData model;
    private EditDataFrame view;
    private Connectivity con;
    private Frame previouesView;
    private int agentId;
    private int ukk;

    public EditDataController(EditData editData, EditDataFrame editDataFrame, int agentId, int ukk, Frame previouesView) {
        this.model = editData;
        this.view = editDataFrame;
        this.agentId = agentId;
        this.ukk = ukk;
        this.previouesView = previouesView;
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        setViewButtonSave();
    }

    public EditDataController(EditData editData, EditDataFrame editDataFrame, int agentId, int ukk, Frame previouesView, Connectivity con) {
        this.model = editData;
        this.view = editDataFrame;
        this.con = con;
        this.agentId = agentId;
        this.ukk = ukk;
        this.previouesView = previouesView;
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        setViewButtonSave();
    }

    public String getName() {
        return model.getName();
    }
    public String getViewName() {
        return view.getInputName();
    }

    public void setName(String name) {
        model.setName(name);
    }

    public String getSurname() {
        return model.getSurname();
    }
    public String getViewSurname() {
        return view.getInputSurname();
    }

    public void setSurname(String surname) {
        model.setSurname(surname);
    }

    public String getCity() {
        return model.getCity();
    }
    public String getViewCity() {
        return view.getInputCity();
    }

    public void setCity(String city) {
        model.setCity(city);
    }

    public String getStreetAndNumber() {
        return model.getStreetAndNumber();
    }
    public String getViewStreetAndNumber() {
        return view.getInputStreetAndNo();
    }

    public void setStreetAndNumber(String streetAndNumber) {
        model.setStreetAndNumber(streetAndNumber);
    }

    public String getPostCode() {
        return model.getPostCode();
    }
    public String getViewPostCode() {
        return view.getInputPostCode();
    }

    public void setPostCode(String postCode) {
        model.setPostCode(postCode);
    }

    public String getPhoneNumber() {
        return model.getPhone();
    }
    public String getViewPhoneNumber() {
        return view.getInputPhoneNo();
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

    private void saveData() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setErrorMessage("");
        String err = "";

        Pattern pattern;
        Matcher matcher;
        if(view.getCheckBoxImie().isSelected()){
            //name
            String name = String.valueOf(view.getInputName());
            pattern = Pattern.compile("[a-zA-Z]+");
            matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                err += "Nie wpisano imienia lub wpisano niepoprawne - imię może składać się tylko z liter alfabetu\n";
            }
        }

        if(view.getCheckBoxNazwisko().isSelected()){
            //surname
            String surname = String.valueOf(view.getInputSurname());
            pattern = Pattern.compile("[a-zA-Z]+");
            matcher = pattern.matcher(surname);
            if (!matcher.matches()) {
                err += "Nie wpisano nazwiska lub wpisano niepoprawne - nazwisko może składać się tylko z liter alfabetu\n";
            }
        }

        if(view.getCheckBoxMiasto().isSelected()){
            //City
            String city = String.valueOf(view.getInputCity());
            pattern = Pattern.compile("^[a-pr-uwy-zA-PR-UWY-qZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+(?:[\\s-][a-zA-Z]+)*$");
            matcher = pattern.matcher(city);
            if (!matcher.matches()) {
                err += "Nie wpisano miasta lub wpisano niepoprawne - miasto może składać się tylko z liter alfabetu\n";
            }
        }

        if(view.getCheckBoxStreet().isSelected()){
            //street
            String street = String.valueOf(view.getInputStreetAndNo());
            pattern = Pattern.compile("^[a-pr-uwy-zA-PR-UWY-qZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+ [a-zA-Z0-9/]+");
            matcher = pattern.matcher(street);
            if (!matcher.matches()) {
                err += "Nie wpisano ulicy i numeru lub wpisano niepoprawne - miasto może składać się tylko z liter alfabetu, a po spacji musi znaleźć się numer budynku\n";
            }
        }

        if(view.getCheckBoxPostCode().isSelected()){
            //postcode
            String postCode = String.valueOf(view.getInputPostCode());
            pattern = Pattern.compile("\\d{2}-\\d{3}");
            matcher = pattern.matcher(postCode);
            if (!matcher.matches()) {
                err += "Nie wpisano kodu pocztowego lub wpisano niepoprawny - kod pocztowy musi miec format xx-xxx\n";
            }
        }

        if(view.getCheckBoxPhone().isSelected()){
            //phoneNumber
            String phoneNumber = String.valueOf(view.getInputPhoneNo());
            pattern = Pattern.compile("\\d{3}[ -]*\\d{3}[ -]*\\d{3}|\\d{2}[ -]*\\d{3}[ -]*\\d{2}[ -]*\\d{2}"); //komorkowy | stacjonarny
            matcher = pattern.matcher(phoneNumber);
            if (!matcher.matches()) {
                err += "Nie wpisano numeru telefonu lub wpisano niepoprawny - telefon stacjonarny musi zawierać numer kierunkowy\n";
            }
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
            String columnName = null;
            String sql;
            if (view.getCheckBoxImie().isSelected()) {
                setName(getViewName());
                columnName = "first_name";
                sql = "UPDATE client set " + columnName + "=? where ukk=?";
                try {
                    con = new Connectivity();
                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, getName());
                    preparedStatement.setString(2, String.valueOf(ukk));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (view.getCheckBoxNazwisko().isSelected()) {
                setSurname(getViewSurname());
                columnName = "last_name";
                sql = "UPDATE client set " + columnName + "=? where ukk=?";
                try {
                    con = new Connectivity();
                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(getSurname()));
                    preparedStatement.setString(2, String.valueOf(ukk));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (view.getCheckBoxMiasto().isSelected()) {
                setCity(getViewCity());
                columnName = "city";
                sql = "UPDATE contact join client on client.contact_id=contact.id set contact." + columnName + "=? where client.ukk=?";
                try {
                    con = new Connectivity();
                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, getCity());
                    preparedStatement.setString(2, String.valueOf(ukk));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (view.getCheckBoxStreet().isSelected()) {
                setStreetAndNumber(getViewStreetAndNumber());
                columnName = "street_building_number";
                sql = "UPDATE contact join client on client.contact_id=contact.id set contact." + columnName + "=? where client.ukk=?";
                try {
                    con = new Connectivity();
                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, getStreetAndNumber());
                    preparedStatement.setString(2, String.valueOf(ukk));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (view.getCheckBoxPostCode().isSelected()) {
                setPostCode(getViewPostCode());
                columnName = "post_code";
                sql = "UPDATE contact join client on client.contact_id=contact.id set contact." + columnName + "=? where client.ukk=?";
                try {
                    con = new Connectivity();
                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, getPostCode());
                    preparedStatement.setString(2, String.valueOf(ukk));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (view.getCheckBoxPhone().isSelected()) {
                setPhoneNumber(getViewPhoneNumber());
                columnName = "phone_number";
                sql = "UPDATE contact join client on client.contact_id=contact.id set contact." + columnName + "=? where client.ukk=?";
                try {
                    con = new Connectivity();
                    preparedStatement = con.getConn().prepareStatement(sql);
                    preparedStatement.setString(1, getPhoneNumber());
                    preparedStatement.setString(2, String.valueOf(ukk));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            view.setInfoLabel("Pomyślnie zedytowano dane");
        }
        else{
            view.setErrorLabel(err);
        }
    }
}
