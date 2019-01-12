package controller;

import model.Multiagency;
import model.Offer;
import model.Server.Connectivity;
import view.offer.SearchOfferFrame;
import view.policy.SearchPolicyFrame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SearchOfferController extends Controller{
    Connectivity con;
    int ukk;
    int agentId;
    SearchOfferFrame view;

    private List offerts;

    public SearchOfferController(int ukk, int agentId){
        this.ukk = ukk;
        this.agentId = agentId;
        view = new SearchOfferFrame("Wyszukaj ofertę", ukk);
        //this.offerts = new LinkedList<Offer>();
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        //getOfferts();
    }

    public SearchOfferController(int ukk, int agentId, Connectivity con){
        this(ukk, agentId);
        this.con = con;
    }

    /*private void getOfferts(){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from offer";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Offer offer = new Offer();
                offer.setName(resultSet.getString("name"));
                offer.setType();
                offer.setPrice(resultSet.getDouble("price"));
                offer.setDescription(resultSet.getString("description"));
                offer.setCompanyName(resultSet.getString("company_name"));
                offerts.add(offer);
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
    }*/
}
