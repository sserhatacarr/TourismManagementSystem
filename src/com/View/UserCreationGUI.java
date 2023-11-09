package com.View;

import com.Helper.Config;
import com.Helper.DBConnector;
import com.Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCreationGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_uname;
    private JTextField fld_password;
    private JComboBox cmb_user_type;
    private JLabel lbl_uname;
    private JLabel lbl_password;
    private JLabel lbl_user_type;
    private JButton btn_create_user;
    private JTextField fld_email;
    private JTextField fld_surname;
    private JTextField fld_name;
    private JLabel lbl_name;
    private JLabel lbl_surname;
    private JLabel lbl_email;
    private JButton btn_back;
    private JButton btn_listof_users;

    public UserCreationGUI() {
        add(wrapper);
        setSize(280, 310);
        Helper.setLayout();
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_create_user.addActionListener(e -> {
            String name = fld_name.getText();
            String surname = fld_surname.getText();
            String email = fld_email.getText();
            String uname = fld_uname.getText();
            String password = fld_password.getText();
            String userType = cmb_user_type.getSelectedItem().toString();

            if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_surname) || Helper.isFieldEmpty(fld_email) || Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.isEmailValid(email)) {
                    if (createNewUser(name, surname, email, uname, password, userType)) {
                        Helper.showMsg("User created successfully.");
                        dispose();
                        SignInGUI signInGUI = new SignInGUI();
                    } else {
                        Helper.showMsg("error");
                    }
                }else {
                    Helper.showMsg("email");
                }
            }
        });

        btn_back.addActionListener(e -> {
            dispose();
            SignInGUI signInGUI = new SignInGUI();
        });
        btn_listof_users.addActionListener(e -> {
            dispose();
            UserListGUI userListGUI = new UserListGUI();
        });
    }

    private boolean createNewUser(String name, String surname, String email, String uname, String password, String userType) {
        String query = "INSERT INTO user (first_name, last_name, email, uname, password, type) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, surname);
            pr.setString(3, email);
            pr.setString(4, uname);
            pr.setString(5, password);
            pr.setString(6, userType);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new UserCreationGUI();
    }
}
