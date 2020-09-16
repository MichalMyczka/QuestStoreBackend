package com.company.samuraiSatan.models;

public class ExperienceLvl {
    private int experienceLvl_ID;
    private String experienceName;
    private int experienceNeeded;

    public ExperienceLvl(int experienceLvl_id, String experienceName, int experienceNeeded) {
        this.experienceLvl_ID = experienceLvl_id;
        this.experienceName = experienceName;
        this.experienceNeeded = experienceNeeded;
    }

    public int getExperienceLvl_ID() {
        return experienceLvl_ID;
    }

    public void setExperienceLvl_ID(int experienceLvl_ID) {
        this.experienceLvl_ID = experienceLvl_ID;
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
