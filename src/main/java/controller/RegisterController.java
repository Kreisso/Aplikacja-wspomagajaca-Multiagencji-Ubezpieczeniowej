package controller;

import model.Server.Connectivity;
import model.Server.Register;
import view.Frame;
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


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String err = "";

        // login
        String login = String.valueOf(view.getInputLogin());
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(login);
            err += "Wpisano niepoprawny login - login może zawierać tylko znaki używane w słowach\n";
        }
        //pass
        String pass = String.valueOf(view.getInputPassword());
        String repass = String.valueOf(view.getInputRepeatPassword());
        matcher = pattern.matcher(pass);
            err += "Wpisane hasła nie są takie same\n";
        }
        //pesel
        String pesel = String.valueOf(view.getInputPesel());
        pattern = Pattern.compile("\\d{11}");
        matcher = pattern.matcher(pesel);
            err += "Wpisano niepoprawny pesel - pesel musi składać się z 11 cyfr\n";
        }
        //name
        String name = String.valueOf(view.getInputName());
        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(name);
            err += "Nie wpisano imienia lub wpisano niepoprawne - imię może składać się tylko z liter alfabetu\n";
        }
        //surname
        String surname = String.valueOf(view.getInputSurname());
        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(surname);
            err += "Nie wpisano nazwiska lub wpisano niepoprawne - nazwisko może składać się tylko z liter alfabetu\n";
        }
        //City
        String city = String.valueOf(view.getInputCity());
        pattern = Pattern.compile("[a-zA-Z]+");
        matcher = pattern.matcher(city);
            err += "Nie wpisano miasta lub wpisano niepoprawne - miasto może składać się tylko z liter alfabetu\n";
        }
        //street
        String street = String.valueOf(view.getInputStreetAndNo());
        pattern = Pattern.compile("[a-zA-Z]+ [a-zA-Z0-9/]+");
        matcher = pattern.matcher(street);
            err += "Nie wpisano ulicy i numeru lub wpisano niepoprawne - miasto może składać się tylko z liter alfabetu, a po spacji musi znaleźć się numer budynku\n";
        }
        //postcode
        pattern = Pattern.compile("\\d{2}-\\d{3}");
        matcher = pattern.matcher(postCode);
            err += "Nie wpisano kodu pocztowego lub wpisano niepoprawny - kod pocztowy musi miec format xx-xxx\n";
        }
        //phoneNumber
        String phoneNumber = String.valueOf(view.getInputPhoneNo());
        pattern = Pattern.compile("\\d{3}[ -]*\\d{3}[ -]*\\d{3}|\\d{2}[ -]*\\d{3}[ -]*\\d{2}[ -]*\\d{2}"); //komorkowy | stacjonarny
        matcher = pattern.matcher(phoneNumber);
            err += "Nie wpisano numeru telefonu lub wpisano niepoprawny - telefon stacjonarny musi zawierać numer kierunkowy\n";
        }

/**
 *  1 Login
 *  2 Pass
 *  3 Pesel
 *  4 Name
 *  5 Surname
 *  6 City
 *  8 PostCode
 *  9 phoneNumber
 *
 *
 */



                }


            }

        }
        {
        }
    }




}
