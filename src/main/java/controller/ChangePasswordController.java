package controller;

import model.Server.ChangePassword;
import model.Server.Connectivity;
import view.Frame;
import view.user.ChangePasswordFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordController extends Controller{
    private ChangePassword model;
    private ChangePasswordFrame view;
    private Connectivity con;
    private Frame previousView;
    private int agentId;
    private int ukk;

    public ChangePasswordController(ChangePassword model, ChangePasswordFrame view, Frame previousView, int agentId, int ukk, Connectivity con) {
        this.view = view;
        this.model = model;
        this.con = con;
        this.previousView = previousView;
        this.ukk = ukk;
        this.agentId=agentId;
        addClientMenuActions(view, ukk);
        setChangeButton();
    }

    public ChangePasswordController(ChangePassword model, ChangePasswordFrame view, Frame previousView, int agentId, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.agentId = agentId;
        this.ukk = ukk;
        addClientMenuActions(view, ukk);
        setChangeButton();
    }

    public String getOldPassword() {
        return model.getOldPassword();
    }

    public void setOldPassword(String oldPassword) {
        model.setOldPassword(oldPassword);
    }

    public String getNewPassword() {
        return model.getNewPassword();
    }

    public void setNewPassword(String newPassword) {
        model.setNewPassword(newPassword);
    }

    void setChangeButton(){
        view.setChangeButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePassword(ukk);
            }
        });
    }
    private void changePassword(int ukk){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setOldPassword(String.valueOf(view.getInputOldPassword()));
        setNewPassword(String.valueOf(view.getInputNewPassword()));
        String userType=null;
        String userId=null;
        if(ukk > 0) {
            userType = "client";
            userId = "ukk";
        }
        else{
            userType = "agent";
            userId = "id";
        }
        String sql="select * from user join " + userType + " on " + userType + ".login=user.login where " + userType + "." + userId + "=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            if(ukk>0){
                preparedStatement.setString(1,String.valueOf(ukk));
            }
            else{
                preparedStatement.setString(1,String.valueOf(agentId));
            }
            resultSet = preparedStatement.executeQuery();
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            while (resultSet.next()) {

                if (getOldPassword().equals(resultSet.getString("password")) && !getNewPassword().isEmpty()) {
                    sql = "UPDATE user join " + userType + " on " + userType + ".login=user.login set user.password=? where " + userType + "." + userId + "=?";

                    try {
                        con = new Connectivity();
                        preparedStatement = con.getConn().prepareStatement(sql);
                        preparedStatement.setString(1, getNewPassword());
                        if(ukk>0){
                            preparedStatement.setString(2,String.valueOf(ukk));
                        }
                        else{
                            preparedStatement.setString(2,String.valueOf(agentId));
                        }
                        preparedStatement.executeUpdate();

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    view.dispose();
                    previousView.setVisible(true);
                }
                else{
                    view.setErrorMessageLabel("Błędne obecne hasło lub brak nowego hasła");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void setEditPersonalDataMyAccount(){
//        view.setEditPersonalDataMyAccountMenuItemListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new EditDataController(new EditData(), new EditDataFrame("Edycja danych"), agentId, ukk, view, con);
//            }
//        });
//    }
//
//    private void setLogoutMyAccount(){
//        view.setLogutMyAccountMenuItemListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new LoginController(new Login(), new LoginFrame("Logowanie"));
//                view.dispose();
//            }
//        });
//    }
}
