package com.company.samuraiSatan.models;

public class UserArtifactSolo {

    int userSoloArtifactID;
    int artifactID;
    int userID;

    public UserArtifactSolo(int userSoloArtifact, int artifactID, int userID){
        this.userSoloArtifactID = userSoloArtifact;
        this.artifactID = artifactID;
        this.userID = userID;
    }

    public int getUserSoloArtifactID() {
        return userSoloArtifactID;
    }

    public void setUserSoloArtifactID(int userSoloArtifact) {
        this.userSoloArtifactID = userSoloArtifact;
    }

    public int getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(int artifactID) {
        this.artifactID = artifactID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
