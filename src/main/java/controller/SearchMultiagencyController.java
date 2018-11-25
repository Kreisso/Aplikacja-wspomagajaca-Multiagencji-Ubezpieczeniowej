package controller;

import model.Connectivity;
import model.Contact;
import model.Multiagency;
import model.SearchMultiagency;
import view.multiagency.SearchMultiagencyFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMultiagencyController {
    private SearchMultiagency model;
    private SearchMultiagencyFrame view;
    private Connectivity con;

    public SearchMultiagencyController(SearchMultiagency model, SearchMultiagencyFrame view) {
        this.model = model;
        this.view = view;
    }

    private String getViewCity(){
        return view.getInputSearchByCity();
    }

    private String getModelCity(){
        return model.getContact().getCity();
    }

    private void setModelCity(String city){
        contactModel.setCity(city);
    }

    private void  setModelContact(Contact contact){
        model.setContact(contact);
    }

    private void setViewCityButtonEvent()
    {
        view.setSearchByCityButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click search button");
                search();
            }
        });
    }

    private void search()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        String sql="select * from contact where city=?";
        try{
            con =  new Connectivity();
            setModelCity(getViewCity());

            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, getModelCity());
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
            if(resultSet!=null){
                System.out.println(resultSet);
            }
            else{
                System.out.println("blad");
            }
        }
    }
}
