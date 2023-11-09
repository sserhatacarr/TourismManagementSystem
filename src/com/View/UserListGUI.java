package com.View;

import com.Helper.Config;
import com.Helper.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserListGUI extends JFrame {
    private JPanel wrapper;
    private JTable tbl_user_list;
    private JButton deleteUserButton;
    private JButton btn_back;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    public UserListGUI() {
        add(wrapper);
        setSize(480, 380);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_user_list = {"ID", "Username", "Password", "Name", "Surname", "E-Mail", "Role"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];

        loadUserList();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
        tbl_user_list.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl_user_list.getColumnModel().getColumn(1).setMaxWidth(50);
        tbl_user_list.getColumnModel().getColumn(2).setMaxWidth(50);
        tbl_user_list.getColumnModel().getColumn(3).setMaxWidth(75);
        tbl_user_list.getColumnModel().getColumn(4).setMaxWidth(75);
        tbl_user_list.getColumnModel().getColumn(5).setMaxWidth(150);
        tbl_user_list.getColumnModel().getColumn(6).setMaxWidth(75);


        deleteUserButton.addActionListener(e -> {
            if (tbl_user_list.getSelectedRow() != -1) {
                int selectedRow = tbl_user_list.getSelectedRow();
                int id = (int) tbl_user_list.getValueAt(selectedRow, 0);
                com.Model.User.delete(id);
                loadUserList();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a user to delete.");
            }
        });
        btn_back.addActionListener(e -> {
            dispose();
            SignInGUI signInGUI = new SignInGUI();

        });
    }

    private void loadUserList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (i = 0; i < com.Model.User.getList().size(); i++) {
            row_user_list[0] = com.Model.User.getList().get(i).getId();
            row_user_list[1] = com.Model.User.getList().get(i).getUname();
            row_user_list[2] = com.Model.User.getList().get(i).getPass();
            row_user_list[3] = com.Model.User.getList().get(i).getFirst_name();
            row_user_list[4] = com.Model.User.getList().get(i).getLast_name();
            row_user_list[5] = com.Model.User.getList().get(i).getEmail();
            row_user_list[6] = com.Model.User.getList().get(i).getType();
            mdl_user_list.addRow(row_user_list);
        }
    }


}
