package com.company.samuraiSatan.models;

public class UserQuest {

    private int userQuestID;
    private int userID;
    private int questID;
    private int questStatusID;
    private int evaluatedByID;
    private String date;

    public UserQuest(int userQuestID, int userID, int questID, int questStatusID, int evaluatedByID, String date) {
        this.userQuestID = userQuestID;
        this.userID = userID;
        this.questID = questID;
        this.questStatusID = questStatusID;
        this.evaluatedByID = evaluatedByID;
        this.date = date;
    }

    public int getUserQuestID() {
        return userQuestID;
    }

    public void setUserQuestID(int userQuestID) {
        this.userQuestID = userQuestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public int getQuestStatusID() {
        return questStatusID;
    }

    public void setQuestStatusID(int questStatusID) {
        this.questStatusID = questStatusID;
    }

    public int getEvaluatedByID() {
        return evaluatedByID;
    }

    public void setEvaluatedByID(int evaluatedByID) {
        this.evaluatedByID = evaluatedByID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}