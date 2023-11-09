package com.Model;

import com.Helper.DBConnector;
import com.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    // This class is used to create a user.
    private int id;
    private String first_name;
    private String last_name;
    private String uname;
    private String email;
    private String pass;
    private String type;

    public User(){}

    public User(int id, String first_name, String last_name, String uname, String email, String pass,String type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.type = type;
    }

    public static User getFetch(String uname_email, String pass){
        // This method is used to fetch a user from the database.

        User obj = null;
        String query = "SELECT * FROM user WHERE uname=? AND password=?";
        if (uname_email.contains("@")){
            query = "SELECT * FROM user WHERE email=? AND password=?";
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname_email);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new User(rs.getInt("id"),rs.getString("first_name"),
                        rs.getString("last_name"),rs.getString("uname"),
                        rs.getString("email"),rs.getString("password"),rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public static ArrayList<User> getList() {
        // This method is used to get the list of users.
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setFirst_name(rs.getString("first_name"));
                obj.setLast_name(rs.getString("last_name"));
                obj.setUname(rs.getString("uname"));
                obj.setEmail(rs.getString("email"));
                obj.setPass(rs.getString("password"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public static void delete(int id) {
        // This method is used to delete a user from the database.
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement("DELETE FROM user WHERE id=?");
            pr.setInt(1,id);
            pr.executeUpdate();
            Helper.showMsg("User deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
