package com.example.oop.studentdb;

import java.sql.*;

public class StudentDb {
    static Connection con;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/myproject"; // Updated database name
    static String uname = "root";
    static String pass = "";

    public static Connection getConnection() throws Exception {
        if (con == null) {
            Class.forName(driver);
            con = DriverManager.getConnection(url, uname, pass);
        }
        return con;
    }
}
