package com.company.samuraiSatan;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String args[]) {
        Connection c = null;
        try {
            // following line was needed for Java older than 1.6:
            // Class.forName("org.postgresql.Driver"); // force classloader to load the driver
            c = DriverManager.getConnection("jdbc:postgresql://ec2-52-208-175-161.eu-west-1.compute.amazonaws.com:5432/daae0lv8si0pu9", "iblrlqtsxotcnc", "1fabaa390b31e5c4e896ba5e297d50c7cb93a04322b8a7db105ec699959cb1f9"); // set user and password
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
