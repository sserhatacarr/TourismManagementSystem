package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Hotel;
import com.Model.Room;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.ArrayList;

public class SearchHotelGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_hotel_list;
    private JScrollPane scrll_hotel_list;
    private JTable tbl_hotel_list;
    private JPanel pnl_reservation;
    private JTextField fld_hotel_name;
    private JButton bttn_create_reservation;
    private JComboBox cmb_room_type;
    private JTextField fld_room_count;
    private JTextField fld_search_hotel_name;
    private JButton bttn_search_hotel;
    private JPanel pnl_hotel_srch;
    private JButton backButton;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private String city;
    private int days;
    private int adultCount;
    private int childCount;
    private Date entranceDate;
    private Date releaseDate;

    public SearchHotelGUI(String city, int days, int adultCount, int childCount, Date entranceDate, Date releaseDate) {
        this.city = city;
        this.days = days;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.entranceDate = entranceDate;
        this.releaseDate = releaseDate;

        add(wrapper);
        setSize(850, 500);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] col_hotel_list = {"Hotel Name", "City", "District", "Stars"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(230);
        tbl_hotel_list.getColumnModel().getColumn(1).setMaxWidth(160);
        tbl_hotel_list.getColumnModel().getColumn(2).setMaxWidth(160);
        tbl_hotel_list.getColumnModel().getColumn(3).setMaxWidth(120);

        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            int seletedRow = tbl_hotel_list.getSelectedRow();
            if (seletedRow != -1) {
                String selected_hotel_name = tbl_hotel_list.getValueAt(seletedRow, 0).toString();
                fld_hotel_name.setText(selected_hotel_name);
            }
        });


        bttn_create_reservation.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_room_count) || cmb_room_type.getSelectedItem().toString().equals("")) {
                Helper.showMsg("fill");
            } else {
                if (Room.getFetch(fld_hotel_name.getText(), cmb_room_type.getSelectedItem().toString()) == null) {
                    Helper.showMsg("There is no room for this hotel information");
                } else if (Room.getFetch(fld_hotel_name.getText(), cmb_room_type.getSelectedItem().toString()).getStock() < Integer.parseInt(fld_room_count.getText())) {
                    Helper.showMsg("There is no enough room for this hotel information");

                } else {
                    ReservationCreationGUI reservationCreationGUI = new ReservationCreationGUI(Room.getFetch(fld_hotel_name.getText(), cmb_room_type.getSelectedItem().toString()), Hotel.getFetch(fld_hotel_name.getText()), Integer.parseInt(fld_room_count.getText()), adultCount, childCount, days, entranceDate, releaseDate);
                    dispose();
                }
            }
        });
        bttn_search_hotel.addActionListener(e -> {
            loadHotelModel(Hotel.getList(fld_search_hotel_name.getText(), city));
        });
        backButton.addActionListener(e -> {
            dispose();
        });
    }

    public void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getListByCity(city)) {
            boolean isRoomAviable = isRoomAviable(obj);
            if (!isRoomAviable) {
                continue;
            }
            i = 0;
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            row_hotel_list[i++] = obj.getStars();
            mdl_hotel_list.addRow(row_hotel_list);
        }
        if(mdl_hotel_list.getRowCount() == 0){
            Helper.showMsg("There is no enough room for this hotel information");
            dispose();
        }
    }

    public void loadHotelModel(ArrayList<Hotel> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : list) {
            boolean isRoomAviable = isRoomAviable(obj);
            if (!isRoomAviable) {
                continue;
            }
            i = 0;
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            row_hotel_list[i++] = obj.getStars();
            mdl_hotel_list.addRow(row_hotel_list);
        }
       if(mdl_hotel_list.getRowCount() == 0){
           Helper.showMsg("There is no hotel for this information");
           dispose();
       }
    }

    private static boolean isRoomAviable(Hotel obj) {
        boolean isRoomAviable = false;
        for (Room room : Room.getFetchAll(obj.getName())) {
            if (room.getStock() > 0) {
                isRoomAviable = true;
                break;
            }
        }
        return isRoomAviable;
    }
}