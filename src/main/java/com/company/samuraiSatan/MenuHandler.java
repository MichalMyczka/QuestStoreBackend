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
        switch (user.getRoleID()) {
            case 1 -> {
                initializeStudentMenu(user);
                studentPanel();
            }
            case 2 -> {
                initializeMentorMenu(user);
                mentorPanel();
            }
            case 3 -> {
                initializeCreepMenu(user);
                creepPanel();
            }
        }
    }

    private void exit() {
        isRunning = false;
    }

//----------------------------------------------------------------------------------------------------------------------
    private void initializeCreepMenu(User user) {
        creepMenu = new HashMap<>();
        creepMenu.put(1, user::createNewMentor);
        creepMenu.put(2, user::createNewClass);
        creepMenu.put(3, user::editMentorsProfile);
        creepMenu.put(4, user::createNewExperienceLvl);
//        creepMenu.put(5, user::createNewLevelOfExperience);
        creepMenu.put(6, this::isLogin);
    }

    private void creepPanel() {
        while (isLogin){
            ui.displayCreepMenu();
            int userChoice = io.gatherIntInput("\nEnter a number: ",1,6);
            creepMenu.get(userChoice).run();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    private void initializeMentorMenu(User user) {
        mentorMenu = new HashMap<>();
        mentorMenu.put(1, user::createNewCodecooler);
        mentorMenu.put(2, user::createNewQuest);
        mentorMenu.put(3, user::createNewArtifact);
        mentorMenu.put(4, this::updateQuestData);
        mentorMenu.put(5, this::updateArtifactData);
        mentorMenu.put(6, this::splitQuestType);
        mentorMenu.put(7, this::splitArtifactType);
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

    private void updateQuestData() {
        System.out.println("Editing Quest");
        questsDao.showAllQuests();
        List<Quest> quests = questsDao.getQuests();
        int id = io.gatherIntInput("Enter ID of quest to change: ",1, questsDao.getQuests().size());
        Quest quest = quests.get(id-1);
        String name = io.gatherInput("Enter new name of the quest: ");
        quest.setName(name);
        int reward = io.gatherIntInput("Enter new reward of the quest: ", (int) 1, 99999);
        quest.setReward(reward);
        String description = io.gatherInput("Enter new description of the quest: ");
        quest.setDescription(description);
        questsDao.updateQuest(quest);
    }

    private void splitQuestType() {
        System.out.println("Editing Quest");
        questsDao.showAllQuests();
        List<Quest> quests = questsDao.getQuests();
        int id = io.gatherIntInput("Enter ID of quest to change: ",1, questsDao.getQuests().size());
        Quest quest = quests.get(id-1);

        int basic = io.gatherIntInput("Change quest type: (1 - basic/ 2 - extra) ", (int) 1, 2);
        if (basic == 1) {
            quest.setIsBasic(true);
        }
        else {
            quest.setIsBasic(false);
        }
        questsDao.splitQuest(quest);
    }

    private void splitArtifactType() {
        System.out.println("Editing Artifact");
        artifactsDao.showAllArtifacts();
        List<Artifact> artifacts = artifactsDao.getArtifacts();
        int id = io.gatherIntInput("Enter ID of artifact to change: ",1, artifactsDao.getArtifacts().size());
        Artifact artifact = artifacts.get(id-1);

        int solo = io.gatherIntInput("Change artifact type: (1 - solo/ 2 - group) ", (int) 1, 2);
        if (solo == 1) {
            artifact.setSolo(true);
        }
        else {
            artifact.setSolo(false);
        }
        artifactsDao.splitArtifact(artifact);
    }

    private void updateArtifactData() {
        System.out.println("Editing Artifact");
        artifactsDao.showAllArtifacts();
        List<Artifact> artifacts = artifactsDao.getArtifacts();
        int id = io.gatherIntInput("Enter ID of artifact to change: ",1, questsDao.getQuests().size());
        Artifact artifact = artifacts.get(id-1);
        String name = io.gatherInput("Enter new name of the artifact: ");
        artifact.setArtifactName(name);
        int cost = io.gatherIntInput("Enter new reward of the quest: ", (int) 1, 99999);
        artifact.setCost(cost);
        String description = io.gatherInput("Enter new description of the artifact: ");
        artifact.setDescription(description);
        artifactsDao.updateArtifact(artifact);
    }

//----------------------------------------------------------------------------------------------------------------------
    private void initializeStudentMenu(User user) {
        studentMenu = new HashMap<>();
        studentMenu.put(1, userDao::showCodecoolerExp);
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
            studentMenu.get(userChoice).run();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
}
