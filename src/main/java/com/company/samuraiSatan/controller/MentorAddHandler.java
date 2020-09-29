package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.UserDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class MentorAddHandler implements HttpHandler {

    UserDao userDao = new UserDao();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                String mentorName = data.get("mName");
                String mentorSurname = data.get("mSurname");
                String mentorEmail = data.get("mEmail");
                int mentorPhone = Integer.parseInt(data.get("mPhone"));
                String mentorPassword = data.get("mPassword");
                int mentorClass = Integer.parseInt(data.get("mClass"));

                User user = new User(0, mentorName,mentorSurname,mentorEmail,mentorPhone,mentorPassword, 2, true, mentorClass, 1, 0);
                userDao.addUser(user);
                String questJSON = objectMapper.writeValueAsString(user);
                HttpCookie cookie = new HttpCookie("user", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}
