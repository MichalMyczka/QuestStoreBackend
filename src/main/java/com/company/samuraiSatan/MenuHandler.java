package com.company.samuraiSatan;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.company.samuraiSatan.dao.QuestDao;
import com.company.samuraiSatan.dao.UserDao;
import com.company.samuraiSatan.dao.ArtifactDao;
import com.company.samuraiSatan.models.Artifact;
import com.company.samuraiSatan.models.User;
import com.company.samuraiSatan.models.Quest;

public class MenuHandler {
    public boolean isRunning;
    private Map<Integer, Runnable> mainMenu;
    private String[] mainMenuList;
    private final IO io;
    private final UI ui;
    private UserDao userDao;
    private QuestDao questsDao;
    private ArtifactDao artifactsDao;
    private Map<Integer, Runnable> creepMenu;
    private Map<Integer, Runnable> studentMenu;
    private Map<Integer, Runnable> mentorMenu;
    private boolean isLogin;

    public MenuHandler() {
        this.isRunning = true;
        this.ui = new UI();
        this.io = new IO();
        initializeDao();
        initializeMainMenu();
    }

    private void initializeDao() {
        this.userDao = new UserDao();
        this.questsDao = new QuestDao();
        this.artifactsDao = new ArtifactDao();
    }

    private void isLogin() {
        isLogin = false;
        System.out.println("You'll be logged out.");
    }

//----------------------------------------------------------------------------------------------------------------------

    private void initializeMainMenu() {
        mainMenuList = new String[] {"1. Login", "2. Exit"};
        mainMenu = new HashMap<>();
        mainMenu.put(1, this::login);
        mainMenu.put(2, this::exit);
    }

    public void mainMenu() {
        ui.displayMainMenu();
        ui.displayInLine(mainMenuList);
        int userChoice = io.gatherIntInput("\nEnter a number: ",1,2);
        mainMenu.get(userChoice).run();
    }

    private void login() {
        ui.clearScreen();
        String email = io.gatherInput("Enter Email: ");
        String password = io.gatherInput("Enter password: ");
        User user = userDao.getUser(email, password);
        isLogin = true;
        switch (user.getRole_ID()) {
            case 1:
                initializeStudentMenu(user);
                studentPanel();
                break;
            case 2:
                initializeMentorMenu(user);
                mentorPanel();
            case 3:
                initializeCreepMenu(user);
                creepPanel();
        }
    }

    private void exit() {
        isRunning = false;
    }

//----------------------------------------------------------------------------------------------------------------------
//funkcje tutaj czy gdzies indziej???
    private void initializeCreepMenu(User user) {
        creepMenu = new HashMap<>();
        creepMenu.put(1, user::createNewUser);
//        creepMenu.put(2, user::createNewClass);
//        creepMenu.put(3, user::editMentorProfile);
//        creepMenu.put(4, user::showMentorProfile);
//        creepMenu.put(5, user::createNewLevelOfExperience);
        creepMenu.put(6, this::isLogin);
    }

    private void creepPanel() {
        while (isLogin){
            ui.displayCreepMenu();
            int userChoice = io.gatherIntInput("\nEnter a number: ",1,6);
        }
    }

//----------------------------------------------------------------------------------------------------------------------
//funkcje tutaj czy gdzies indziej???
    private void initializeMentorMenu(User user) {
        mentorMenu = new HashMap<>();
//        mentorMenu.put(1, user::createNewUser);
//        mentorMenu.put(2, user::addNewQuest);
//        mentorMenu.put(3, user::addNewArtifact);
//        mentorMenu.put(4, user::updateQuest);
//        mentorMenu.put(5, user::updateArtifact);
//        mentorMenu.put(6, user::markQuestAsDone);
//        mentorMenu.put(7, user::showStudentWallet);
        mentorMenu.put(8, this::isLogin);
    }

    private void mentorPanel() {
        while (isLogin) {
            ui.displayMentorMenu();
            int userChoice = io.gatherIntInput("\nEnter a number: ",1,8);
            mentorMenu.get(userChoice).run();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
//funkcje tutaj czy gdzies indziej???
    private void initializeStudentMenu(User user) {
        studentMenu = new HashMap<>();
//        studentMenu.put(1, user::showMyWallet);
//        studentMenu.put(2, user::buyArtifact);
//        studentMenu.put(3, user::showMyLevelOfExperience);
//        studentMenu.put(4, user::showAvailableQuests);
//        studentMenu.put(5, user::sumbitQuest);
        studentMenu.put(6, this::isLogin);
    }

    private void studentPanel() {
        while (isLogin) {
            ui.displayStudentMenu();
            int userChoice = io.gatherIntInput("\nenter a number: ",1,6);
        }
    }

//----------------------------------------------------------------------------------------------------------------------
}
