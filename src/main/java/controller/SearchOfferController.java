package controller;

import model.Offer;
import model.Server.Connectivity;
import model.enums.Type;
import view.offer.SearchOfferFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
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
        this.offerts = new LinkedList<Offer>();
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
        getOfferts();
    }

    public SearchOfferController(int ukk, int agentId, Connectivity con){
        this(ukk, agentId);
        this.con = con;
    }

    private void getOfferts(){
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
                offer.setId(resultSet.getInt("id"));
                offer.setName(resultSet.getString("name"));
                String stringType = resultSet.getString("type");
                Type type;
                if(stringType.equals("OC")){
                    type = Type.OC;
                }
                else{
                    type = Type.AC;
                }
                offer.setType(type);
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
            this.addCompanyToView();
        }
    }

    private void addCompanyToView(){
        Iterator it = offerts.iterator();
        while(it.hasNext()){
            final Offer offer = (Offer) it.next();
            String[] toDisplay = offer.getOfferToDisplay();
            view.addCompanyToView(toDisplay[0], toDisplay[1], toDisplay[2], new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(offer.getName() + " clicked");
                    view.dispose();
                    new OfferController(ukk, agentId, offer);
                }
            });
        }
    }
}
