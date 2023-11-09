package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationListGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_reservation_list;
    private JTable tbl_reservation_list;
    private JScrollPane scrll_reservation_list;
    private JPanel pnl_reservation_srch;
    private JTextField fld_contact_name;
    private JTextField fld_hotel_name;
    private JButton btn_search;
    private JButton deleteButton;
    private DefaultTableModel mdl_reservation_list;
    private Object[] row_reservation_list;

    public ReservationListGUI() {
        add(wrapper);
        setSize(1200, 500);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_reservation_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] col_reservation_list = {"Contact Name", "Contact Phone", "Contact E-Mail", "Hotel Name", "Pension Type", "Room Type", "Room Count", "Adult Count", "Child Count", "Days", "Entrance Date", "Release Date", "Price"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);
        row_reservation_list = new Object[col_reservation_list.length];

        loadReservationList();

        tbl_reservation_list.setModel(mdl_reservation_list);
        tbl_reservation_list.getTableHeader().setReorderingAllowed(false);

        btn_search.addActionListener(e -> {
            String contactName = fld_contact_name.getText();
            String hotelName = fld_hotel_name.getText();
            loadReservationList(Reservation.getList(fld_contact_name.getText(), fld_hotel_name.getText()));
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = tbl_reservation_list.getSelectedRow();
            if (selectedRow == -1) {
                Helper.showMsg("select");
            } else {
                String selectedName = (String) tbl_reservation_list.getValueAt(selectedRow, 0);
                Reservation.deleteReservation(selectedName);
                Helper.showMsg("success");
                loadReservationList();
            }
        });
    }

    public void loadReservationList() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Reservation obj : Reservation.getList()) {
            i = 0;
            row_reservation_list[i++] = obj.getContactName();
            row_reservation_list[i++] = obj.getContactPhone();
            row_reservation_list[i++] = obj.getContactEmail();
            row_reservation_list[i++] = obj.getHotelName();
            row_reservation_list[i++] = obj.getPensionType();
            row_reservation_list[i++] = obj.getRoomType();
            row_reservation_list[i++] = obj.getRoomCount();
            row_reservation_list[i++] = obj.getAdultCount();
            row_reservation_list[i++] = obj.getChildCount();
            row_reservation_list[i++] = obj.getDays();
            row_reservation_list[i++] = obj.getEntranceDate();
            row_reservation_list[i++] = obj.getReleaseDate();
            row_reservation_list[i++] = obj.getPrice();
            mdl_reservation_list.addRow(row_reservation_list);
        }
    }

    public void loadReservationList(ArrayList<Reservation> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_reservation_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Reservation obj : list) {
            i = 0;
            row_reservation_list[i++] = obj.getContactName();
            row_reservation_list[i++] = obj.getContactPhone();
            row_reservation_list[i++] = obj.getContactEmail();
            row_reservation_list[i++] = obj.getHotelName();
            row_reservation_list[i++] = obj.getPensionType();
            row_reservation_list[i++] = obj.getRoomType();
            row_reservation_list[i++] = obj.getRoomCount();
            row_reservation_list[i++] = obj.getAdultCount();
            row_reservation_list[i++] = obj.getChildCount();
            row_reservation_list[i++] = obj.getDays();
            row_reservation_list[i++] = obj.getEntranceDate();
            row_reservation_list[i++] = obj.getReleaseDate();
            row_reservation_list[i++] = obj.getPrice();
            mdl_reservation_list.addRow(row_reservation_list);
        }
    }
}
