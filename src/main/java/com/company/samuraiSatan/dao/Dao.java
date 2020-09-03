package com.company.samuraiSatan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public abstract class Dao {
    protected Connection connection;
    protected Statement statement;

    public void connect() {
        try {
            // following line was needed for Java older than 1.6:
            // Class.forName("org.postgresql.Driver"); // force classloader to load the driver
            connection = DriverManager.getConnection("jdbc:postgresql://ec2-52-208-175-161.eu-west-1.compute.amazonaws.com:5432/daae0lv8si0pu9?sslmode=require", "iblrlqtsxotcnc", "1fabaa390b31e5c4e896ba5e297d50c7cb93a04322b8a7db105ec699959cb1f9");
            statement = connection.createStatement();
            System.out.println("polaczono z baza");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}