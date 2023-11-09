package com.Model;

import com.Helper.DBConnector;
import com.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    // This class is used to store hotel information.
    private int id;
    private String name;
    private String city;
    private String district;
    private String address;
    private String email;
    private String phoneNumber;
    private String stars;
    private String facilityFeatures;
    private String pensionType;
    private int nightlyAdultPrice;
    private int nightlyChildrenPrice;
    private int singleStock;
    private int doubleStock;
    private int suitStock;

    public Hotel() {

    }

    public Hotel(int id, String name, String city, String district, String address, String email, String phoneNumber, String stars, String facilityFeatures, String pensionType, int nightlyAdultPrice, int nightlyChildrenPrice, int singleStock, int doubleStock, int suitStock) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.district = district;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.stars = stars;
        this.facilityFeatures = facilityFeatures;
        this.pensionType = pensionType;
        this.nightlyAdultPrice = nightlyAdultPrice;
        this.nightlyChildrenPrice = nightlyChildrenPrice;
        this.singleStock = singleStock;
        this.doubleStock = doubleStock;
        this.suitStock = suitStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getFacilityFeatures() {
        return facilityFeatures;
    }

    public void setFacilityFeatures(String facilityFeatures) {
        this.facilityFeatures = facilityFeatures;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public int getNightlyAdultPrice() {
        return nightlyAdultPrice;
    }

    public void setNightlyAdultPrice(int nightlyAdultPrice) {
        this.nightlyAdultPrice = nightlyAdultPrice;
    }

    public int getNightlyChildrenPrice() {
        return nightlyChildrenPrice;
    }

    public void setNightlyChildrenPrice(int nightlyChildrenPrice) {
        this.nightlyChildrenPrice = nightlyChildrenPrice;
    }

    public int getSingleStock() {
        return singleStock;
    }

    public void setSingleStock(int singleStock) {
        this.singleStock = singleStock;
    }

    public int getDoubleStock() {
        return doubleStock;
    }

    public void setDoubleStock(int doubleStock) {
        this.doubleStock = doubleStock;
    }

    public int getSuitStock() {
        return suitStock;
    }

    public void setSuitStock(int suitStock) {
        this.suitStock = suitStock;
    }


    public static boolean add(String name, String city, String district, String address, String email, String phoneNumber, String stars, String facilityFeatures, String pensionType, int nightlyAdultPrice, int nightlyChildrenPrice) {
        // This method is used to add a new hotel to the database.

        String query = "INSERT INTO hotel (name, city, district, address, email, phone_number, stars, facility_features, pension_type, adult_price, children_price, single_stock, double_stock, suit_stock) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int singleStock = 0;
        int doubleStock = 0;
        int suitStock = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, city);
            pr.setString(3, district);
            pr.setString(4, address);
            pr.setString(5, email);
            pr.setString(6, phoneNumber);
            pr.setString(7, stars);
            pr.setString(8, facilityFeatures);
            pr.setString(9, pensionType);
            pr.setInt(10, nightlyAdultPrice);
            pr.setInt(11, nightlyChildrenPrice);
            pr.setInt(12, singleStock);
            pr.setInt(13, doubleStock);
            pr.setInt(14, suitStock);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Hotel> getList() {
        // This method is used to get the list of hotels from the database.

        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM hotel");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cit = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String stars = rs.getString("stars");
                String facilityFeatures = rs.getString("facility_features");
                String pensionType = rs.getString("pension_type");
                int nightlyAdultPrice = rs.getInt("adult_price");
                int nightlyChildrenPrice = rs.getInt("children_price");
                int singleStock = rs.getInt("single_stock");
                int doubleStock = rs.getInt("double_stock");
                int suitStock = rs.getInt("suit_stock");
                obj = new Hotel(id, name, cit, district, address, email, phoneNumber, stars, facilityFeatures, pensionType, nightlyAdultPrice, nightlyChildrenPrice, singleStock, doubleStock, suitStock);
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static ArrayList<Hotel> getList(String hotelName) {
        // This method is used to get the list of hotels from the database by hotel name.

        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM hotel WHERE name LIKE '%" + hotelName + "%'");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cit = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String stars = rs.getString("stars");
                String facilityFeatures = rs.getString("facility_features");
                String pensionType = rs.getString("pension_type");
                int nightlyAdultPrice = rs.getInt("adult_price");
                int nightlyChildrenPrice = rs.getInt("children_price");
                int singleStock = rs.getInt("single_stock");
                int doubleStock = rs.getInt("double_stock");
                int suitStock = rs.getInt("suit_stock");
                obj = new Hotel(id, name, cit, district, address, email, phoneNumber, stars, facilityFeatures, pensionType, nightlyAdultPrice, nightlyChildrenPrice, singleStock, doubleStock, suitStock);
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static ArrayList<Hotel> getList(String hotelName, String hotelCity) {
        // This method is used to get the list of hotels from the database by hotel name and city.

        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement("SELECT * FROM hotel WHERE city = ? AND name LIKE '%" + hotelName + "%'");
            pr.setString(1, hotelCity);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cit = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String stars = rs.getString("stars");
                String facilityFeatures = rs.getString("facility_features");
                String pensionType = rs.getString("pension_type");
                int nightlyAdultPrice = rs.getInt("adult_price");
                int nightlyChildrenPrice = rs.getInt("children_price");
                int singleStock = rs.getInt("single_stock");
                int doubleStock = rs.getInt("double_stock");
                int suitStock = rs.getInt("suit_stock");
                obj = new Hotel(id, name, cit, district, address, email, phoneNumber, stars, facilityFeatures, pensionType, nightlyAdultPrice, nightlyChildrenPrice, singleStock, doubleStock, suitStock);
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static ArrayList<String> cities() {
        // This method is used to get the list of cities from the database.

        ArrayList<String> cityList = new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT city FROM hotel");
            while (rs.next()) {
                cityList.add(rs.getString("city"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cityList;
    }

    public static ArrayList<Hotel> getListByCity(String city) {
        // This method is used to get the list of hotels from the database by city.

        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel WHERE city = ?";
        Hotel obj;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, city);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cit = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String stars = rs.getString("stars");
                String facilityFeatures = rs.getString("facility_features");
                String pensionType = rs.getString("pension_type");
                int nightlyAdultPrice = rs.getInt("adult_price");
                int nightlyChildrenPrice = rs.getInt("children_price");
                int singleStock = rs.getInt("single_stock");
                int doubleStock = rs.getInt("double_stock");
                int suitStock = rs.getInt("suit_stock");
                obj = new Hotel(id, name, cit, district, address, email, phoneNumber, stars, facilityFeatures, pensionType, nightlyAdultPrice, nightlyChildrenPrice, singleStock, doubleStock, suitStock);
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public static Hotel getFetch(String hotelName) {
        // This method is used to get the hotel information from the database by hotel name.

        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, hotelName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setCity(rs.getString("city"));
                obj.setDistrict(rs.getString("district"));
                obj.setAddress(rs.getString("address"));
                obj.setEmail(rs.getString("email"));
                obj.setPhoneNumber(rs.getString("phone_number"));
                obj.setStars(rs.getString("stars"));
                obj.setFacilityFeatures(rs.getString("facility_features"));
                obj.setPensionType(rs.getString("pension_type"));
                obj.setNightlyAdultPrice(rs.getInt("adult_price"));
                obj.setNightlyChildrenPrice(rs.getInt("children_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean addStock(String room, String hotelName, int stock) {
        // This method is used to add stock to the hotel.

        String roomType = null;
        if (room.equals("Single")) {
            roomType = "single_stock";
        } else if (room.equals("Double")) {
            roomType = "double_stock";
        } else if (room.equals("Suit")) {
            roomType = "suit_stock";
        }
        String query = "UPDATE hotel SET " + roomType + " = ? WHERE name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, stock);
            pr.setString(2, hotelName);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", stars='" + stars + '\'' +
                ", facilityFeatures='" + facilityFeatures + '\'' +
                ", pensionType='" + pensionType + '\'' +
                ", nightlyAdultPrice=" + nightlyAdultPrice +
                ", nightlyChildrenPrice=" + nightlyChildrenPrice +
                ", singleStock=" + singleStock +
                ", doubleStock=" + doubleStock +
                ", suitStock=" + suitStock +
                '}';
    }

}