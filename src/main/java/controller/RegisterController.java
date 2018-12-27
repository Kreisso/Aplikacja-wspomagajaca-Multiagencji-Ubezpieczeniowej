package controller;

import model.Server.Connectivity;
import model.Server.Register;
import view.Frame;
import view.loginpanel.LoginFrame;
import view.loginpanel.RegisterFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    private Register model;
    private RegisterFrame view;
    private Connectivity con;
    private Frame previouesView;


    public RegisterController(Register register, RegisterFrame registerFrame, Frame previouesView) {
        this.model = register;
        this.view = registerFrame;
        this.previouesView = previouesView;
        setViewButtonSignUp();
        setViewButtonGoToLogin();
    }

    public RegisterController(Register register, RegisterFrame registerFrame,Frame previouesView, Connectivity con) {
        this.model = register;
        this.view = registerFrame;
        this.con = con;
        this.previouesView = previouesView;
        setViewButtonSignUp();
        setViewButtonGoToLogin();
    }

    private String getLogin()
    {
        return model.getLogin();
    }
    private String getPassword()
    {
        return model.getPassword();
    }
    private String getRepeatPassword()
    {
        return model.getRepeatPassword();
    }
    private String getPesel()
    {
        return model.getPesel();
    }
    private String getName()
    {
        return model.getName();
    }
    private String getSurname()
    {
        return model.getSurname();
    }
    private String getCity()
    {
        return model.getCity();
    }
    private String getStreatAndNumber()
    {
        return model.getStreatAndNumber();
    }
    private String getPostCode()
    {
        return model.getPostCode();
    }
     private String getPhoneNumber()
    {
        return model.getPhone();
    }
    private String getErrorMessage()
    {
        return model.getErrorMessage();
    }


    private void setErrorMessage(String errorMessage)
    {
        model.setErrorMessage(errorMessage);
    }
    private void setLogin(String login)
    {
        model.setLogin(login);
    }
    private void setPassword(String password)
    {
        model.setPassword(password);
    }
    private void setRepeatPassword(String repeatPassword)
    {
        model.setRepeatPassword(repeatPassword);
    }
    private void setPesel(String pesel)
    {
        model.setPesel(pesel);
    }
    private void setName(String name)
    {
        model.setName(name);
    }
    private void setSurname(String surname)
    {
        model.setSurname(surname);
    }
    private void setCity(String city)
    {
        model.setCity(city);
    }
    private void setStreetAndNumber(String streetAndNumber)
    {
        model.setStreatAndNumber(streetAndNumber);
    }
    private void setPostCode(String postCode)
    {
        model.setPostCode(postCode);
    }
    private void setPhoneNumber(String phoneNumber)
    {
        model.setPhone(phoneNumber);
    }

    private void setViewButtonSignUp()
    {
        view.setButtonSignUp(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click register button");
                register();
            }
        });
    }

    private void setViewButtonGoToLogin()
    {
        view.setButtonGoToLogIn(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //view.setVisible(false);
                view.dispose();
                previouesView.setVisible(true);


            }
        });
    }

    private boolean isAdded(String login, Connectivity con)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql="select * from user where login=?";


        try {
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return true;
            }
            else
            {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void register() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setErrorMessage("");
        String err = "";

        // login
        String login = String.valueOf(view.getInputLogin());
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            err += "Wpisano niepoprawny login - login może zawierać tylko znaki używane w słowach\n";
        }
        //pass
        String pass = String.valueOf(view.getInputPassword());
        String repass = String.valueOf(view.getInputRepeatPassword());
        pattern = Pattern.compile("[^ ]+");
        matcher = pattern.matcher(pass);
        if (!matcher.matches()) {
            err += "Wpisane hasło jest niepoprawne - hasło nie może zawierać białych znakow\n";
        } else if (!pass.equals(repass)) {
            err += "Wpisane hasła nie są takie same\n";
        }
        //pesel
        String pesel = String.valueOf(view.getInputPesel());
        pattern = Pattern.compile("\\d{11}");
        matcher = pattern.matcher(pesel);
        if (!matcher.matches()) {
            err += "Wpisano niepoprawny pesel - pesel musi składać się z 11 cyfr\n";
        }
        //name
        String name = String.valueOf(view.getInputName());
        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(name);
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
 *  7 Streat and number
 *  8 PostCode
 *  9 phoneNumber
 *
 *
 */
        if (err == "") {
            String sql = "{call addClient(?,?,?,?,?,?,?,?,?)}";
            try {
                con = new Connectivity();

                if (isAdded(view.getInputLogin(), con)) {
                    err += "Podany login już istnieje \n";


                    //todo

                } else if (err.length() < 1) {
                    setLogin(view.getInputLogin());
                    setPassword(String.valueOf(view.getInputPassword()));
                    setPesel(view.getInputPesel());
                    setName(view.getInputName());
                    setSurname(view.getInputSurname());
                    setCity(view.getInputCity());
                    setStreetAndNumber(view.getInputStreetAndNo());
                    setPostCode(view.getInputPostCode());
                    setPhoneNumber(view.getInputPhoneNo());

                    preparedStatement = con.getConn().prepareStatement(sql);

                    preparedStatement.setString(1, getLogin());
                    preparedStatement.setString(2, getPassword());
                    preparedStatement.setString(3, getPesel());
                    preparedStatement.setString(4, getName());
                    preparedStatement.setString(5, getSurname());
                    preparedStatement.setString(6, getCity());
                    preparedStatement.setString(7, getStreatAndNumber());
                    preparedStatement.setString(8, getPostCode());
                    preparedStatement.setString(9, getPhoneNumber());

                    resultSet = preparedStatement.executeQuery();
                    if (isAdded(getLogin(), con)) {
                        view.dispose();
                        previouesView.setVisible(true);

                    } else {

                        view.setErrorLabel(err);
                    }
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
