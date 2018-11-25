package controller;

import model.Contact;
import model.Multiagency;
import model.Server.Connectivity;
import model.Server.SearchMultiagency;
import view.multiagency.SearchMultiagencyFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SearchMultiagencyController {
    private SearchMultiagency model;
    private SearchMultiagencyFrame view;
    private Connectivity con;

    public SearchMultiagencyController(SearchMultiagency model, SearchMultiagencyFrame view, Connectivity con) {
        this.model = model;
        this.view = view;
        this.con = con;
    }

    public SearchMultiagencyController(SearchMultiagency model, SearchMultiagencyFrame view) {
        this.model = model;
        this.view = view;
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
                getMultiagencies();
            }
        });
    }

    private void getMultiagencies()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from multiagency join contact on multiagency.contact_id=contact.id where city=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, model.getCity());
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next())
            {
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


            while (resultSet.next())
            {
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
}
