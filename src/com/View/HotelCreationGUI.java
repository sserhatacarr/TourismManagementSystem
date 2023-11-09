package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Hotel;

import javax.swing.*;

public class HotelCreationGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_district;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone_number;
    private JTextField fld_hotel_stars;
    private JTextField fld_hotel_children_price;
    private JTextField fld_hotel_adult_price;
    private JButton bttn_add_hotel;
    private JCheckBox check_ultra_all_inc;
    private JCheckBox check_all_inc;
    private JCheckBox check_fll_pns;
    private JCheckBox check_hlf_pns;
    private JCheckBox check_room_break;
    private JCheckBox check_just_bed;
    private JCheckBox check_free_parking;
    private JCheckBox check_free_wifi;
    private JCheckBox check_swimming_pool;
    private JCheckBox check_fitness_center;
    private JCheckBox check_hotel_concierge;
    private JCheckBox check_spa;
    private JCheckBox check_room_service;
    private JButton btn_back;

    private StringBuilder pensions;
    private StringBuilder features;


    HotelCreationGUI(){
        add(wrapper);
        setSize(400,700);
        Helper.setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);




        bttn_add_hotel.addActionListener(e -> {

            features = new StringBuilder();
            if (check_free_parking.isSelected()){
                features.append("Free Parking, ");
            }if (check_free_wifi.isSelected()){
                features.append("Free Wifi, ");
            }if (check_swimming_pool.isSelected()){
                features.append("Swimming Pool, ");
            }if (check_fitness_center.isSelected()){
                features.append("Fitness Center, ");
            }if (check_hotel_concierge.isSelected()){
                features.append("Hotel Concierge, ");
            }if (check_spa.isSelected()){
                features.append("SPA, ");
            }if (check_room_service.isSelected()){
                features.append("7/24 Room Service, ");
            }
            if (features.length() > 1){
                features.setLength(features.length() - 2);
            }

            pensions = new StringBuilder();
            if (check_ultra_all_inc.isSelected()){
                pensions.append(check_ultra_all_inc.getText()).append(",");
            }if (check_all_inc.isSelected()){
                pensions.append(check_all_inc.getText()).append(",");
            }if (check_fll_pns.isSelected()){
                pensions.append(check_fll_pns.getText()).append(",");
            }if (check_hlf_pns.isSelected()){
                pensions.append(check_hlf_pns.getText()).append(",");
            }if (check_room_break.isSelected()){
                pensions.append(check_room_break.getText()).append(",");
            }if (check_just_bed.isSelected()){
                pensions.append(check_just_bed.getText()).append(",");
            }
            if (pensions.length() > 1){
                pensions.setLength(pensions.length() - 1);
            }

            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_district) || Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_phone_number) || Helper.isFieldEmpty(fld_hotel_stars) || Helper.isFieldEmpty(fld_hotel_adult_price) || Helper.isFieldEmpty(fld_hotel_children_price)) {
                Helper.showMsg("fill");
            } else {
                if (Hotel.add(fld_hotel_name.getText(), fld_hotel_city.getText(), fld_hotel_district.getText(), fld_hotel_address.getText(), fld_hotel_email.getText(), fld_hotel_phone_number.getText(), fld_hotel_stars.getText(), features.toString(), pensions.toString(), Integer.parseInt(fld_hotel_adult_price.getText()), Integer.parseInt(fld_hotel_children_price.getText()))) {
                    Helper.showMsg("success");
                    dispose();
                    AgencyManagementGUI agencyManagementGUI = new AgencyManagementGUI();
                } else {
                    Helper.showMsg("error");
                }
            }

        });

        btn_back.addActionListener(e -> {
            dispose();
            AgencyManagementGUI agencyManagementGUI = new AgencyManagementGUI();
        });
    }
}