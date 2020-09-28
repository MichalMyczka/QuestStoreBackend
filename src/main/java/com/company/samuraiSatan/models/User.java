package com.company.samuraiSatan.models;

import com.company.samuraiSatan.IO;
import com.company.samuraiSatan.dao.*;
import com.jakewharton.fliptables.FlipTable;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class User {
    private int userID;
    private String userName;
    private String userSurname;
    private String email;
    private Integer phone;
    private String password;
    private int roleID;
    private boolean isActive;
    private int userClassID;
    private int experienceLvlID;
    private int totalBalance;

    private final IO io = new IO();
    private final UserDao userDao = new UserDao();
    private final QuestDao questDao = new QuestDao();
    private final ArtifactDao artifactDao = new ArtifactDao();
    private final ClassDao classDao = new ClassDao();
    private final ExperienceDao experienceDao = new ExperienceDao();

    public User(int userID, String userName, String userSurname, String email, int phone, String password, int roleID, boolean isActive, int userClassID, int experienceLvlID, int totalBalance) {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.roleID = roleID;
        this.isActive = isActive;
        this.userClassID = userClassID;
        this.experienceLvlID = experienceLvlID;
        this.totalBalance = totalBalance;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public int getUserClassID() {
        return userClassID;
    }

    public int getExperienceLvlID() {
        return experienceLvlID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setUserClassID(int userClassID){this.userClassID = userClassID; }

    public void setExperienceLvlID(int experienceLvlID){this.experienceLvlID = experienceLvlID; }


//----------------------------------------------------------------------------------------------------------------------

    public void createNewMentor() {
        String name = io.gatherInput("Enter user name: ");
        String surname = io.gatherInput("Enter user surname: ");
        String email = io.gatherInput("Enter user email: ");
        int phone = io.gatherIntInput("Enter user phone: ",0, Integer.MAX_VALUE);
        String password = io.gatherInput("Enter user password: ");
        int role = 2;
        boolean is_Active = true;
        int userClassID = io.gatherIntInput("Enter user class ID: ",0, Integer.MAX_VALUE);
        int experienceLvlID = 1;
        int totalBalance = 0;
        try {
            User user = new User(0, name, surname, email, phone, password, role, isActive, userClassID, experienceLvlID, totalBalance);
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
        user.setUserName(name);
        String surname = io.gatherInput("Enter new surname of Mentor: ");
        user.setUserSurname(surname);
        String email = io.gatherInput("Enter new email of Mentor: ");
        user.setEmail(email);
        int phone = io.gatherIntInput("Enter new phone of Mentor: ",1, Integer.MAX_VALUE);
        user.setPhone(phone);
        String password = io.gatherInput("Enter new password of Mentor: ");
        user.setPassword(password);
        int classs = io.gatherIntInput("Enter new ID of class to assign Mentor: ",1, Integer.MAX_VALUE);
        user.setUserClassID(classs);
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
        boolean isActive = true;
        int userClassID = 1;
        int experienceLvlID = 1;
        try {
            User user = new User(0, name, surname, email, phone, password, role, isActive, userClassID, experienceLvlID, totalBalance);
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
        boolean isActive = true;
        boolean isBasic = false;
        try {
            Quest quest = new Quest(0, name, reward, isActive, description, isBasic);
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
        boolean isActive = true;
        boolean isSolo = false;
        try {
            Artifact artifact = new Artifact(0, name, cost, isActive, description, isSolo);
            artifactDao.addNewArtifact(artifact);
            io.gatherEmptyInput("Artifact successfully created!\nPress any ket to back to main menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------



}

