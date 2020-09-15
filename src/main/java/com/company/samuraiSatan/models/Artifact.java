package com.company.samuraiSatan.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.company.samuraiSatan.dao.ArtifactDao;

public class Artifact {
    private int artifact_ID;
    private String artifactName;
    private int cost;
    private boolean is_Active;
    private String description;
    private boolean is_Solo;

    public Artifact(int artifact_ID, String artifactName, int cost, boolean is_Active, String description, boolean is_Solo) {
        this.artifact_ID = artifact_ID;
        this.artifactName = artifactName;
        this.cost = cost;
        this.is_Active = is_Active;
        this.description = description;
        this.is_Solo = is_Solo;
    }

    public int getArtifact_ID() {
        return artifact_ID;
    }

    public String getArtifact_Name() {
        return artifactName;
    }

    public int getCost() {
        return cost;
    }

    public boolean getIs_Active() {
        return is_Active;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIs_Solo() {
        return is_Solo;
    }

    public void setArtifact_Name(String artifact_Name) {
        this.artifactName = artifact_Name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_Solo(boolean is_Solo) {
        this.is_Solo = is_Solo;
    }

}
