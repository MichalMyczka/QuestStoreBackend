package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Artifact;
import com.company.samuraiSatan.models.Class;
import com.company.samuraiSatan.models.ExperienceLvl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDao extends Dao{

    public List<ExperienceLvl> getExperiences() {
        List<ExperienceLvl> experiences = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM \"experienceLvls\" ;");
            while (results.next()) {
                experiences.add(createExperience(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experiences;
    }

    private ExperienceLvl createExperience(ResultSet results) throws SQLException {
        int experienceLvlID = results.getInt("ExperienceLvl_ID");
        String experienceName = results.getString("ExperienceName");
        int experienceNeeded = results.getInt("ExperienceNeeded");
        return new ExperienceLvl(experienceLvlID, experienceName,experienceNeeded);
    }

    public void addExperienceLvl(ExperienceLvl experienceLvl) {
        connect();
        PreparedStatement addExperienceLvl;
        String sql = "INSERT INTO \"experienceLvls\" (\"ExperienceName\", \"ExperienceNeeded\") VALUES (?, ?)";
        try {
            addExperienceLvl = connection.prepareStatement(sql);
            addExperienceLvl.setString(1, experienceLvl.getExperienceName());
            addExperienceLvl.setInt(2, experienceLvl.getExperienceNeeded());
            addExperienceLvl.executeUpdate();
            addExperienceLvl.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
