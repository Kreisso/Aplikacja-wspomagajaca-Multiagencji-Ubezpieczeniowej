package controller;

import model.Server.*;
import view.Frame;
import view.loginpanel.LoginFrame;
import view.mainviews.ClientMainFrame;
import view.multiagency.SearchMultiagencyFrame;
import view.offer.SearchOfferFrame;
import view.user.AccountFrame;
import view.user.ChangePasswordFrame;
import view.user.EditDataFrame;

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
                    new SearchMultiagencyController(new SearchMultiagency(),
                            new SearchMultiagencyFrame("Wyszukaj multiagencje"),activeView, ukk);
                }
            });
        }
        if(!(activeView instanceof AccountFrame)) {
            activeView.setEditPersonalDataMyAccountMenuItemListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditDataController(new EditData(), new EditDataFrame("Edycja danych"), -1, ukk, activeView);
                }
            });
        }
        if(!(activeView instanceof ChangePasswordFrame)) {
            activeView.setChangePasswordMyAccountMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new ChangePasswordController(new ChangePassword(), new ChangePasswordFrame("Zmiana hasła"), activeView, -1, ukk);
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
