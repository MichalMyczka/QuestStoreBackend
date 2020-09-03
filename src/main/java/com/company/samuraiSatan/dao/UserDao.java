package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDao extends Dao {

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Users;");
            while (results.next()) {
                users.add(createUser(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User createUser(ResultSet results) throws SQLException {
        int user_ID = results.getInt("User_ID");
        String user_Name = results.getString("Name");
        String user_Surname = results.getString("Surname");
        int phone = results.getInt("Phone");
        String email = results.getString("Email");
        int role_ID = results.getInt("role_ID");
        String password = results.getString("Password");
        int balance = results.getInt("Balance");
        boolean is_Active = results.getBoolean("Is_Active");
        int purchased = results.getInt("Purchased");
        return new User(user_ID, user_Name, user_Surname, phone, email, role_ID, password, balance, is_Active, purchased);
    }

    public User getUser(String email, String password) {
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE email = ? AND password = ?;");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();
            List<User> users = getUsers();
            int indexDifference = 1;
            int user_ID = results.getInt("User_ID") - indexDifference;
            results.close();
            statement.close();
            connection.close();
            return users.get(user_ID);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new NoSuchElementException("There isn't user with specified data in database");
    }

    public void addUser(User user) {
        connect();
        PreparedStatement addUser;
        String sql = "INSERT INTO Users (Name, Surname, Phone, Email, Role_ID, Password, Balance, Is_Active, Purchased) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            addUser = connection.prepareStatement(sql);
            addUser.setString(1, user.getUser_Name());
            addUser.setString(2, user.getUser_Name());
            addUser.setInt(3, user.getPhone());
            addUser.setString(3, user.getEmail());
            addUser.setInt(4, user.getRole_ID());
            addUser.setString(4, user.getPassword());
            addUser.setInt(5, user.getBalance());
            addUser.setBoolean(6, user.getIs_Active());
            addUser.setInt(7, user.getPurchased());
            addUser.executeUpdate();
            addUser.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

