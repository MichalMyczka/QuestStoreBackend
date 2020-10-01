package com.company.samuraiSatan.models;

public class UserArtifactGroup {

    int userGroupArtifactID;
    int artifactID;

    public UserArtifactGroup(int userGroupArtifactID, int artifactID){
        this.userGroupArtifactID = userGroupArtifactID;
        this.artifactID = artifactID;
    }

    public int getUserGroupArtifactID() {
        return userGroupArtifactID;
    }

    public void setUserGroupArtifactID(int userGroupArtifactID) {
        this.userGroupArtifactID = userGroupArtifactID;
    }

    public int getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(int artifactID) {
        this.artifactID = artifactID;
    }
}
