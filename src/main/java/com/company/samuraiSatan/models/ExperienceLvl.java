package com.company.samuraiSatan.models;

public class ExperienceLvl {
    private int experienceLvlID;
    private String experienceName;
    private int experienceNeeded;

    public ExperienceLvl(int experienceLvlID, String experienceName, int experienceNeeded) {
        this.experienceLvlID = experienceLvlID;
        this.experienceName = experienceName;
        this.experienceNeeded = experienceNeeded;
    }

    public int getExperienceLvl_ID() {
        return experienceLvlID;
    }

    public void setExperienceLvlID(int experienceLvlID) {
        this.experienceLvlID = experienceLvlID;
    }

    public String getExperienceName() {
        return experienceName;
    }

    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    public int getExperienceNeeded() {
        return experienceNeeded;
    }

    public void setExperienceNeeded(int experienceNeeded) {
        this.experienceNeeded = experienceNeeded;
    }

}
