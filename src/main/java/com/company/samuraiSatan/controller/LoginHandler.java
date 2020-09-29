package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.UserDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpCookie;
import java.util.Map;

public class LoginHandler implements HttpHandler {

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
                String email = data.get("email");
                String password = data.get("password");

                System.out.println(email);

                User user = userDao.getUser(email, password);
                String userJSON = objectMapper.writeValueAsString(user);
                HttpCookie cookie = new HttpCookie("user", userJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(userJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}