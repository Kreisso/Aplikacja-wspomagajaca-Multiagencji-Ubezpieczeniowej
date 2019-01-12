package controller;

import model.Offer;
import model.Policy;
import model.Server.*;
import model.enums.Status;
import model.enums.Type;
import view.Frame;
import view.loginpanel.LoginFrame;
import view.mainviews.ClientMainFrame;
import view.multiagency.SearchMultiagencyFrame;
import view.user.ChangePasswordFrame;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class MainClientController extends Controller{
    private ClientMainFrame view;
    private ClientMain model;
    private Connectivity con;
    private Frame previousView;
    private int ukk;

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.ukk = ukk;
        addClientMenuActions(view, ukk);
        getPolices();

        setBiggerTextCheckBox();
    }

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk, Connectivity con) {
        this(model, view, previousView, ukk);
        this.con = con;

        setSearchMultiagencyMenuListener();
        setChangePasswordMyAccount();
        setLogoutMyAccount();
    }

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.ukk = ukk;
        getPolices();
        setSearchMultiagencyMenuListener();
        setChangePasswordMyAccount();
        setLogoutMyAccount();

    }

    private void setMessageSender(String messageSender) {
        model.setMessageSender(messageSender);
    }

    private void setMessage(String message) {
        model.setMessage(message);
    }

    private void setBiggerText(boolean biggerText) {
        model.setBiggerText(biggerText);
    }

    private void addPolicy(Policy policy) {
        model.addPolicy(policy);
    }

    public String getMessageSender() {
        return model.getMessageSender();
    }

    public String getMessage() {
        return model.getMessage();
    }

    public Boolean getBiggerText() {
        return model.getBiggerText();
    }

    public List getTableRows() {
        return model.getPolicies();
    }

    private void getPolices()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from policy where ukk=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(ukk));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Policy policy = new Policy();
                policy.setAgentId(resultSet.getInt("agent_id"));
                policy.setBeginning(resultSet.getDate("beginning"));
                policy.setEnding(resultSet.getDate("ending"));
                policy.setId(resultSet.getInt("id"));
                policy.setUkk(resultSet.getInt("ukk"));
                policy.setStatus(Status.valueOf(resultSet.getString("status")));
                policy.setOffer(getOffer(resultSet.getInt("offer_id")));
                model.addPolicy(policy);
            }

        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            this.addColumnsToPolicyTable();
        }

    }


    private Offer getOffer(int id){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Offer offer = new Offer();

        String sql="select * from offer where id=?";
        try{
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(id));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                System.out.println("OFFFERRRRR");
                System.out.println(resultSet.getString("type"));
                System.out.println(Type.valueOf(resultSet.getString("type")));
                offer.setType(Type.valueOf(resultSet.getString("type")));
                offer.setDescription(resultSet.getString("description"));
                offer.setName(resultSet.getString("name"));
                offer.setPrice(resultSet.getDouble("price"));
            }

        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            return offer;
        }
    }

    public void addColumnsToPolicyTable(){
        List<Policy> policyList = model.getPolicies();
        Iterator it = policyList.iterator();
        int i = 0;
        while(it.hasNext()){
            i++;
            Policy policy = (Policy) it.next();
            view.addColumnToPolicyTable(policy.infoForTable(i));
        }
    }


    private void setBiggerTextCheckBox(){
        view.setBiggerTextCheckBoxListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isClicked = view.getBiggerTextCheckBoxStatus();
                if(isClicked){
                    view.setBiggerTextSize();
                }
                else{
                    view.setNormalTextSize();
                }

    private void setSearchMultiagencyMenuListener()
    {
        view.setSearchMultiagencyMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                System.out.println("1");
                new SearchMultiagencyController(new SearchMultiagency(),
                        new SearchMultiagencyFrame("Wyszukaj multiagencje"),view, con);
                view.setVisible(false);
            }

            public void menuDeselected(MenuEvent e) {
                System.out.println("2");
            }

            public void menuCanceled(MenuEvent e) {
                System.out.println("3");
            }
        });
    }

    private void setChangePasswordMyAccount(){
        view.setChangePasswordMyAccountMenuItemListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordConroller(new ChangePassword(), new ChangePasswordFrame("Zmiana hasła"), view, -1, ukk, con);
                view.setVisible(false);
            }
        });
    }

    private void setLogoutMyAccount(){
        view.setLogutMyAccountMenuItemListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginController(new Login(), new LoginFrame("Logowanie"));
                view.dispose();

            }
        });
    }
}
