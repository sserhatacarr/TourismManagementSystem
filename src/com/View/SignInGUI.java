package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.User;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignInGUI extends JFrame{
    private JPanel pnl_logo;
    private JPanel pnl_fields;
    private JPanel pnl_btns;
    private JLabel lbl_uname;
    private JTextField fld_uname;
    private JLabel lbl_pass;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_logo;
    private JPanel wrapper;

    public SignInGUI(){
        add(wrapper);
        setSize(450,400);
        Helper.setLayout();
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        fld_pass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        btn_login.addActionListener(e -> {
            login();
        });
    }
    private void login(){
        if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_pass)){
            Helper.showMsg("fill");
        }else{
            User user = User.getFetch(fld_uname.getText(),fld_pass.getText());
            if (user == null) {
                Helper.showMsg("User not found");
            }else{
                switch (user.getType()) {
                    case "employee" -> {
                        AgencyManagementGUI agencyManagementGUI = new AgencyManagementGUI();
                        dispose();
                    }
                    case "admin" -> {
                        UserCreationGUI userCreationGUI = new UserCreationGUI();
                        dispose();
                    }
                    default -> System.out.println(user.getType());
                }

            }
        }
    }
}
