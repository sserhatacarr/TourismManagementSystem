package com.Model;

import com.Helper.DBConnector;
import com.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Room {
    // This class is used to create a room.
    private int id;
    private String hotelName;
    private String roomType;
    private int stock;
    private String bedCount;
    private String television;
    private String minibar;
    private String gameConsole;
    private String squareMeter;
    private String till;
    private String projection;

    public Room(){
    }

    public Room(int id, String hotelName, String roomType, int stock, String bedCount, String television, String minibar, String gameConsole, String squareMeter, String till, String projection) {
        this.id = id;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.stock = stock;
        this.bedCount = bedCount;
        this.television = television;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.squareMeter = squareMeter;
        this.till = till;
        this.projection = projection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelID) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBedCount() {
        return bedCount;
    }

    public void setBedCount(String bedCount) {
        this.bedCount = bedCount;
    }

    public String getTelevision() {
        return television;
    }

    public void setTelevision(String television) {
        this.television = television;
    }

    public String getMinibar() {
        return minibar;
    }

    public void setMinibar(String minibar) {
        this.minibar = minibar;
    }

    public String getGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(String gameConsole) {
        this.gameConsole = gameConsole;
    }

    public String getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(String squareMeter) {
        this.squareMeter = squareMeter;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public static boolean add(String hotelName, String roomType, int stock, String bedCount, String television, String minibar, String gameConsole, String squareMeter, String till, String projection) {
        // This method is used to add a room to the database.

        String query = "INSERT INTO room (hotel_name, room_type, stock, bed_count, television, minibar, game_console, square_meter, till, projection) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, hotelName);
            pr.setString(2, roomType);
            pr.setInt(3,stock);
            pr.setString(4, bedCount);
            pr.setString(5, television);
            pr.setString(6, minibar);
            pr.setString(7, gameConsole);
            pr.setString(8, squareMeter);
            pr.setString(9, till);
            pr.setString(10, projection);
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

    public static Room getFetch(String hotelName, String roomType){
        // This method is used to get a room from the database.

        Room obj = null;
        String query = "SELECT * FROM room WHERE hotel_name = ? AND room_type = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hotelName);
            pr.setString(2,roomType);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setBedCount(rs.getString("bed_count"));
                obj.setTelevision(rs.getString("television"));
                obj.setMinibar(rs.getString("minibar"));
                obj.setGameConsole(rs.getString("game_console"));
                obj.setSquareMeter(rs.getString("square_meter"));
                obj.setTill(rs.getString("till"));
                obj.setProjection(rs.getString("projection"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Room> getFetchAll(String hotelName){
        // This method is used to get all rooms from the database.

        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room WHERE hotel_name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hotelName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                Room obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setHotelName(rs.getString("hotel_name"));
                obj.setRoomType(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setBedCount(rs.getString("bed_count"));
                obj.setTelevision(rs.getString("television"));
                obj.setMinibar(rs.getString("minibar"));
                obj.setGameConsole(rs.getString("game_console"));
                obj.setSquareMeter(rs.getString("square_meter"));
                obj.setTill(rs.getString("till"));
                obj.setProjection(rs.getString("projection"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static boolean deleteStock(int roomCount,String roomType, String hotelName){
        // This method is used to delete a room from the database.

        String query = "UPDATE room SET stock = stock - ? WHERE room_type = ? AND hotel_name = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, roomCount);
            pr.setString(2, roomType);
            pr.setString(3, hotelName);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}