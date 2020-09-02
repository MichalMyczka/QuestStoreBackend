package com.company.samuraiSatan;

import java.util.Map;

public class MenuHandler {
    public boolean isRunning;
    private Map<Integer, Runnable> mainMenu;
    private String[] mainMenuList;
    private final IO io;
    private final UI ui;
    private CreepDao creepDao;
    private StudentDao studentDao;
    private MentorDao mentorDao;
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
        this.creepDao = new UserDao();
        this.mentorDao = new MentorDao();
        this.studentDao = new StudentDao();
    }

    private void mainMenu() {
        ui.displayMainMenu();
        ui.displayInLine(mainMenuList);
        int userChoice = io.gatherIntInput("\nEnter a number: ",1,3);
        mainMenu.get(userChoice).run();
    }

    private void login() {
        ui.clearScreen();
        String email = io.gatherInput("Enter Email: ");
        String password = io.gatherInput("Enter password: ");
        User user = userDao.getUser(email, password);
        isLogin = true;
        switch (user.getRole()) {
            case 1:
                initializeAdminMenu();
                adminPanel();
                break;
            case 2:
                initializeMentorMenu();
                mentorPanel();
            case 3:
                initializeStudentMenu();
                studentPanel();
        }
    }

    private void exit() {
        isRunning = false;
    }
}
