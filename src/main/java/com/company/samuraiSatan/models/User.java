package com.company.samuraiSatan.models;

import com.company.samuraiSatan.IO;
import com.company.samuraiSatan.dao.*;
import com.jakewharton.fliptables.FlipTable;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class User {
    private int user_ID;
    private String user_Name;
    private String user_Surname;
    private String email;
    private Integer phone;
    private String password;
    private int role_ID;
    private boolean is_Active;
    private int userClass_ID;
    private int experienceLvl_ID;
    private int totalBalance;

    private final IO io = new IO();
    private final UserDao userDao = new UserDao();
    private final QuestDao questDao = new QuestDao();
    private final ArtifactDao artifactDao = new ArtifactDao();
    private final ClassDao classDao = new ClassDao();
    private final ExperienceDao experienceDao = new ExperienceDao();

    public User(int user_ID, String user_Name, String user_Surname, String email, int phone, String password, int role_ID, boolean is_Active, int userClass_ID, int experienceLvl_ID, int totalBalance) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.user_Surname = user_Surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role_ID = role_ID;
        this.is_Active = is_Active;
        this.userClass_ID = userClass_ID;
        this.experienceLvl_ID = experienceLvl_ID;
        this.totalBalance = totalBalance;
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

    public int getTotalBalance() {
        return totalBalance;
    }

    public boolean getIs_Active() {
        return is_Active;
    }

    public int getUserClass_ID() {
        return userClass_ID;
    }

    public int getExperienceLvl_ID() {
        return experienceLvl_ID;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setUser_Surname(String user_Surname) {
        this.user_Surname = user_Surname;
    }

    public void setPhone(Integer phone) {
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

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public void setUserClass_ID(int userClass_ID){this.userClass_ID = userClass_ID; }

    public void setExperienceLvl_ID(int experienceLvl_ID){this.experienceLvl_ID = experienceLvl_ID; }


//----------------------------------------------------------------------------------------------------------------------

    public void createNewMentor() {
        String name = io.gatherInput("Enter user name: ");
        String surname = io.gatherInput("Enter user surname: ");
        String email = io.gatherInput("Enter user email: ");
        int phone = io.gatherIntInput("Enter user phone: ",0, Integer.MAX_VALUE);
        String password = io.gatherInput("Enter user password: ");
        int role = 2;
        boolean is_Active = true;
        int userClass_ID = io.gatherIntInput("Enter user class ID: ",0, Integer.MAX_VALUE);
        int experienceLvl_ID = 1;
        int totalBalance = 0;
        try {
            User user = new User(0, name, surname, email, phone, password, role, is_Active, userClass_ID, experienceLvl_ID, totalBalance);
            userDao.addUser(user);
            io.gatherEmptyInput("Account successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editMentorsProfile() {
        System.out.println("Editing mentor's profile");
        userDao.showAllMentors();
        List<User> users = userDao.getUsers();
        int id = io.gatherIntInput("Enter ID of Mentor to change his profile: ",1, userDao.getUsers().size());
        User user = users.get(id-1);
        String name = io.gatherInput("Enter new name of Mentor: ");
        user.setUser_Name(name);
        String surname = io.gatherInput("Enter new surname of Mentor: ");
        user.setUser_Surname(surname);
        String email = io.gatherInput("Enter new email of Mentor: ");
        user.setEmail(email);
        int phone = io.gatherIntInput("Enter new phone of Mentor: ",1, Integer.MAX_VALUE);
        user.setPhone(phone);
        String password = io.gatherInput("Enter new password of Mentor: ");
        user.setPassword(password);
        int classs = io.gatherIntInput("Enter new ID of class to assign Mentor: ",1, Integer.MAX_VALUE);
        user.setUserClass_ID(classs);
        userDao.updateMentor(user);
    }

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

    public void createNewExperienceLvl() {
        System.out.println("Adding new experience lvl");
        String name = io.gatherInput("Enter experience lvl name: ");
        int expNeeded = io.gatherIntInput("Enter experience needed", 0 , Integer.MAX_VALUE);
        try {
            ExperienceLvl experienceLvl = new ExperienceLvl(0, name, expNeeded);
            experienceDao.addExperienceLvl(experienceLvl);
            io.gatherEmptyInput("Experience Lvl successfully created!\nPress any ket to back to main menu");
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
        int totalBalance = 0;
        boolean is_Active = true;
        int userClass_ID = 1;
        int experienceLvl_ID = 1;
        try {
            User user = new User(0, name, surname, email, phone, password, role, is_Active, userClass_ID, experienceLvl_ID, totalBalance);
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
        boolean is_Basic = false;
        try {
            Quest quest = new Quest(0, name, reward, is_Active, description, is_Basic);
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
        boolean is_Active = true;
        boolean is_Solo = false;
        try {
            Artifact artifact = new Artifact(0, name, cost, is_Active, description, is_Solo);
            artifactDao.addNewArtifact(artifact);
            io.gatherEmptyInput("Artifact successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

