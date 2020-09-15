package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.Static;
import com.company.samuraiSatan.helpers.Parser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

public class LoginHandler implements HttpHandler {

    private void sendResponse(String response, HttpExchange exchange, int status) throws IOException {
        if (status == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
        }
        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        exchange.sendResponseHeaders(status, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = readHtmlPage("html/loginPage.html");
        // get file path from url
        URI uri = exchange.getRequestURI();
        System.out.println("looking for: " + uri.getPath());
        String path = "." + uri.getPath();
        exchange.sendResponseHeaders(200, response.length());
    }

    private String readHtmlPage(String path) throws IOException {
        BufferedReader objReader = new BufferedReader(new FileReader("/resources/html/loginPage.html"));

        String naszhtml = objReader.readLine();
        String linia += naszhtml;
        return
    }
}