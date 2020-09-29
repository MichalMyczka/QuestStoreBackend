package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Artifact;
import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArtifactDao extends Dao {
    public List<Artifact> getArtifacts() {
        List<Artifact> artifacts = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM artifacts ORDER BY \"Artifact_ID\"");
            while (results.next()) {
                artifacts.add(createArtifact(results));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifacts;
    }

    public Artifact createArtifact(ResultSet results) throws SQLException {
        int artifactID = results.getInt("Artifact_ID");
        String artifactName = results.getString("ArtifactName");
        int cost = results.getInt("Cost");
        boolean isActive = results.getBoolean("Is_Active");
        String description = results.getString("Description");
        boolean isSolo = results.getBoolean("Is_Solo");
        return new Artifact(artifactID, artifactName, cost, isActive, description, isSolo);
    }

    public void addNewArtifact(Artifact artifact) {
        connect();
        PreparedStatement addNewArtifact;
        String sql = "INSERT INTO artifacts (\"ArtifactName\", \"Cost\", \"Is_Active\", \"Description\", \"Is_Solo\") VALUES (?, ?, ?, ?, ?)";
        try {
            addNewArtifact = connection.prepareStatement(sql);
            addNewArtifact.setString(1, artifact.getArtifactName());
            addNewArtifact.setInt(2, artifact.getCost());
            addNewArtifact.setBoolean(3, artifact.getActive());
            addNewArtifact.setString(4, artifact.getDescription());
            addNewArtifact.setBoolean(5, artifact.getSolo());
            addNewArtifact.executeUpdate();
            addNewArtifact.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllArtifacts() {
        String sql = "SELECT * FROM artifacts ORDER BY \"Artifact_ID\"";
        connect();
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateArtifact(Artifact artifact) {
        PreparedStatement editArtifact;
        connect();
        String sql = "UPDATE artifacts SET \"ArtifactName\" = ?, \"Cost\" = ?, \"Is_Active\" = ?, \"Description\" = ?, \"Is_Solo\" = ? WHERE \"Artifact_ID\" = ?";
        try {
            editArtifact = connection.prepareStatement(sql);
            editArtifact.setString(1, artifact.getArtifactName());
            editArtifact.setInt(2, artifact.getCost());
            editArtifact.setBoolean(3, artifact.getActive());
            editArtifact.setString(4, artifact.getDescription());
            editArtifact.setBoolean(5,artifact.getSolo());
            editArtifact.setInt(6, artifact.getArtifactID());
            editArtifact.executeUpdate();
            editArtifact.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void splitArtifact(Artifact artifact) {
        PreparedStatement splitArtifact;
        connect();
        String sql = "UPDATE artifacts SET \"Is_Solo\" = ? WHERE \"Artifact_ID\" = ?";
        try {
            splitArtifact = connection.prepareStatement(sql);
            splitArtifact.setBoolean(1, artifact.getSolo());
            splitArtifact.setInt(2, artifact.getArtifactID());
            splitArtifact.executeUpdate();
            splitArtifact.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
