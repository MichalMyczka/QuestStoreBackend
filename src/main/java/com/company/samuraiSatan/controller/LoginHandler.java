package com.company.samuraiSatan.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class LoginHandler implements HttpHandler {
    ReadHtml readHtml = new ReadHtml();

    @Override
    public void handle(HttpExchange httpExchange) {
        String response = null;
        try {
            response = readHtml.readHtmlPage("QuestStoreSQL/src/main/resources/html/loginPage.html", "html/loginPage.html");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}