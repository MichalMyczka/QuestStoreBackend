package com.company.samuraiSatan.models;

import com.company.samuraiSatan.IO;
import com.company.samuraiSatan.dao.ArtifactDao;
import com.company.samuraiSatan.dao.ClassDao;
import com.company.samuraiSatan.dao.QuestDao;
import com.company.samuraiSatan.dao.UserDao;

import java.util.List;

public class Quest {

    private int questID;
    private String questName;
    private int reward;
    private boolean isActive;
    private String description;
    private boolean isBasic;

    public Quest(int questID, String name, int reward, boolean isActive, String description, boolean isBasic) {
        this.questID = questID;
        this.questName = name;
        this.reward = reward;
        this.isActive = isActive;
        this.description = description;
        this.isBasic = isBasic;
    }

    public int getQuestID() {
        return questID;
    }

    public String getQuestName() {
        return questName;
    }

    public int getReward() {
        return reward;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean getIsBasic() {
        return isBasic;
    }

    public void setName(String name) {
        this.questName = name;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_Basic(boolean is_Basic) {
        this.isBasic = is_Basic;
    }

}
