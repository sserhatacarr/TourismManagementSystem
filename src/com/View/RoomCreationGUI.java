package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Hotel;
import com.Model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RoomCreationGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_hotel_list;
    private JScrollPane scrll_hotel_list;
    private JTable tbl_hotel_list;
    private JPanel pnl_room_form;
    private JTextField fld_hotel_name;
    private JComboBox cmb_room_type;
    private JTextField fld_bed_count;
    private JComboBox cmb_television;
    private JComboBox cmb_minibar;
    private JComboBox cmb_game_console;
    private JTextField fld_room_size;
    private JComboBox cmb_till;
    private JComboBox cmb_projection;
    private JButton bttn_add_room;
    private JTextField fld_room_stock;
    private JButton bttn_back;
    private JTextField fld_srch_hotel_name;
    private JButton bttn_search;
    private DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;


    public RoomCreationGUI() {
        add(wrapper);
        setSize(600,700);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] col_hotel_list = {"Hotel Name", "City", "District"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];

        loadHotelModel();

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);

        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            int selected_row = tbl_hotel_list.getSelectedRow();
            if (selected_row != -1){
                String selected_hotel_name =tbl_hotel_list.getValueAt(selected_row,0).toString();
                fld_hotel_name.setText(selected_hotel_name);
            }

        });



        bttn_add_room.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_room_stock) || Helper.isFieldEmpty(fld_bed_count) || Helper.isFieldEmpty(fld_room_size)){
                Helper.showMsg("fill");
            }else {
                if (Room.add(fld_hotel_name.getText(),cmb_room_type.getSelectedItem().toString(),Integer.parseInt(fld_room_stock.getText()),fld_bed_count.getText(),cmb_television.getSelectedItem().toString(),cmb_minibar.getSelectedItem().toString(),cmb_game_console.getSelectedItem().toString(),fld_room_size.getText(),cmb_till.getSelectedItem().toString(),cmb_projection.getSelectedItem().toString())){
                    Hotel.addStock(cmb_room_type.getSelectedItem().toString(),fld_hotel_name.getText(), Integer.parseInt(fld_room_stock.getText()));
                    Helper.showMsg("success");
                }
            }
            fld_hotel_name.setText(null);
            fld_room_stock.setText(null);
            fld_bed_count.setText(null);
            fld_room_size.setText(null);
        });
        bttn_back.addActionListener(e -> {
            AgencyManagementGUI agencyManagementGUI = new AgencyManagementGUI();
            dispose();
        });
        bttn_search.addActionListener(e -> {
            loadHotelModel(Hotel.getList(fld_srch_hotel_name.getText()));
        });
    }

    public void loadHotelModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj: Hotel.getList()){
            i = 0;
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    public void loadHotelModel(ArrayList<Hotel> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj: list){
            i = 0;
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getDistrict();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }


}
