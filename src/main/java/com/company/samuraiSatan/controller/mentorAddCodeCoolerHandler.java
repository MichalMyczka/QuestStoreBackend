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
                //String surname = data.get("surname");
                String email = data.get("email");
                //int phone = Integer.parseInt(data.get("phone"));

                /*
                    CHANGE ID
                 */
                User user = new User(0, cName, "testnrdwasurname", email, 789, "bakomatwojastara",
                        2, true, 2,1, 0  );

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
