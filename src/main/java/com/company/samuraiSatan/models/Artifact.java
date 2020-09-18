package com.company.samuraiSatan.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.company.samuraiSatan.dao.ArtifactDao;

public class Artifact {
    private int id;
    private String name;
    private int cost;
    private boolean isActive;
    private String description;
    private boolean isSolo;

    public Artifact(int id, String name, int cost, boolean isActive, String description, boolean isSolo) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.isActive = isActive;
        this.description = description;
        this.isSolo = isSolo;
    }

    public int getArtifact_ID() {
        return id;
    }

    public String getArtifact_Name() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean getActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }

    public boolean getSolo() {
        return isSolo;
    }

    public void setArtifact_Name(String artifact_Name) {
        this.name = artifact_Name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSolo(boolean solo) {
        this.isSolo = solo;
    }

}
