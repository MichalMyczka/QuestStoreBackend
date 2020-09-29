package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;


public class SeeMentorProfileHandler implements HttpHandler {

    UserDao userDao = new UserDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("GET")){
                List userList = userDao.getUsers();

                String artifactJSON = objectMapper.writeValueAsString(userList);
                HttpCookie cookie = new HttpCookie("userList", artifactJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(artifactJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}
