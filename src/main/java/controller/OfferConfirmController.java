package controller;

import model.Offer;
import model.Server.AgentMain;
import model.Server.Connectivity;
import view.mainviews.AgentMainFrame;
import view.offer.OfferConfirmFrame;

import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OfferConfirmController extends Controller{
    private Connectivity con;
    private int ukk;
    private int agentId;
    private int ukkToBuyPolice;
    private int getUkkToBuyPolice;
    private double clientDiscount;
    private OfferConfirmFrame view;
    private Offer offer;

    public OfferConfirmController(final int ukk, final int agentId, Offer offer, int ukkToBuyPolice, double clientDiscount){
        this.ukk = ukk;
        this.agentId = agentId;
        this.offer = offer;
        this.ukkToBuyPolice = ukkToBuyPolice;
        this.view = new OfferConfirmFrame("Dostosuj ofertę", ukk, offer, ukkToBuyPolice, clientDiscount);
        addAgentMenuActions(view, ukk, agentId);

        view.addComboBoxHowLongListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int howLong = view.getComboBoxHowLongSelectedIndex();
                double discount;
                if(howLong == 0){
                    discount = 1;
                }
                else if(howLong == 1){
                    discount = 0.95;
                }
                else if(howLong == 2){
                    discount = 0.9;
                }
                else{
                    discount = 0.85;
                }
                view.setLabelPriceAfterDiscountDiscount(discount);
            }
        });

        view.addButtonAcceptListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(OfferConfirmController.this.buyPolcy()){
                    if(view.showSuccessMessage("Polisa została zakupiona")==0){
                        view.dispose();
                        new MainAgentController(new AgentMain(), new AgentMainFrame("Polisy klientów"), view, agentId, ukk);
                    }
                }
                else{
                    view.showErrorMessage("Z nieznanego powodu polisa nie mogła zostać zakupiona. Poinformuj o błędzie administratora aplikacji.");
                }
            }
        });
    }

    private boolean buyPolcy(){
        con = new Connectivity();
        String sql = "{call addPolicy(?,?,?,?,?,?)}";
        try {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = con.getConn().prepareStatement(sql);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateBeginning = String.valueOf(dateFormat.format(new Date()));

            int howLong = view.getComboBoxHowLongSelectedIndex();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if(howLong == 0){
                cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+1));
            }
            else if(howLong == 1){
                cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+3));
            }
            else if(howLong == 2){
                cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+6));
            }
            else{
                cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+12));
            }
            String dateEnding = String.valueOf(dateFormat.format(cal.getTime()));

            preparedStatement.setInt(1, agentId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(dateBeginning));
            preparedStatement.setDate(3, java.sql.Date.valueOf(dateEnding));
            preparedStatement.setInt(4, offer.getId());
            preparedStatement.setString(5, "Aktywna");
            preparedStatement.setInt(6, ukkToBuyPolice);

            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
        finally {
            con.close();
        }
        return true;
    }
}
