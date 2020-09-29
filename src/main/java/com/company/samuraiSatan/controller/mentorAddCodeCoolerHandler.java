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

public class mentorAddCodeCoolerHandler implements HttpHandler {

    UserDao userDao = new UserDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = null;
        try {
            String method = exchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());

                String cName = data.get("cName");
                String cSurname = data.get("cSurname");
                String email = data.get("email");
                String cPassword = data.get("cPassword");
                int cPhone = Integer.parseInt(data.get("cPhone"));

                User user = new User(0, cName, cSurname, email, cPhone, cPassword,
                        2, true, 1,2, 0  );

                userDao.addUser(user);

                String userJSON = objectMapper.writeValueAsString(user);
                HttpCookie cookie = new HttpCookie("user", userJSON);
                exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(userJSON, exchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), exchange, 404);
            e.printStackTrace();
        }

    }
}
