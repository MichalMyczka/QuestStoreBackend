package com.company.samuraiSatan.dao;
import java.sql.Connection;
import java.sql.DriverManager;

//public class PostgreSQLJDBC {
//    public static void main(String args\[\]) {
//        Connection c = null;
//        try {
//            // following line was needed for Java older than 1.6:
//            // Class.forName("org.postgresql.Driver"); // force classloader to load the driver
//            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "123"); // set user and password
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        System.out.println("Opened database successfully");
//    }
//}