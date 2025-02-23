package controller;

import model.Offer;
import model.Policy;
import model.Server.*;
import model.enums.Status;
import model.enums.Type;
import view.Frame;
import view.loginpanel.LoginFrame;
import view.mainviews.AgentMainFrame;
import view.user.ChangePasswordFrame;
import view.user.EditDataFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class MainAgentController extends Controller{
    private AgentMainFrame view;
    private AgentMain model;
    private Connectivity con;
    private Frame previousView;
    private int agentId;
    private int ukk;

    public MainAgentController(AgentMain model, AgentMainFrame view, Frame previousView, int agentId, int ukk, Connectivity con) {
        this.view = view;
        this.model = model;
        this.con = con;
        this.previousView = previousView;
        this.agentId = agentId;
        this.ukk = ukk;
        /*System.out.println(view);
        System.out.println(ukk);
        System.out.println(agentId);*/
        addAgentMenuActions(view, ukk, agentId);
        getPolices();
    }

    public MainAgentController(AgentMain model, AgentMainFrame view, Frame previousView, int agentId, int ukk) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.agentId = agentId;
        this.ukk = ukk;
        addAgentMenuActions(view, ukk, agentId);
        getPolices();

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

        String sql="select * from policy where agent_id=? order by ending";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(agentId));
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
            view.addColumnToPolicyTable(policy.infoForTableAgent(i));
        }
    }
}
