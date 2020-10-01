package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.UserArtifactGroupDao;
import com.company.samuraiSatan.dao.UserArtifactSoloDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.UserArtifactGroup;
import com.company.samuraiSatan.models.UserArtifactSolo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class ActivateGroupArtifactHandler implements HttpHandler {
    UserArtifactGroupDao userArtifactGroupDao = new UserArtifactGroupDao();

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                int artifactID = Integer.parseInt(data.get("artifactID"));

                UserArtifactGroup userArtifactGroup = new UserArtifactGroup(0,artifactID);
                userArtifactGroupDao.addUserArtifactGroup(userArtifactGroup);
                String questJSON = objectMapper.writeValueAsString(userArtifactGroup);
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
