//package com.company.samuraiSatan;
//
//import com.company.samuraiSatan.controller.HttpCommunication;
//import com.company.samuraiSatan.helpers.Parser;
//import com.company.samuraiSatan.models.Quest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpCookie;
//import java.util.Map;
//
//public class ActivateQuestHandler implements HttpHandler {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//    @Override
//    public void handle(HttpExchange httpExchange) throws IOException {
//        try {
//            String method = httpExchange.getRequestMethod();
//            if(method.equals("POST")) {
//                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
//                BufferedReader br = new BufferedReader(isr);
//
//                Map<String, String> data = Parser.parseFormData(br.readLine());
//                int userID = Integer.parseInt(data.get("userID"));
//                int questID = data.get("questID");
//
//
//                Quest quest = new Quest(0,questName,codecoinsEarned,isActive,questDescription,isBasic);
//                questDao.addQuest(quest);
//                String questJSON = objectMapper.writeValueAsString(quest);
//                HttpCookie cookie = new HttpCookie("quest", questJSON);
//                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
//                HttpCommunication.sendResponse(questJSON, httpExchange, 200);
//            }
//        } catch (Exception e) {
//            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
//            e.printStackTrace();
//        }
//    }
//}
