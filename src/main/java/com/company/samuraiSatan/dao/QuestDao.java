package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Quest;
import com.company.samuraiSatan.models.User;
import com.jakewharton.fliptables.FlipTableConverters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class QuestDao extends Dao {

    public List<Quest> getQuests() {
        List<Quest> quests = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM quests ORDER BY \"Quest_ID\";");
            while (results.next()) {
                quests.add(createQuest(results));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    public Quest createQuest(ResultSet results) throws SQLException {
        int questID = results.getInt("Quest_ID");
        String name = results.getString("QuestName");
        int reward = results.getInt("Reward");
        boolean isActive = results.getBoolean("Is_Active");
        String description = results.getString("Description");
        boolean isBasic = results.getBoolean("Is_Basic");
        return new Quest(questID, name, reward, isActive, description, isBasic);
    }

    public void addQuest(Quest quest) {
        connect();
        PreparedStatement addNewQuest;
        String sql = "INSERT INTO quests (\"QuestName\", \"Reward\", \"Is_Active\", \"Description\", \"Is_Basic\") VALUES (?, ?, ?, ?, ?)";
        try {
            addNewQuest = connection.prepareStatement(sql);
            addNewQuest.setString(1, quest.getQuestName());
            addNewQuest.setInt(2, quest.getReward());
            addNewQuest.setBoolean(3, quest.isIsActive());
            addNewQuest.setString(4, quest.getDescription());
            addNewQuest.setBoolean(5, quest.getIsBasic());
            addNewQuest.executeUpdate();
            addNewQuest.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllQuests() {
        String sql = "SELECT * FROM quests ORDER BY \"Quest_ID\"";
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

    public void updateQuest(Quest quest) {
        PreparedStatement editQuest;
        connect();
        String sql = "UPDATE quests SET \"QuestName\" = ?, \"Reward\" = ?, \"Description\" = ? WHERE \"Quest_ID\" = ?";
        try {
            editQuest = connection.prepareStatement(sql);
            editQuest.setString(1, quest.getQuestName());
            editQuest.setInt(2, quest.getReward());
            editQuest.setString(3, quest.getDescription());
            editQuest.setInt(4, quest.getQuestID());
            editQuest.executeUpdate();
            editQuest.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void splitQuest(Quest quest) {
        PreparedStatement splitQuest;
        connect();
        String sql = "UPDATE quests SET \"Is_Basic\" = ? WHERE \"Quest_ID\" = ?";
        try {
            splitQuest = connection.prepareStatement(sql);
            splitQuest.setBoolean(1, quest.getIsBasic());
            splitQuest.setInt(2, quest.getQuestID());
            splitQuest.executeUpdate();
            splitQuest.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
