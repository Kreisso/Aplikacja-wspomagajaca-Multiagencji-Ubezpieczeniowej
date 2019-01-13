package view;

import view.user.AccountFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public abstract class Frame extends JFrame{

    private String name;
    private JMenuBar menuBar;

    private JButton myPoliciesMenu;
    private JButton searchOfferMenu;
    private JButton searchMultiagencyMenu;

    private JButton searchClientMenu;
    private JButton searchPoliceMenu;

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

        myPoliciesMenu = new JButton("Moje polisy");
        myPoliciesMenu.setMnemonic(KeyEvent.VK_N);
        myPoliciesMenu.setOpaque(true);
        myPoliciesMenu.setContentAreaFilled(false);
        myPoliciesMenu.setBorderPainted(false);
        myPoliciesMenu.setFocusable(false);
        menuBar.add(myPoliciesMenu);

        searchOfferMenu = new JButton("Wyszukaj ofertę");
        searchOfferMenu.setMnemonic(KeyEvent.VK_N);
        searchOfferMenu.setOpaque(true);
        searchOfferMenu.setContentAreaFilled(false);
        searchOfferMenu.setBorderPainted(false);
        searchOfferMenu.setFocusable(false);
        menuBar.add(searchOfferMenu);

        searchMultiagencyMenu = new JButton("Wyszukaj multiagencję");
        searchMultiagencyMenu.setMnemonic(KeyEvent.VK_N);
        searchMultiagencyMenu.setOpaque(true);
        searchMultiagencyMenu.setContentAreaFilled(false);
        searchMultiagencyMenu.setBorderPainted(false);
        searchMultiagencyMenu.setFocusable(false);
        menuBar.add(searchMultiagencyMenu);

        myAccountMenu = new JMenu("Moje konto");
        editPersonalDataMyAccountMenuItem = new JMenuItem("Edytuj dane", KeyEvent.VK_T);
        myAccountMenu.add(editPersonalDataMyAccountMenuItem);
        changePasswordMyAccountMenuItem = new JMenuItem("Zmień hasło", KeyEvent.VK_T);

        changePasswordMyAccountMenuItem.setMnemonic(KeyEvent.VK_T);
        changePasswordMyAccountMenuItem.getAccessibleContext().setAccessibleDescription("Przejdź do widoku zmiany hasłą");
//        changePasswordMyAccountMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        myAccountMenu.add(changePasswordMyAccountMenuItem);
        logutMyAccountMenuItem = new JMenuItem("Wyloguj", KeyEvent.VK_T);
        logutMyAccountMenuItem.getAccessibleContext().setAccessibleDescription("Wyloguj się ze swojego konta");
        myAccountMenu.add(logutMyAccountMenuItem);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(myAccountMenu);
    }
    public void createAgentMenu(){
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        myPoliciesMenu = new JButton("Polisy klientów");
        myPoliciesMenu.setMnemonic(KeyEvent.VK_N);
        myPoliciesMenu.setOpaque(true);
        myPoliciesMenu.setContentAreaFilled(false);
        myPoliciesMenu.setBorderPainted(false);
        myPoliciesMenu.setFocusable(false);
        myPoliciesMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku polis");
        menuBar.add(myPoliciesMenu);

        searchOfferMenu = new JButton("Wyszukaj ofertę");
        searchOfferMenu.setMnemonic(KeyEvent.VK_N);
        searchOfferMenu.setOpaque(true);
        searchOfferMenu.setContentAreaFilled(false);
        searchOfferMenu.setBorderPainted(false);
        searchOfferMenu.setFocusable(false);
        searchOfferMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wyszukiwania ofert");
        menuBar.add(searchOfferMenu);

        searchClientMenu = new JButton("Wyszukaj klienta");
        searchClientMenu.setMnemonic(KeyEvent.VK_N);
        searchClientMenu.setOpaque(true);
        searchClientMenu.setContentAreaFilled(false);
        searchClientMenu.setBorderPainted(false);
        searchClientMenu.setFocusable(false);
        searchClientMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wyszukiwania klienta");
        menuBar.add(searchClientMenu);

        searchPoliceMenu = new JButton("Wyszukaj polisę");
        searchPoliceMenu.setMnemonic(KeyEvent.VK_N);
        searchPoliceMenu.setOpaque(true);
        searchPoliceMenu.setContentAreaFilled(false);
        searchPoliceMenu.setBorderPainted(false);
        searchPoliceMenu.setFocusable(false);
        searchPoliceMenu.getAccessibleContext().setAccessibleDescription("Przejdź do widoku wyszukiwania polis");
        menuBar.add(searchPoliceMenu);

        myAccountMenu = new JMenu("Moje konto");
        changePasswordMyAccountMenuItem = new JMenuItem("Zmień hasło", KeyEvent.VK_T);
        changePasswordMyAccountMenuItem.getAccessibleContext().setAccessibleDescription("Przejdź do widoku zmiany hasłą");
        myAccountMenu.add(changePasswordMyAccountMenuItem);
        logutMyAccountMenuItem = new JMenuItem("Wyloguj", KeyEvent.VK_T);
        myAccountMenu.add(logutMyAccountMenuItem);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(myAccountMenu);
    }

    public void setMyPoliciesMenuItemListener(ActionListener actionListener){
        myPoliciesMenu.addActionListener(actionListener);
    }

    public void setSearchOfferMenuItemListener(ActionListener actionListener){
        searchOfferMenu.addActionListener(actionListener);
    }

    public void setSearchMultiagencyMenuItemListener(ActionListener actionListener){
        searchMultiagencyMenu.addActionListener(actionListener);
    }
    public void setSearchClientMenuListener(ActionListener actionListener){
        searchClientMenu.addActionListener(actionListener);
    }
    public void setSearchPoliceMenu(ActionListener actionListener){
        searchPoliceMenu.addActionListener(actionListener);
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

    public void setBiggerMenuSize(){
        myPoliciesMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        searchOfferMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        searchMultiagencyMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        myAccountMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        editPersonalDataMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        changePasswordMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        logutMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
    }

    public void setNormalMenuSize(){
        System.out.println(myPoliciesMenu.getFont().getFamily());
        myPoliciesMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        searchOfferMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        searchMultiagencyMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
        myAccountMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        editPersonalDataMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        changePasswordMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        logutMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
    }
}

