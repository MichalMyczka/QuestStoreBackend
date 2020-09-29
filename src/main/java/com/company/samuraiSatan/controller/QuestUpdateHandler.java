package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.QuestDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public class QuestUpdateHandler implements HttpHandler {

    QuestDao questDao = new QuestDao();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("GET")){
                List questList = questDao.getQuests();

                String questJSON = objectMapper.writeValueAsString(questList);
                HttpCookie cookie = new HttpCookie("questlist", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                String questName = data.get("questName");
                String questDescription = data.get("questDescription");
                int codecoinsEarned = Integer.parseInt(data.get("codecoinsEarned"));
                boolean isActive = Boolean.parseBoolean(data.get("questIsActive"));
                boolean isBasic = Boolean.parseBoolean(data.get("questIsBasic"));
                int questID = Integer.parseInt(data.get("questID"));

                Quest quest = new Quest(questID,questName,codecoinsEarned,isActive,questDescription,isBasic);
                questDao.updateQuest(quest);
                String questJSON = objectMapper.writeValueAsString(quest);
                HttpCookie cookie = new HttpCookie("quest", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}
