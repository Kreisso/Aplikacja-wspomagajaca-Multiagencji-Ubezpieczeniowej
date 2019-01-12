package view;

import view.user.AccountFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

public abstract class Frame extends JFrame{

    private String name;
    private JMenuBar menuBar;
    private JButton myPoliciesMenu;
    private JButton searchOfferMenu;
    private JButton searchMultiagencyMenu;
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
        myPoliciesMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        searchOfferMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        searchMultiagencyMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        myAccountMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        editPersonalDataMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        changePasswordMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        logutMyAccountMenuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
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

