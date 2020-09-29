package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.ExperienceDao;
import com.company.samuraiSatan.dao.UserDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.ExperienceLvl;
import com.company.samuraiSatan.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class ExperienceAddHandler implements HttpHandler {
    ExperienceDao experienceDao = new ExperienceDao();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = null;
        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                String experienceName = data.get("levelTitle");
                int experienceNeeded = Integer.parseInt(data.get("codepointAmount"));

                ExperienceLvl experienceLvl = new ExperienceLvl(0,experienceName, experienceNeeded);
                experienceDao.addExperienceLvl(experienceLvl);

                String questJSON = objectMapper.writeValueAsString(experienceLvl);
                HttpCookie cookie = new HttpCookie("experienceLvl", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }

}
