package com.company.samuraiSatan.models;

public class Quest {

    private int quest_ID;
    private String quest_Name;
    private int reward;
    private boolean is_Active;
    private String description;
    private boolean is_Done;
    private boolean evaluation;
    private boolean is_Basic;

    public Quest(int quest_ID, String name, int reward, boolean is_Active, String description, boolean is_Done, boolean evaluation, boolean is_Basic) {
        this.quest_ID = quest_ID;
        this.quest_Name = name;
        this.reward = reward;
        this.is_Active = is_Active;
        this.description = description;
        this.is_Done = is_Done;
        this.evaluation = evaluation;
        this.is_Basic = is_Basic;
    }

    public int getQuest_ID() {
        return quest_ID;
    }

    public String getQuest_Name() {
        return quest_Name;
    }

    public int getReward() {
        return reward;
    }

    public boolean isIs_Active() {
        return is_Active;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIs_Done() {
        return is_Done;
    }

    public boolean getEvaluation() {
        return evaluation;
    }
    
    public boolean getIs_Basic() {
        return is_Basic;
    }

    public void setName(String name) {
        this.quest_Name = name;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_Done(boolean is_Done) {
        this.is_Done = is_Done;
    }

    public void setIs_Basic(boolean is_Basic) {
        this.is_Basic = is_Basic;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }
}
