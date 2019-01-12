package controller;

import model.Contact;
import model.Multiagency;
import model.Server.Connectivity;
import model.Server.EditData;
import model.Server.Login;
import model.Server.SearchMultiagency;
import view.Frame;
import view.loginpanel.LoginFrame;
import view.multiagency.SearchMultiagencyFrame;
import view.user.EditDataFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class SearchMultiagencyController extends Controller{
    private SearchMultiagency model;
    private SearchMultiagencyFrame view;
    private Connectivity con;
    private Frame previousView;
    private int ukk;

    public SearchMultiagencyController(SearchMultiagency model, SearchMultiagencyFrame view, Frame previousView, int ukk) {
        this.model = model;
        this.view = view;
        this.previousView = previousView;
        this.ukk = ukk;
        addClientMenuActions(view, ukk);
        setViewCity();
    }


    public SearchMultiagencyController(SearchMultiagency model, SearchMultiagencyFrame view, Frame previousView, int ukk, Connectivity con) {
        this.con = con;
        this.model = model;
        this.view = view;
        this.previousView = previousView;
        this.ukk = ukk;
        addClientMenuActions(view, ukk);
        setViewCity();
    }

    private String getModelCity(){
        return model.getCity();
    }

    private List getModelMultiagencies(){
        return model.getMultiagencies();
    }

    private void setModelCity(String city){
        model.setCity(city);
    }

    private void setModelMultiagencies(Multiagency multiagency){
        model.addMultiagencies(multiagency);
    }

    private String getViewCity(){
        return view.getInputSearchByCity();
    }

    private void setViewCity(){
        view.setSearchByCityButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("search");
                getMultiagencies();
                addColumnsToMultiagenciesTable();
            }
        });
    }

    private void getMultiagencies() {
        view.restartRowCount();
        model.getMultiagencies().clear();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        setModelCity(getViewCity());
        String sql="select * from multiagency INNER join contact on multiagency.contact_id=contact.id where city=?";
        try{
            con =  new Connectivity();
            System.out.println(getModelCity());
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, getModelCity());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("w srodku 1");
                Multiagency multiagency = new Multiagency();
                multiagency.setId(resultSet.getInt("id"));
                multiagency.setName(resultSet.getString("name"));
                multiagency.setContact(getContact(resultSet.getInt("contact_id")));

                model.addMultiagencies(multiagency);
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private Contact getContact(int id){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from contact where id=?";
        try{
            //con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));
            resultSet = preparedStatement.executeQuery();

            System.out.println("na zewnatrz");
            while (resultSet.next())
            {
                System.out.println("w srodku");
                Contact contact = new Contact();
                contact.setCity(resultSet.getString("city"));
                contact.setId(resultSet.getInt("id"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));
                contact.setPostCode(resultSet.getString("post_code"));
                contact.setStreetAndNo(resultSet.getString("street_building_number"));

                return contact;
            }

        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void addColumnsToMultiagenciesTable(){
        List<Multiagency> multiagencyList = model.getMultiagencies();
        System.out.println(multiagencyList);
        Iterator it = multiagencyList.iterator();
        int i = 0;
        while(it.hasNext()){
            i++;
            Multiagency multiagency = (Multiagency) it.next();
            view.addColumnToMultiagencyTable(multiagency.infoForTable(i));
        }
    }
}
