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

public class ShowQuestHandler implements HttpHandler {

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

        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }

}
