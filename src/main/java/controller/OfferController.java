package controller;

import model.Offer;
import model.Server.Connectivity;
import view.offer.OfferFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class OfferController extends Controller{
    private Connectivity con;
    private int ukk;
    private int ukkToBuyPolice;
    private int agentId;
    private OfferFrame view;
    private Offer offer;

    public OfferController(int ukk, int agentId, Offer offer){
        this.ukk = ukk;
        this.agentId = agentId;
        this.offer = offer;
        view = new OfferFrame(offer.getName(), ukk, offer);
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
            view.addButtonBuyListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.createUKKInput();
                }
            });
            view.addButtonAcceptListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OfferController.this.checkUKKToBuyPolice();
                }
            });
        }
    }

    private void checkUKKToBuyPolice(){
        try{
            this.ukkToBuyPolice = Integer.parseInt(view.getInputUKK());
        }
        catch (NumberFormatException e){
            this.ukkToBuyPolice = -1;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select * from client where ukk=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setInt(1,ukkToBuyPolice);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                this.chooseOffer();
            }
            else {
                view.showErrorMessage("Klient o wpisanym ukk nie istnieje");
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

    private void chooseOffer(){
        view.dispose();
        double clientDiscount = countDiscount();
        new OfferConfirmController(ukk, agentId, offer, ukkToBuyPolice, clientDiscount);
    }

    private double countDiscount(){
        String clientPesel = "";
        double clientDiscount = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql="select client.pesel from client where ukk=?";
        try{
            con =  new Connectivity();
            preparedStatement = con.getConn().prepareStatement(sql);
            preparedStatement.setInt(1,ukkToBuyPolice);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                clientPesel = resultSet.getString("pesel");
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
            con.close();
        }

        int clientYear = Integer.parseInt(clientPesel.substring(0,2));
        int clientMonth = Integer.parseInt(clientPesel.substring(2,4));
        int clientAge;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if(clientMonth>12){
            clientYear+=2000;
            clientAge = year - clientYear;
        }
        else{
            clientYear+=1900;
            clientAge = year - clientYear;
        }

        if(clientAge <= 21){
            clientDiscount = 1;
        }
        else if(clientAge <= 40){
            clientDiscount = 0.9;
        }
        else{
            clientDiscount = 0.8;
        }
        System.out.println("znizka na podstawie wieku: "+clientDiscount);
        return clientDiscount;
    }
}
