package view;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

public abstract class Frame extends JFrame{

    private String name;
    private JMenuBar menuBar;
    private JMenu myPoliciesMenu;
    private JMenu searchOfferMenu;
    private JMenu searchMultiagencyMenu;
    private JMenu myAccountMenu;
    private JMenuItem editPersonalDataMyAccountMenuItem;
    private JMenuItem changePasswordMyAccountMenuItem;
    private JMenuItem logutMyAccountMenuItem;

    public Frame(String name) throws HeadlessException {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void createClientMenu(){
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        myPoliciesMenu = new JMenu("Moje polisy");
        myPoliciesMenu.setMnemonic(KeyEvent.VK_N);
        myPoliciesMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku polis");
        menuBar.add(myPoliciesMenu);

        searchOfferMenu = new JMenu("Wyszukaj ofertę");
        searchOfferMenu.setMnemonic(KeyEvent.VK_N);
        searchOfferMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wyszukiwania ofert");
        menuBar.add(searchOfferMenu);

        searchMultiagencyMenu = new JMenu("Wyszukaj multiagencję");
        searchMultiagencyMenu.setMnemonic(KeyEvent.VK_N);
        searchMultiagencyMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wyszukiwania multiagencji");
        menuBar.add(searchMultiagencyMenu);

        myAccountMenu = new JMenu("Moje konto");
        editPersonalDataMyAccountMenuItem = new JMenuItem("Edytuj dane", KeyEvent.VK_T);
        editPersonalDataMyAccountMenuItem.getAccessibleContext().setAccessibleDescription("Przejdź do widoku edycji danych");
        myAccountMenu.add(editPersonalDataMyAccountMenuItem);
        changePasswordMyAccountMenuItem = new JMenuItem("Zmień hasło", KeyEvent.VK_T);
        changePasswordMyAccountMenuItem.getAccessibleContext().setAccessibleDescription("Przejdź do widoku zmiany hasłą");
        myAccountMenu.add(changePasswordMyAccountMenuItem);
        logutMyAccountMenuItem = new JMenuItem("Wyloguj", KeyEvent.VK_T);
        logutMyAccountMenuItem.getAccessibleContext().setAccessibleDescription("Wyloguj się ze swojego konta");
        myAccountMenu.add(logutMyAccountMenuItem);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(myAccountMenu);
    }

    public void setMyPoliciesMenuListener(MenuListener menuListener){
        myPoliciesMenu.addMenuListener(menuListener);
    }

    public void setSearchOfferMenuListener(MenuListener menuListener){
        searchOfferMenu.addMenuListener(menuListener);
    }

    public void setSearchMultiagencyMenuListener(MenuListener menuListener){
        searchMultiagencyMenu.addMenuListener(menuListener);
    }

    public void setEditPersonalDataMyAccountMenuItemListener(ActionListener actionListener){
        editPersonalDataMyAccountMenuItem.addActionListener(actionListener);
    }

    public void setChangePasswordMyAccountMenuItemListener(ActionListener actionListener){
        changePasswordMyAccountMenuItem.addActionListener(actionListener);
    }

    public void setLogutMyAccountMenuItemListener(ActionListener actionListener){
        logutMyAccountMenuItem.addActionListener(actionListener);
    }
}

