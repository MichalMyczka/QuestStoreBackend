package com.company.samuraiSatan;

import java.util.Map;
import java.util.HashMap;

public class UI {
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayMainMenu(){
        clearScreen();
        System.out.println("WELCOME TO QUEST STORE");
        System.out.println("MAIN MENU: ");
    }

    public void displayInLine(String [] strings){
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public void displayCreepMenu(){
        clearScreen();
        Map<Integer, String> creepMenu = makeCreepChoiceMap();
        System.out.println("Creep MENU: ");
        creepMenu.forEach((k,v) -> System.out.println(k+". "+v));
    }

    public void displayStudentMenu(){
        clearScreen();
        Map<Integer, String> studentMenu = makeStudentChoiceMap();
        System.out.println("Student MENU: ");
        studentMenu.forEach((k,v) -> System.out.println(k+". "+v));
    }
    public void displayMentorMenu(){
        clearScreen();
        Map<Integer, String> mentorMenu = makeMentorChoiceMap();
        System.out.println("Mentor MENU: ");
        mentorMenu.forEach((k,v) -> System.out.println(k+". "+v));
    }

    private Map<Integer, String> makeCreepChoiceMap() {
        Map<Integer, String> creepChoiceMap = new HashMap<>();
        creepChoiceMap.put(1, "Create Mentor account");
        creepChoiceMap.put(2, "Create class");
        creepChoiceMap.put(3, "Edit Mentor's profile");
        creepChoiceMap.put(4, "Show Mentor's profile");
        creepChoiceMap.put(5, "Create new level of experience");
        creepChoiceMap.put(6, "Logout");
        return creepChoiceMap;
    }

    private Map<Integer, String> makeMentorChoiceMap() {
        Map<Integer, String> mentorChoiceMap = new HashMap<>();
        mentorChoiceMap.put(1, "Create new Student account");
        mentorChoiceMap.put(2, "Add new quest");
        mentorChoiceMap.put(3, "Add new artifact to store");
        mentorChoiceMap.put(4, "Update quests");
        mentorChoiceMap.put(5, "Update artifacts");
        mentorChoiceMap.put(6, "Mark quest as done");
        mentorChoiceMap.put(7, "Show Student's wallet");
        mentorChoiceMap.put(8, "Logout");
        return mentorChoiceMap;
    }

    private Map<Integer, String> makeStudentChoiceMap() {
        Map<Integer, String> studentChoiceMap = new HashMap<>();
        studentChoiceMap.put(1, "Show my wallet");
        studentChoiceMap.put(2, "But artifact");
        studentChoiceMap.put(3, "Show my lever of experience");
        studentChoiceMap.put(4, "Show available quests");
        studentChoiceMap.put(5, "Submit a quest");
        studentChoiceMap.put(6, "Logout");
        return studentChoiceMap;
    }
}

