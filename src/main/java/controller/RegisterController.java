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

                view.setVisible(false);
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

    private void register()
    {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setErrorMessage(null);
        String err = "";
        String pass = String.valueOf(view.getInputPassword());
        String repass = String.valueOf(view.getInputRepeatPassword());
        if(!pass.equals(repass))
        {
            err += "Hasło jest niepoprawne \n";
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

        String sql="{call addClient(?,?,?,?,?,?,?,?,?)}";
        try{
            con =  new Connectivity();

            if(isAdded(view.getInputLogin(), con))
            {
                err +="Podany login już istnieje \n";



                //todo

            }else if(err.length() < 1){
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
                    view.setVisible(false);
                    previouesView.setVisible(true);

                } else {

                   //TODO  view.setErrorMessageLabel(err);
                }
            }

            if(err.length() > 1)
            {
                setErrorMessage(err);
                System.out.println(getErrorMessage());


            }



        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
            con.close();
        }
        finally {
            con.close();
        }

    }




}
