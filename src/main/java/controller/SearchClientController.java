package controller;

import model.Client;
import model.Contact;
import model.Policy;
import model.Server.Connectivity;
import model.Server.SearchClient;
import model.enums.Status;
import view.Frame;
import view.client.SearchClientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchClientController extends Controller {
    private SearchClient model;
    private SearchClientFrame view;
    private Connectivity con;
    private Frame previousView;
    private Client currentClient;
    private Contact currentContact;
    private int agentId;
    private int ukk;
    private String searchUKK;
    private Pattern pattern;
    private Matcher matcher;

    public SearchClientController(SearchClient model, final SearchClientFrame view, Frame previousView, int agentId, int ukk) {
        this.model = model;
        this.view = view;
        this.previousView = previousView;
        this.ukk = ukk;
        this.agentId = agentId;
        if(this.ukk>0) {
            System.out.println("klienta menu");
            addClientMenuActions(this.view, this.ukk, this.agentId);
        }
        else{
            System.out.println("agenta menu");
            addAgentMenuActions(this.view, this.ukk, this.agentId);
        }
        this.setSearchClientByUkkButton();
        view.setMoreInfoButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAddressInfo();
                view.setAddressTableVisibility(true);


            }
        });

        view.setSendMessageButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setInputMessageVisibility(true);
                view.setButtonMessageFinishfVisibility(true);

            }
        });

        view.setSendMessageFinishButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setMessage(view.getInputMessage()) != -1) {
                    System.out.println("wiadomość wysłana");
                    view.setMessageLabel("Wiadomość wysłana pomyślnie");
                    view.setinputMessage("");
                    view.setInputMessageVisibility(false);
                    view.setButtonMessageFinishfVisibility(false);

                } else {
                    System.out.println("wiadomosc nie wysłana");
                }
            }

        });
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

    void getAddressInfo()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from client join contact on client.contact_id = contact.id where ukk=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);

            preparedStatement.setString(1,searchUKK);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next())
            {
                currentClient = new Client();
                currentContact = new Contact();

                currentContact.setCity(resultSet.getString("city"));
                currentContact.setStreetAndNo(resultSet.getString("street_building_number"));
                currentContact.setPostCode(resultSet.getString("post_code"));
                currentContact.setPhoneNumber(resultSet.getString("phone_number"));

                currentClient.setContact(currentContact);

                currentClient.setAgentId(resultSet.getInt("agent_id"));

            }

            System.out.println(currentClient.getContact().getCity());

        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {

            view.addColumnToInfoTable(addClinetInfoToTable());
        }

    }

    private Object[] addClinetInfoToTable(){

            Object[] infoTable = new Object[5];

        System.out.println(currentClient.getContact().getCity());
            infoTable[0] = currentClient.getContact().getCity();
            infoTable[1] = currentClient.getContact().getStreetAndNo();
            infoTable[2] = currentClient.getContact().getPostCode();
            infoTable[3] = currentClient.getContact().getPhoneNumber();
            infoTable[4] = currentClient.getAgentId();


        return infoTable;
    }

    private int setMessage(String message)
    {


        PreparedStatement preparedStatement = null;
        int resultSet = -1;

        String prefix = "Wiadomość napisana przez agenta o numerze identyfikacyjnym: "+agentId +"\n\n";

        String sql="UPDATE client SET message =? WHERE ukk = ?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setString(1,prefix+message);
            preparedStatement.setString(2, searchUKK);
            resultSet = preparedStatement.executeUpdate();
            System.out.println("---------");
            System.out.println(resultSet);

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


        return resultSet;
    }
    private void setViewAfterSearching(){
        view.setAddressTableVisibility(false);
        view.setInputMessageVisibility(false);
        view.setButtonMessageFinishfVisibility(false);
        view.setMoreInfoButtonVisibility(false);
        view.setSendMessageButtonVisibility(false);
        view.setFoundClientLabel("");

        setInputSearchByUkk(getViewInputSearchByUkk());
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from client where ukk=?";
        searchUKK = getInputSearchByUkk();
        pattern = Pattern.compile("^[0-9]*$");
        matcher = pattern.matcher(searchUKK);
        if (!matcher.matches()) {
            view.setErrorMessagePopUp("Wprowadzono niepoprawny numer UKK! \nUKK składa się z samych liczb.");

        }else {


            try {
                con = new Connectivity();
                preparedStatement = con.getConn().prepareStatement(sql);

                preparedStatement.setString(1, searchUKK);
                resultSet = preparedStatement.executeQuery();
                view.setErrorMessageLabel("Brak klienta o podanym ukk");
                view.setFoundClientLabel("");
                view.setButtonMessageFinishfVisibility(false);
                view.setMoreInfoButtonVisibility(false);
                view.setSendMessageButtonVisibility(false);

                while (resultSet.next()) {
                    view.setErrorMessageLabel("");
                    view.setFoundClientLabel(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                    view.setMoreInfoButtonVisibility(true);
                    view.setSendMessageButtonVisibility(true);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
