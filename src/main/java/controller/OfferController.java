package controller;

import model.Offer;
import model.Server.Connectivity;
import view.offer.OfferFrame;
import view.offer.SearchOfferFrame;

import java.util.LinkedList;

/**
 * Created by RobertTrojan on 12/01/2019.
 */
public class OfferController extends Controller{
    Connectivity con;
    int ukk;
    int agentId;
    OfferFrame view;

    public OfferController(int ukk, int agentId, Offer offer){
        this.ukk = ukk;
        this.agentId = agentId;
        view = new OfferFrame(offer.getName(), ukk, offer);
        if(ukk>0) {
            addClientMenuActions(view, ukk, agentId);
        }
        else{
            addAgentMenuActions(view, ukk, agentId);
        }
    }
}
