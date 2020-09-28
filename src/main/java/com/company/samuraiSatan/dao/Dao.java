package com.company.samuraiSatan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Map;

import com.company.samuraiSatan.helpers.JSONreader;

public abstract class Dao {
    protected Connection connection;
    protected Statement statement;
    private JSONreader jsonReader;


    public Dao() {
        jsonReader = new JSONreader();
    }

    public void connect() {
        Map<String, String> data = jsonReader.JSONread();
        try {
            connection = DriverManager.getConnection(
                    data.get("connection"),
                    data.get("user"),
                    data.get("password")
                    );
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