package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Artifact;
import com.company.samuraiSatan.models.Quest;
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
        int artifact_ID = results.getInt("Artifact_ID");
        String artifact_Name = results.getString("ArtifactName");
        int cost = results.getInt("Cost");
        boolean is_Active = results.getBoolean("Is_Active");
        String description = results.getString("Description");
        boolean is_Solo = results.getBoolean("Is_Solo");
        return new Artifact(artifact_ID, artifact_Name, cost, is_Active, description, is_Solo);
    }

    public void addNewArtifact(Artifact artifact) {
        connect();
        PreparedStatement addNewArtifact;
        String sql = "INSERT INTO artifacts (\"ArtifactName\", \"Cost\", \"Is_Active\", \"Description\", \"Is_Solo\") VALUES (?, ?, ?, ?, ?)";
        try {
            addNewArtifact = connection.prepareStatement(sql);
            addNewArtifact.setString(1, artifact.getArtifact_Name());
            addNewArtifact.setInt(2, artifact.getCost());
            addNewArtifact.setBoolean(3, artifact.getIs_Active());
            addNewArtifact.setString(4, artifact.getDescription());
            addNewArtifact.setBoolean(5, artifact.getIs_Solo());
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
        String sql = "UPDATE artifacts SET \"ArtifactName\" = ?, \"Cost\" = ?, \"Description\" = ? WHERE \"Artifact_ID\" = ?";
        try {
            editArtifact = connection.prepareStatement(sql);
            editArtifact.setString(1, artifact.getArtifact_Name());
            editArtifact.setInt(2, artifact.getCost());
            editArtifact.setString(3, artifact.getDescription());
            editArtifact.setInt(4, artifact.getArtifact_ID());
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
            splitArtifact.setBoolean(1, artifact.getIs_Solo());
            splitArtifact.setInt(2, artifact.getArtifact_ID());
            splitArtifact.executeUpdate();
            splitArtifact.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
