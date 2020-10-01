package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.UserQuestDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.UserQuest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class ActivateQuestHandler implements HttpHandler {
    UserQuestDao userQuestDao = new UserQuestDao();

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                int userID = Integer.parseInt(data.get("userID"));
                int questID = Integer.parseInt(data.get("questID"));
                System.out.println(questID);


                UserQuest userQuest = new UserQuest(0,userID,questID,1,1,"not yet evaluated");
                userQuestDao.addUserQuest(userQuest);
                String questJSON = objectMapper.writeValueAsString(userQuest);
                HttpCookie cookie = new HttpCookie("userQuest", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}
