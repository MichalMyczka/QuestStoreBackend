package com.company.samuraiSatan.models;

import com.company.samuraiSatan.IO;
import com.company.samuraiSatan.dao.ArtifactDao;
import com.company.samuraiSatan.dao.ClassDao;
import com.company.samuraiSatan.dao.QuestDao;
import com.jakewharton.fliptables.FlipTable;
import com.company.samuraiSatan.dao.UserDao;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class User {
    private int user_ID;
    private String user_Name;
    private String user_Surname;
    private int phone;
    private String email;
    private int role_ID;
    private String password;
    private int balance;
    private boolean is_Active;
    private String purchased;
    private final IO io = new IO();
    private final UserDao userDao = new UserDao();
    private final QuestDao questDao = new QuestDao();
    private final ArtifactDao artifactDao = new ArtifactDao();
    private final ClassDao classDao = new ClassDao();

    public User(int user_ID, String user_Name, String user_Surname, int phone, String email, int role_ID, String password, int balance, boolean is_Active, String purchased) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.user_Surname = user_Surname;
        this.phone = phone;
        this.email = email;
        this.role_ID = role_ID;
        this.password = password;
        this.balance = balance;
        this.is_Active = is_Active;
        this.purchased = purchased;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String getUser_Surname() {
        return user_Surname;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getRole_ID() {
        return role_ID;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public boolean getIs_Active() {
        return is_Active;
    }

    public String getPurchased() {
        return purchased;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setUser_Surname(String user_Surname) {
        this.user_Surname = user_Surname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public void setPurchased(String purchased) {
        this.purchased = purchased;
    }

//----------------------------------------------------------------------------------------------------------------------

    public void createNewMentor() {
        String name = io.gatherInput("Enter user name: ");
        String surname = io.gatherInput("Enter user surname: ");
        String password = io.gatherInput("Enter user password: ");
        int phone = io.gatherIntInput("Enter user phone: ",0, Integer.MAX_VALUE);
        String email = io.gatherInput("Enter user email: ");
        int role = 2;
        int balance = 0;
        boolean is_Active = true;
        String purchased = null;
        try {
            User user = new User(0, name, surname, phone, email, role, password, balance, is_Active, purchased);
            userDao.addUser(user);
            io.gatherEmptyInput("Account successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void editMentorsProfile() {
//        System.out.println("Editing mentor's profile");
//        userDao.showAllMentors();
//        List<User> users = userDao.getUsers();
//        int id = io.gatherIntInput("Enter ID of Mentor to change his profile: ",1, userDao.getUsers().size());
//        User user = users.get(id-1);
//        String email = io.gatherInput("Enter new email of Mentor: ");
//        user.setEmail(email);
//        int classs = io.gatherIntInput("Enter new ID of class to assign Mentor: ",1)
//        userDao.updateMentor(user);
//    }

    public void createNewClass() {
        System.out.println("Adding new class");
        String name = io.gatherInput("Enter class name: ");
        try {
            Class classs = new Class(0, name);
            classDao.addClass(classs);
            io.gatherEmptyInput("Class successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //-------------------------------------------------------------------------

    public void createNewCodecooler() {
        String name = io.gatherInput("Enter user name: ");
        String surname = io.gatherInput("Enter user surname: ");
        String password = io.gatherInput("Enter user password: ");
        int phone = io.gatherIntInput("Enter user phone: ",0, Integer.MAX_VALUE);
        String email = io.gatherInput("Enter user email: ");
        int role = 1;
        int balance = 0;
        boolean is_Active = true;
        String purchased = null;
        try {
            User user = new User(0, name, surname, phone, email, role, password, balance, is_Active, purchased);
            userDao.addUser(user);
            io.gatherEmptyInput("Account successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewQuest() {
        String name = io.gatherInput("Enter quest name: ");
        int reward = io.gatherIntInput("Enter reward: ", 0, Integer.MAX_VALUE);
        String description = io.gatherInput("Enter quest description: ");
        boolean is_Active = true;
        boolean is_Done = false;
        boolean evaluation = false;
        boolean is_Basic = false;
        try {
            Quest quest = new Quest(0, name, reward, is_Active, description, is_Done, evaluation, is_Basic);
            questDao.addQuest(quest);
            io.gatherEmptyInput("Quest successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewArtifact() {
        String name = io.gatherInput("Enter artifact name: ");
        int cost = io.gatherIntInput("Enter cost: ", 0, Integer.MAX_VALUE);
        String description = io.gatherInput("Enter Artifact description: ");
        int collected = 0;
        boolean is_Active = true;
        boolean is_Solo = false;
        boolean is_Used = false;
        try {
            Artifact artifact = new Artifact(0, name, cost, is_Active, description, is_Solo, collected, is_Used);
            artifactDao.addNewArtifact(artifact);
            io.gatherEmptyInput("Artifact successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

