package controller;

import model.Client;
import model.Offer;
import model.Policy;
import model.Server.Connectivity;
import model.Server.SearchPolicy;
import model.enums.Status;
import model.enums.Type;
import view.Frame;
import view.policy.SearchPolicyFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class SearchPolicyController extends Controller{
    private SearchPolicy model;
    private SearchPolicyFrame view;
    private Connectivity con;
    private Frame previousView;
    private int agentId;
    private int ukk;

    public SearchPolicyController(SearchPolicy model, SearchPolicyFrame view, Frame previousView, int agentId, int ukk) {
        this.model = model;
        this.view = view;
        this.previousView = previousView;
        this.ukk = ukk;
        this.agentId = agentId;
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        setSearchPolicyByIdButton();
        setMoreInfoButton();
        setSavePolicyButton();
    }

    public SearchPolicyController(SearchPolicy model, SearchPolicyFrame view, Frame previousView, int agentId, int ukk, Connectivity con) {
        this(model, view, previousView, ukk, agentId);
        this.con = con;
    }

    String getViewInputSearchById(){
        return view.getInputSearchById();
    }
    String getInputSearchById(){
        return model.getId();
    }
    void setInputSearchById(String id){
        model.setId(id);
    }

    void setSearchPolicyByIdButton(){
        view.setSearchByIdButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.setScrollPaneVisibility(true);
                view.setPolicyTableVisibility(true);
                view.setPolicyTableMoreInfoVisibility(false);
                view.setScrollPaneMoreInfoVisibility(false);
                getPolices();
            }
        });
    }

    void setMoreInfoButton(){
        view.setMoreInfoButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setScrollPaneVisibility(false);
                view.setPolicyTableVisibility(false);
                view.setPolicyTableMoreInfoVisibility(true);
                view.setScrollPaneMoreInfoVisibility(true);
                getPolicesMoreInfo();
            }
        });
    }

    void setSavePolicyButton(){
        view.setSavePolicyButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePolicyToFile();
            }
        });
    }

    private void getPolices()
    {
        view.restartRowCount();
        model.getPolicies().clear();
        setInputSearchById(getViewInputSearchById());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from policy where id=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(getInputSearchById()));
            resultSet = preparedStatement.executeQuery();
            view.setErrorMessageLabel("Brak polisy o podanym id");
            view.setMoreInfoButtonVisibility(false);
            view.setPolicyTableVisibility(false);
            view.setScrollPaneVisibility(false);

            while (resultSet.next())
            {
                view.setErrorMessageLabel("");
                view.setMoreInfoButtonVisibility(true);
                view.setPolicyTableVisibility(true);
                view.setScrollPaneVisibility(true);
                view.setSavePolicyButtonVisibility(true);

                Policy policy = new Policy();
                policy.setAgentId(resultSet.getInt("agent_id"));
                policy.setId(resultSet.getInt("id"));
                policy.setUkk(resultSet.getInt("ukk"));
                policy.setStatus(Status.valueOf(resultSet.getString("status")));
                policy.setBeginning(resultSet.getDate("beginning"));
                policy.setEnding(resultSet.getDate("ending"));
                policy.setOffer(getOffer(resultSet.getInt("offer_id")));
                policy.setClient(getClient(resultSet.getInt("ukk")));

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

    private void getPolicesMoreInfo()
    {
        view.restartRowCountMoreInfo();
        model.getPolicies().clear();
        setInputSearchById(getViewInputSearchById());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from policy where id=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(getInputSearchById()));
            resultSet = preparedStatement.executeQuery();
            view.setErrorMessageLabel("Brak polisy o podanym id");
            view.setMoreInfoButtonVisibility(false);
            view.setPolicyTableVisibility(false);
            view.setScrollPaneVisibility(false);
            view.setSavePolicyButtonVisibility(false);

            while (resultSet.next())
            {
                view.setErrorMessageLabel("");
                view.setMoreInfoButtonVisibility(true);
                view.setPolicyTableVisibility(true);
                view.setScrollPaneVisibility(true);
                view.setSavePolicyButtonVisibility(true);

                Policy policy = new Policy();
                policy.setAgentId(resultSet.getInt("agent_id"));
                policy.setId(resultSet.getInt("id"));
                policy.setUkk(resultSet.getInt("ukk"));
                policy.setStatus(Status.valueOf(resultSet.getString("status")));
                policy.setBeginning(resultSet.getDate("beginning"));
                policy.setEnding(resultSet.getDate("ending"));
                policy.setOffer(getOffer(resultSet.getInt("offer_id")));
                policy.setClient(getClient(resultSet.getInt("ukk")));
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
            this.addColumnsToPolicyTableMoreInfo();
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
                offer.setId(resultSet.getInt("id"));
                offer.setCompanyName(resultSet.getString("company_name"));
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

    private Client getClient(int clientUkk){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Client client = new Client();

        String sql="select * from client where ukk=?";
        try{
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(clientUkk));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
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
            return client;
        }

    }


    public void addColumnsToPolicyTable(){
        List<Policy> policyList = model.getPolicies();
        Iterator it = policyList.iterator();
        int i = 0;
        while(it.hasNext()){
            i++;
            Policy policy = (Policy) it.next();
            view.addColumnToPolicyTable(policy.infoForTableToSearchPolicy(i));
        }
    }

    public void addColumnsToPolicyTableMoreInfo(){
        List<Policy> policyList = model.getPolicies();
        Iterator it = policyList.iterator();
        int i = 0;
        while(it.hasNext()){
            i++;
            Policy policy = (Policy) it.next();
            view.addColumnToPolicyTableMoreInfo(policy.infoForTableToSearchPolicyMoreInfo(i));
        }
    }

    public void savePolicyToFile(){
        new PDFWriter(getInputSearchById(), model.getPolicies());
    }
}
