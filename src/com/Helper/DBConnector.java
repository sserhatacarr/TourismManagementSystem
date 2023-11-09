package com.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    // This class is used to connect to the database.
    private Connection connection = null;

    public Connection connectDB(){
        try {
            this.connection = DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME,Config.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    public static Connection getInstance(){
        DBConnector dbConnector = new DBConnector();
        return dbConnector.connectDB();
    }
}
