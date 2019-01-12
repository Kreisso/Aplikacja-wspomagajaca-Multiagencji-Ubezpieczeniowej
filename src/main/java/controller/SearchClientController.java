package controller;

import model.Server.Connectivity;
import model.Server.SearchClient;
import view.Frame;
import view.client.SearchClientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchClientController extends Controller {
    private SearchClient model;
    private SearchClientFrame view;
    private Connectivity con;
    private Frame previousView;
    private int agentId;
    private int ukk;

    public SearchClientController(SearchClient model, SearchClientFrame view, Frame previousView, int agentId, int ukk) {
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
        setSearchClientByUkkButton();
    }

    public SearchClientController(SearchClient model, SearchClientFrame view, Frame previousView, int agentId, int ukk, Connectivity con) {
        this(model, view, previousView, ukk, agentId);
        this.con = con;
    }

    String getViewInputSearchByUkk(){
        return view.getInputSearchByUkk();
    }
    String getInputSearchByUkk(){
        return model.getUkk();
    }
    void setInputSearchByUkk(String clientUkk){
        model.setUkk(clientUkk);
    }

    void setSearchClientByUkkButton(){
        view.setSearchByUkkButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setViewAfterSearching();
            }
        });
    }

    void setViewAfterSearching(){
        setInputSearchByUkk(getViewInputSearchByUkk());
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from client where ukk=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1, getInputSearchByUkk());
            resultSet = preparedStatement.executeQuery();
            view.setErrorMessageLabel("Brak klienta o podanym ukk");
            view.setFoundClientLabel("");
            view.setMoreInfoButtonVisibility(false);
            view.setSendMessageButtonVisible(false);

            while (resultSet.next())
            {
                view.setErrorMessageLabel("");
                view.setFoundClientLabel(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                view.setMoreInfoButtonVisibility(true);
                view.setSendMessageButtonVisible(true);
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
}
