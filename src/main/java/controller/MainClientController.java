package controller;

import model.Offer;
import model.Policy;
import model.Server.*;
import model.enums.Status;
import model.enums.Type;
import view.Frame;
import view.mainviews.ClientMainFrame;

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
    private int agentId;
    MessageController bigD;

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk, int agentId) {
        this.view = view;
        this.model = model;
        this.previousView = previousView;
        this.ukk = ukk;
        this.agentId = agentId;
        addClientMenuActions(view, ukk, agentId);
        getPolices();


        bigD = new MessageController(view,ukk);
        bigD.start();
        setBiggerTextCheckBox();

        view.setClearButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMessage();

            }
        });
    }

    private void clearMessage() {

        PreparedStatement preparedStatement = null;
        String emptyMessage = "Brak wiadomości";

        String sql="UPDATE client SET message =? WHERE ukk = ?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,emptyMessage);
            preparedStatement.setInt(2, ukk);
            preparedStatement.executeUpdate();
            view.setMessageTextPane(emptyMessage);

        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {

        }
    }

    public MainClientController(ClientMain model, ClientMainFrame view, Frame previousView, int ukk, int agentId, Connectivity con) {
        this(model, view, previousView, ukk, agentId);
        this.con = con;
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
                if (isClicked) {
                    view.setBiggerTextSize();
                    ClientMainFrame.bigText = true;
                } else {
                    view.setNormalTextSize();
                    ClientMainFrame.bigText = false;
                }
            }
        });
    }

}
