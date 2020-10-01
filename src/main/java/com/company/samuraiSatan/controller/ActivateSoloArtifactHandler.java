package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.UserArtifactSoloDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.UserArtifactSolo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class ActivateSoloArtifactHandler implements HttpHandler {
    UserArtifactSoloDao userArtifactSoloDao = new UserArtifactSoloDao();

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
                int artifactID = Integer.parseInt(data.get("artifactID"));

                UserArtifactSolo userArtifactSolo = new UserArtifactSolo(0,artifactID,userID);
                userArtifactSoloDao.addUserArtifactSolo(userArtifactSolo);
                String questJSON = objectMapper.writeValueAsString(userArtifactSolo);
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
