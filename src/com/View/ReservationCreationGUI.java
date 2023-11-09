package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.*;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReservationCreationGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_hotel_features;
    private JPanel pnl_hotel;
    private JLabel lbl_hotel_name;
    private JLabel lbl_address;
    private JLabel lbl_district;
    private JLabel lbl_city;
    private JTextArea txt_hotel_features;
    private JLabel lbl_room_type;
    private JLabel lbl_entrence_date;
    private JLabel lbl_release_date;
    private JLabel lbl_adult_count;
    private JLabel lbl_child_count;
    private JLabel lbl_room_count;
    private JLabel lbl_bed_count;
    private JLabel lbl_television;
    private JLabel lbl_minibar;
    private JLabel lbl_game_console;
    private JLabel lbl_till;
    private JLabel lbl_square_meter;
    private JLabel lbl_projection;
    private JLabel lbl_days;
    private JTabbedPane tab_operator;
    private JPanel pnl_guest_information;
    private JComboBox cmb_pension_type;
    private JButton btn_calculate;
    private JTextField fld_guest_name;
    private JTextField fld_guest_phone;
    private JTextField fld_guest_email;
    private JButton completeReservationButton;
    private JPanel pnl_guest;
    private JLabel lbl_pension_type;

    private JTextField[] adultTextFields;


    private Room room;
    private Hotel hotel;

    public ReservationCreationGUI(Room room, Hotel hotel, int roomCount, int adultCount, int childCount, int days, Date entranceDate, Date releaseDate) {
        this.hotel = hotel;
        this.room = room;

        add(wrapper);
        setSize(850, 500);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        loadPensionTypeCombo();

        //HotelPanel
        lbl_hotel_name.setText(hotel.getName());
        lbl_city.setText("City: " + hotel.getCity());
        lbl_district.setText("District: " + hotel.getDistrict());
        lbl_address.setText("Address: " + hotel.getAddress());
        txt_hotel_features.setText(hotel.getFacilityFeatures());
        //##HotelPanel

        //ReservationPanel
        lbl_room_type.setText(room.getRoomType() + " Room");
        lbl_entrence_date.setText("Entrance Date: " + entranceDate);
        lbl_release_date.setText("Release Date: " + releaseDate);
        lbl_adult_count.setText(String.valueOf(adultCount) + " Adult");
        lbl_child_count.setText(String.valueOf(childCount) + " Children");
        lbl_room_count.setText(String.valueOf(roomCount) + " Room");


        lbl_bed_count.setText(room.getBedCount() + " Bed ");
        lbl_television.setText("Television: " + room.getTelevision());
        lbl_minibar.setText("Minibar: " + room.getMinibar());
        lbl_game_console.setText("Game Console: " + room.getGameConsole());
        lbl_square_meter.setText("Room Size: " + room.getSquareMeter());
        lbl_till.setText("Till: " + room.getTill());
        lbl_projection.setText("Projection: " + room.getProjection());

        lbl_days.setText("For " + String.valueOf(days) + " days.");
        //##ReservationPanel


        btn_calculate.addActionListener(e -> {
            Season season = new Season();
            Season.isSummerSeason(entranceDate);
            if (Season.isSummerSeason(entranceDate)) {
                int price = (Calculation.calculate(hotel, room.getRoomType(), days, cmb_pension_type.getSelectedItem().toString(), roomCount, adultCount, childCount)) * 2;
                Helper.showMsg(Integer.toString(price));
            }else {
                int price = Calculation.calculate(hotel, room.getRoomType(), days, cmb_pension_type.getSelectedItem().toString(), roomCount, adultCount, childCount);
                Helper.showMsg(Integer.toString(price));
            }

        });
        completeReservationButton.addActionListener(e -> {
            int price = Calculation.calculate(hotel, room.getRoomType(), days, cmb_pension_type.getSelectedItem().toString(), roomCount, adultCount, childCount);
            if (Helper.isFieldEmpty(fld_guest_name) || Helper.isFieldEmpty(fld_guest_phone) || Helper.isFieldEmpty(fld_guest_email)) {
                Helper.showMsg("fill");
            } else {
                System.out.println(entranceDate);
                System.out.println(releaseDate);
                if (Reservation.createReservation(fld_guest_name.getText(), fld_guest_phone.getText(), fld_guest_email.getText(), hotel.getName(), cmb_pension_type.getSelectedItem().toString(), room.getRoomType(), roomCount, adultCount, childCount, days, entranceDate, releaseDate, price)) {
                    Helper.showMsg("success");
                    Room.deleteStock(roomCount, room.getRoomType(), hotel.getName());
                    /*Hotel.deleteStock(roomCount, room.getRoomType(), hotel.getName());*/
                    dispose();
                }

            }


        });
    }

    public void loadPensionTypeCombo() {
        String[] pensionTypes = hotel.getPensionType().split(",");
        for (String pension : pensionTypes) {
            cmb_pension_type.addItem(pension);
        }
    }
}
