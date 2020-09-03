package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.dao.QuestDao;
import com.company.samuraiSatan.models.Quest;
import com.jakewharton.fliptables.FlipTableConverters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class QuestDao extends Dao {
    public List<Quest> getQuests() {
        List<Quest> quests = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Quests;");
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
        int quest_id = results.getInt("Quest_ID");
        String name = results.getString("Quest_Name");
        int reward = results.getInt("Reward");
        boolean is_Active = results.getBoolean("Is_Active");
        String description = results.getString("Description");
        boolean is_Done = results.getBoolean("Is_Done");
        boolean evaluation = results.getBoolean("Evaluation");
        return new Quest(quest_id, name, reward, is_Active, description, is_Done, evaluation);
    }

    public void addNewQuest(Quest quest) {
        connect();
        PreparedStatement addNewQuest;
        String sql = "INSERT INTO Quests (Quest_Name, Reward, Is_Active, Description, Is_Done, Evaluation) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            addNewQuest = connection.prepareStatement(sql);
            addNewQuest.setString(1, quest.getQuest_Name());
            addNewQuest.setInt(2, quest.getReward());
            addNewQuest.setBoolean(3, quest.isIs_Active());
            addNewQuest.setString(4, quest.getDescription());
            addNewQuest.setBoolean(5, quest.getIs_Done());
            addNewQuest.setBoolean(6, quest.getEvaluation());
            addNewQuest.executeUpdate();
            addNewQuest.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
