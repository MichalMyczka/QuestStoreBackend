package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.UserArtifactSolo;
import com.company.samuraiSatan.models.UserQuest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserArtifactSoloDao extends Dao{

    public List<UserArtifactSolo> getUserSoloArtifacts() {
        List<UserArtifactSolo> userArtifactSolo = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM \"userSoloArtifacts\" ORDER BY \"UserSoloArtifact_ID\";");
            while (results.next()) {
                userArtifactSolo.add(createArtifactSolo(results));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userArtifactSolo;
    }

    public UserArtifactSolo createArtifactSolo(ResultSet results) throws SQLException {
        int userSoloArtifactID = results.getInt("UserSoloArtifact_ID");
        int artifactID = results.getInt("Artifact_ID");
        int userID = results.getInt("User_ID");


        return new UserArtifactSolo(userSoloArtifactID, artifactID, userID);
    }

    public void addUserArtifactSolo(UserArtifactSolo userArtifactSolo) {
        connect();
        PreparedStatement addNewQuest;
        String sql = "INSERT INTO \"userSoloArtifacts\" (\"Artifact_ID\", \"User_ID\") VALUES (?, ?)";
        try {
            addNewQuest = connection.prepareStatement(sql);
            addNewQuest.setInt(1, userArtifactSolo.getArtifactID());
            addNewQuest.setInt(2, userArtifactSolo.getUserID());
            addNewQuest.executeUpdate();
            addNewQuest.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
