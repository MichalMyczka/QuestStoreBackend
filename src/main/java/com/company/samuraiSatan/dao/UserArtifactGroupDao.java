package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.UserArtifactGroup;
import com.company.samuraiSatan.models.UserArtifactSolo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserArtifactGroupDao extends Dao{

    public List<UserArtifactGroup> getUserGroupArtifacts() {
        List<UserArtifactGroup> userArtifactGroup = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM \"userGroupArtifacts\" ORDER BY \"UserGroupArtifact_ID\";");
            while (results.next()) {
                userArtifactGroup.add(createArtifactGroup(results));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userArtifactGroup;
    }

    public UserArtifactGroup createArtifactGroup(ResultSet results) throws SQLException {
        int userGroupArtifactID = results.getInt("UserGroupArtifact_ID");
        int artifactID = results.getInt("Artifact_ID");

        return new UserArtifactGroup(userGroupArtifactID, artifactID);
    }

    public void addUserArtifactGroup(UserArtifactGroup userArtifactGroup) {
        connect();
        PreparedStatement addNewQuest;
        String sql = "INSERT INTO \"userGroupArtifacts\" (\"Artifact_ID\") VALUES (?)";
        try {
            addNewQuest = connection.prepareStatement(sql);
            addNewQuest.setInt(1, userArtifactGroup.getArtifactID());
            addNewQuest.executeUpdate();
            addNewQuest.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
