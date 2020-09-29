package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.ClassDao;
import com.company.samuraiSatan.dao.QuestDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.Class;
import com.company.samuraiSatan.models.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.Map;

public class AddClassHandler implements HttpHandler {

    ClassDao classDao = new ClassDao();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                String className = data.get("className");

                Class clas = new Class(0, className);
                classDao.addClass(clas);
                String questJSON = objectMapper.writeValueAsString(clas);
                HttpCookie cookie = new HttpCookie("clas", questJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}
