package controller;

import model.Multiagency;
import model.Offer;
import model.Server.Connectivity;
import view.policy.SearchPolicyFrame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SearchOfferController extends Controller{
    Connectivity con;
    int ukk;
    SearchPolicyFrame view;

    private List offerts;

    public SearchOfferController(int ukk){
        this.ukk = ukk;
        view = new SearchPolicyFrame("Wyszukaj ofertę");
        offerts = new LinkedList<Offer>();
        addClientMenuActions(view, ukk);
        //getOfferts();
    }

    public SearchOfferController(int ukk, Connectivity con){
        this(ukk);
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
