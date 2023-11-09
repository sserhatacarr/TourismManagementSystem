package com.Model;

import com.Helper.DBConnector;
import com.Helper.Helper;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Reservation {
    // This class is used to create a reservation.
    private int id;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String hotelName;
    private String pensionType;
    private String roomType;
    private int roomCount;
    private int adultCount;
    private int childCount;
    private int days;
    private String entranceDate;
    private String releaseDate;
    private int price;

    Reservation() {

    }

    public Reservation(int id, String contactName, String contactPhone, String contactEmail, String hotelName, String pensionType, String roomType, int roomCount, int adultCount, int childCount, int days, String entranceDate, String releaseDate, int price) {
        this.id = id;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.hotelName = hotelName;
        this.pensionType = pensionType;
        this.roomType = roomType;
        this.roomCount = roomCount;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.days = days;
        this.entranceDate = entranceDate;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public static boolean createReservation(String contactName, String contactPhone, String contactEmail, String hotelName, String pensionType, String roomType, int roomCount, int adultCount, int childCount, int days, Date entranceDate, Date releaseDate, int price) {
        // This method is used to create a reservation.
        if(Season.isSummerSeason(entranceDate)){
            price = price * 2;
        }
        String query = "INSERT INTO reservation (contact_name,contact_phone,contact_email,hotel_name,pension_type,room_type,room_count,adult_count,child_count,days,entrance_date,release_date,price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, contactName);
            pr.setString(2, contactPhone);
            pr.setString(3, contactEmail);
            pr.setString(4, hotelName);
            pr.setString(5, pensionType);
            pr.setString(6, roomType);
            pr.setInt(7, roomCount);
            pr.setInt(8, adultCount);
            pr.setInt(9, childCount);
            pr.setInt(10, days);
            pr.setDate(11, entranceDate);
            pr.setDate(12, releaseDate);
            pr.setInt(13, price);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.showMsg("error");
            return false;
        }
    }


    public static ArrayList<Reservation> getList() {
        // This method is used to get the list of reservations.

        ArrayList<Reservation> reservationList = new ArrayList<>();
        Reservation obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reservation");
            while (rs.next()) {
                obj = new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setContactName(rs.getString("contact_name"));
                obj.setContactPhone(rs.getString("contact_phone"));
                obj.setContactEmail(rs.getString("contact_email"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setPensionType(rs.getString("pension_type"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomCount(rs.getInt("room_count"));
                obj.setAdultCount(rs.getInt("adult_count"));
                obj.setChildCount(rs.getInt("child_count"));
                obj.setDays(rs.getInt("days"));
                obj.setEntranceDate(rs.getString("entrance_date"));
                obj.setReleaseDate(rs.getString("release_date"));
                obj.setPrice(rs.getInt("price"));
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public static ArrayList<Reservation> getList(String contactName, String hotelName) {
        // This method is used to get the list of reservations by contact name and hotel name.

        ArrayList<Reservation> reservationList = new ArrayList<>();
        Reservation obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reservation WHERE contact_name LIKE '%" + contactName + "%' AND hotel_name LIKE '%" + hotelName + "%'");
            while (rs.next()) {
                obj = new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setContactName(rs.getString("contact_name"));
                obj.setContactPhone(rs.getString("contact_phone"));
                obj.setContactEmail(rs.getString("contact_email"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setPensionType(rs.getString("pension_type"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setRoomCount(rs.getInt("room_count"));
                obj.setAdultCount(rs.getInt("adult_count"));
                obj.setChildCount(rs.getInt("child_count"));
                obj.setDays(rs.getInt("days"));
                obj.setEntranceDate(rs.getString("entrance_date"));
                obj.setReleaseDate(rs.getString("release_date"));
                obj.setPrice(rs.getInt("price"));
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public static void deleteReservation(String selectedName) {
        // This method is used to delete a reservation by name.

        String query = "DELETE FROM reservation WHERE contact_name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, selectedName);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.showMsg("error");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(String entranceDate) {
        this.entranceDate = entranceDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}