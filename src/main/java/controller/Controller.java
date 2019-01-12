package controller;

import model.Server.ClientMain;
import model.Server.Login;
import model.Server.SearchMultiagency;
import view.Frame;
import view.loginpanel.LoginFrame;
import view.mainviews.ClientMainFrame;
import view.multiagency.SearchMultiagencyFrame;
import view.offer.SearchOfferFrame;
import view.user.AccountFrame;
import view.user.ChangePasswordFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    protected void addClientMenuActions(final Frame activeView, final int ukk){
        if(!(activeView instanceof ClientMainFrame)) {
            activeView.setMyPoliciesMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new MainClientController(new ClientMain(), new ClientMainFrame("Moje polisy"), activeView, ukk);
                }
            });
        }
        if(!(activeView instanceof SearchOfferFrame)) {
            activeView.setSearchOfferMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new SearchOfferController(ukk);
                }
            });
        }
        if(!(activeView instanceof SearchMultiagencyFrame)) {
            activeView.setSearchMultiagencyMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new SearchMultiagencyController(new SearchMultiagency(), new SearchMultiagencyFrame("Wyszukaj Multiagencję"), activeView, ukk);
                }
            });
        }
        if(!(activeView instanceof AccountFrame)) {
            activeView.setEditPersonalDataMyAccountMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
        }
        if(!(activeView instanceof ChangePasswordFrame)) {
            activeView.setChangePasswordMyAccountMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
        }
        activeView.setLogutMyAccountMenuItemListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activeView.dispose();
                new LoginController(new Login(), new LoginFrame("Logowanie"));
            }
        });
    }
}
