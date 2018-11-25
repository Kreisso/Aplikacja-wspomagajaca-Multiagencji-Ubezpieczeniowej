package controller;

import model.Offer;
import model.Policy;
import model.Server.ClientMain;
import model.Server.Connectivity;
import model.enums.Status;
import view.Frame;
import view.mainviews.ClientMainFrame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainClientController {
    private ClientMainFrame view;
    private ClientMain model;
    private Connectivity con;
    private Frame previousView;
    private int ukk;

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk, Connectivity con) {
        this.view = view;
        this.model = model;
        this.con = con;
        this.previousView = previousView;
        this.ukk = ukk;
    }

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.ukk = ukk;
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

        String sql="select * from ofer where id=?";
        try{
            //con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(id));
            resultSet = preparedStatement.executeQuery();
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
        while(it.hasNext()){
            Policy policy = (Policy) it.next();
            view.addColumnToPolicyTable(policy.infoForTable());
        }
    }
}