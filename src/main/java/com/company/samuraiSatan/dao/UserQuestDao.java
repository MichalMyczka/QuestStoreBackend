package com.company.samuraiSatan.dao;

import com.company.samuraiSatan.models.Quest;
import com.company.samuraiSatan.models.User;
import com.company.samuraiSatan.models.UserQuest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserQuestDao extends Dao{

    public List<UserQuest> getUserQuests() {
        List<UserQuest> userQuests = new ArrayList<>();
        connect();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM \"userQuests\" ORDER BY \"UserQuest_ID\";");
            while (results.next()) {
                userQuests.add(createUserQuest(results));
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userQuests;
    }

    public UserQuest createUserQuest(ResultSet results) throws SQLException {
        int userQuestID = results.getInt("UserQuest_ID");
        int userID = results.getInt("User_ID");
        int questID = results.getInt("Quest_ID");
        int questStatusID = results.getInt("QuestStatus_ID");
        int evaluatedByID = results.getInt("EvaluatedBy_ID");
        String date = results.getString("EvaluationDate");

        return new UserQuest(userQuestID, userID, questID, questStatusID, evaluatedByID, date);
    }

    public void addUserQuest(UserQuest userQuest) {
        connect();
        PreparedStatement addNewQuest;
        String sql = "INSERT INTO \"userQuests\" (\"User_ID\", \"Quest_ID\", \"QuestStatus_ID\", \"EvaluatedBy_ID\", \"EvaluationDate\") VALUES (?, ?, ?, ?, ?)";
        try {
            addNewQuest = connection.prepareStatement(sql);
            addNewQuest.setInt(1, userQuest.getUserID());
            addNewQuest.setInt(2, userQuest.getQuestID());
            addNewQuest.setInt(3, userQuest.getQuestStatusID());
            addNewQuest.setInt(4, userQuest.getEvaluatedByID());
            addNewQuest.setString(5, userQuest.getDate());
            addNewQuest.executeUpdate();
            addNewQuest.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
