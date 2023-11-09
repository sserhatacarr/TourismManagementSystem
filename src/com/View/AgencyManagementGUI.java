package com.View;


import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Hotel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class AgencyManagementGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_entrance_date;
    private JPanel pnl_release_date;
    private JButton bttn_search;
    private JComboBox cmb_chld_count;
    private JComboBox cmb_adult_count;
    private JComboBox cmb_hotel_city;
    private JButton bttn_hotel_add;
    private JButton bttn_add_room;
    private JButton bttn_show_reservations;
    private JDateChooser entranceDateChooser;
    private JDateChooser releaseDateChooser;
    private Calendar calendar;


    public AgencyManagementGUI(){
        add(wrapper);
        setSize(300,375);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        loadCityCombo();

        //calendar
        calendar = Calendar.getInstance();

        entranceDateChooser = new JDateChooser();
        entranceDateChooser.setDateFormatString("yyyy-MM-dd");
        entranceDateChooser.setMinSelectableDate(new Date());
        pnl_entrance_date.add(entranceDateChooser);

        releaseDateChooser = new JDateChooser();
        releaseDateChooser.setDateFormatString("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        releaseDateChooser.setMinSelectableDate(calendar.getTime());
        pnl_release_date.add(releaseDateChooser);

        //##calendar



        bttn_search.addActionListener(e -> {
            java.sql.Date entranceDate =Helper.convertDate(entranceDateChooser);
            java.sql.Date releaseDate = Helper.convertDate(releaseDateChooser);

            if (entranceDate != null && releaseDate != null){
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(entranceDate);

                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(releaseDate);

                boolean isDatesEqual = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                        cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                        cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

                if (isDatesEqual) {
                    JOptionPane.showMessageDialog(this,"Must stay at least one night");
                }else if (releaseDate.before(entranceDate)){
                    JOptionPane.showMessageDialog(this,"Entrance Date cannot be earlier than Release Date");
                }else {
                    String city = cmb_hotel_city.getSelectedItem().toString();
                    long diffInMillies = Math.abs(entranceDate.getTime() - releaseDate.getTime());
                    long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
                    int days = (int) diffInDays;
                    int adultCount = Integer.parseInt(cmb_adult_count.getSelectedItem().toString());
                    int childCount = Integer.parseInt(cmb_chld_count.getSelectedItem().toString());

                    SearchHotelGUI reservationCreationGUI = new SearchHotelGUI(city,days,adultCount,childCount,entranceDate,releaseDate);
                }
            }else {
                JOptionPane.showMessageDialog(this,"Please Fill All Lines!");
            }
        });


        bttn_hotel_add.addActionListener(e -> {
            HotelCreationGUI hotelCreationGUI = new HotelCreationGUI();
            dispose();
        });


        bttn_add_room.addActionListener(e -> {
            RoomCreationGUI roomGUI = new RoomCreationGUI();
            dispose();
        });
        bttn_show_reservations.addActionListener(e -> {
            ReservationListGUI reservationListGUI = new ReservationListGUI();
        });
    }

    public void loadCityCombo(){
        cmb_hotel_city.removeAllItems();
        for (String city: Hotel.cities()){
            cmb_hotel_city.addItem(city);
        }
    }

    public static void main(String[] args) {
        AgencyManagementGUI agencyManagementGUI = new AgencyManagementGUI();
    }
}