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
import java.util.List;
import java.util.Map;

public class MentorEditHandler implements HttpHandler {

    UserDao userDao = new UserDao();

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("GET")){
                List userList = userDao.getUsers();

                String questJSON = objectMapper.writeValueAsString(userList);
                HttpCookie cookie = new HttpCookie("userList", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
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
                int mentorID = Integer.parseInt(data.get("mID"));
                boolean mentorIsActive = Boolean.parseBoolean(data.get("mIsActive"));

                User user = new User(mentorID, mentorName,mentorSurname,mentorEmail,mentorPhone,mentorPassword, 2, mentorIsActive, mentorClass, 1, 0);
                userDao.updateMentor(user);
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
