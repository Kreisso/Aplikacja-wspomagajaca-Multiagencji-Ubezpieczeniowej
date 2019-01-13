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
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        setChangeButton();
    }

    public ChangePasswordController(ChangePassword model, ChangePasswordFrame view, Frame previousView, int agentId, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.agentId = agentId;
        this.ukk = ukk;
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        setChangeButton();
    }

    public String getNewPassword() {
        return model.getNewPassword();
    }

    public void setNewPassword(String newPassword) {
        model.setNewPassword(newPassword);
    }

    public String getRepeatNewPassword() {
        return model.getRepeatNewPassword();
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        model.setRepeatNewPassword(repeatNewPassword);
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
        setNewPassword(String.valueOf(view.getInputNewPassword()));
        setRepeatNewPassword(String.valueOf(view.getInputRepeatNewPassword()));
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

        if (getNewPassword().equals(getRepeatNewPassword()) && !getNewPassword().isEmpty()) {
            String sql = "UPDATE user join " + userType + " on " + userType + ".login=user.login set user.password=? where " + userType + "." + userId + "=?";
            try {
                con = new Connectivity();
                preparedStatement = con.getConn().prepareStatement(sql);
                preparedStatement.setString(1, getRepeatNewPassword());
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
            view.setErrorMessageLabel("Niepoprawne hasła");
        }
    }
}
