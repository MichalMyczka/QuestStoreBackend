package com.company.samuraiSatan;

public class Main {
    public static void main(String args[]) {
        MenuHandler menuHandler = new MenuHandler();
        while (menuHandler.isRunning) {
            menuHandler.mainMenu();
        }
    }
}
