package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Quest;
import com.company.samuraiSatan.models.User;
import com.jakewharton.fliptables.FlipTableConverters;

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
            ResultSet results = statement.executeQuery("SELECT * FROM users ORDER BY \"User_ID\"");
            while (results.next()) {
                users.add(createUser(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User createUser(ResultSet results) throws SQLException {
        int userID = results.getInt("User_ID");
        String userName = results.getString("Name");
        String userSurname = results.getString("Surname");
        String email = results.getString("Email");
        Integer phone = results.getInt("Phone");
        String password = results.getString("Password");
        int roleID = results.getInt("Role_ID");
        int totalBalance = results.getInt("TotalBalance");
        boolean isActive = results.getBoolean("Is_Active");
        int userClassID = results.getInt("UserClass_ID");
        int experienceLvlID = results.getInt("ExperienceLvl_ID");
        return new User(userID, userName, userSurname,email, phone, password, roleID, isActive, userClassID, experienceLvlID, totalBalance);
    }

    public User getUser(String email, String password) {
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE \"Email\" = ? AND \"Password\" = ? ORDER BY \"User_ID\"");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();
            results.next();
            List<User> users = getUsers();
            int indexDifference = 1;
            int userID = results.getInt("User_ID") - indexDifference;
            results.close();
            statement.close();
            connection.close();
            return users.get(userID);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoSuchElementException("There isn't user with specified data in database");
        }
    }

    public void addUser(User user) {
        connect();
        PreparedStatement addUser;
        String sql = "INSERT INTO users (\"Name\", \"Surname\",\"Email\", \"Phone\",\"Password\", \"Role_ID\", \"Is_Active\", \"UserClass_ID\", \"ExperienceLvl_ID\", \"TotalBalance\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            addUser = connection.prepareStatement(sql);
            addUser.setString(1, user.getUserName());
            addUser.setString(2, user.getUserSurname());
            addUser.setString(3, user.getEmail());
            addUser.setInt(4, user.getPhone());
            addUser.setString(5, user.getPassword());
            addUser.setInt(6, user.getRoleID());
            addUser.setBoolean(7, user.getIsActive());
            addUser.setInt(8, user.getUserClassID());
            addUser.setInt(9, user.getExperienceLvlID());
            addUser.setInt(10, user.getTotalBalance());

            addUser.executeUpdate();
            addUser.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    public void showAllMentors() {
        String sql = "SELECT * FROM users WHERE \"Role_ID\" = 2 ORDER BY \"User_ID\"";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
    //        System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMentor(User user) {
        PreparedStatement editMentor;
        connect();
        String sql = "UPDATE users SET \"Name\" = ?,\"Surname\" = ?, \"Email\" = ?, \"Phone\" = ?, \"Password\" = ?, \"UserClass_ID\" = ?  WHERE \"User_ID\" = ?";
        try {
            editMentor = connection.prepareStatement(sql);
            editMentor.setString(1, user.getUserName());
            editMentor.setString(2, user.getUserSurname());
            editMentor.setString(3, user.getEmail());
            editMentor.setInt(4, user.getPhone());
            editMentor.setString(5, user.getPassword());
            editMentor.setInt(6, user.getUserClassID());
            editMentor.setInt(7, user.getUserID());
            editMentor.executeUpdate();
            editMentor.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    public void showCodecoolerExp() {
        String sql = "SELECT \"User_ID\" ,\"Name\",\"Surname\",\"ExperienceLvl_ID\" FROM users WHERE \"Role_ID\" = 1";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

