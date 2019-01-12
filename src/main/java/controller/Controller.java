package controller;

import model.Server.*;
import view.Frame;
import view.client.SearchClientFrame;
import view.loginpanel.LoginFrame;
import view.mainviews.AgentMainFrame;
import view.mainviews.ClientMainFrame;
import view.multiagency.SearchMultiagencyFrame;
import view.offer.SearchOfferFrame;
import view.policy.SearchPolicyFrame;
import view.user.AccountFrame;
import view.user.ChangePasswordFrame;
import view.user.EditDataFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    protected void addClientMenuActions(final Frame activeView, final int ukk, final int agentId){
        if(!(activeView instanceof ClientMainFrame)) {
            activeView.setMyPoliciesMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new MainClientController(new ClientMain(), new ClientMainFrame("Moje polisy"), activeView, ukk, agentId);
                }
            });
        }
        if(!(activeView instanceof SearchOfferFrame)) {
            activeView.setSearchOfferMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new SearchOfferController(ukk, agentId);
                }
            });
        }
        if(!(activeView instanceof SearchMultiagencyFrame)) {
            activeView.setSearchMultiagencyMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new SearchMultiagencyController(new SearchMultiagency(),
                            new SearchMultiagencyFrame("Wyszukaj multiagencje"),activeView, ukk, agentId);
                }
            });
        }
        if(!(activeView instanceof EditDataFrame)) {
            activeView.setEditPersonalDataMyAccountMenuItemListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new EditDataController(new EditData(), new EditDataFrame("Edycja danych"), agentId, ukk, activeView);
                }
            });
        }
        if(!(activeView instanceof ChangePasswordFrame)) {
            activeView.setChangePasswordMyAccountMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new ChangePasswordController(new ChangePassword(), new ChangePasswordFrame("Zmiana hasła", ukk), activeView, agentId, ukk);
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

    protected void addAgentMenuActions(final Frame activeView, final int ukk, final int agentId){
        if(!(activeView instanceof AgentMainFrame)) {
            activeView.setMyPoliciesMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new MainAgentController(new AgentMain(), new AgentMainFrame("Polisy klientów"), activeView, agentId, ukk);
                }
            });
        }
        if(!(activeView instanceof SearchOfferFrame)) {
            activeView.setSearchOfferMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new SearchOfferController(ukk, agentId);
                }
            });
        }
        if(!(activeView instanceof SearchClientFrame)){
            activeView.setSearchClientMenuListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new SearchClientController(new SearchClient(), new SearchClientFrame("Wyszukaj klienta"), activeView, agentId, ukk);
                }
            });
        }
        if(!(activeView instanceof SearchPolicyFrame)){
            activeView.setSearchPoliceMenu(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                    System.out.println("Szukaj polise");
                }
            });
        }
        if(!(activeView instanceof ChangePasswordFrame)) {
            activeView.setChangePasswordMyAccountMenuItemListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    activeView.dispose();
                    new ChangePasswordController(new ChangePassword(), new ChangePasswordFrame("Zmiana hasła", ukk), activeView, agentId, ukk);
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
