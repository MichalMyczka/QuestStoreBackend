package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.ArtifactDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.Artifact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpCookie;
import java.util.Map;

public class AddArtifactHandler implements HttpHandler {
    ArtifactDao artifactDao = new ArtifactDao();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = null;

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                String artifactName = data.get("artifactName");
                int codecoinsCost = Integer.parseInt(data.get("codecoinsCost"));
                String artifactDescription = data.get("artifactDescription");
                System.out.println(artifactName);
                Artifact artifact = new Artifact(0,artifactName, codecoinsCost,true,artifactDescription,true);
                artifactDao.addNewArtifact(artifact);
                String artifactJSON = objectMapper.writeValueAsString(artifact);
                HttpCookie cookie = new HttpCookie("artifact", artifactJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(artifactJSON, httpExchange, 200);
            }
        } catch (Exception e) {
            HttpCommunication.sendResponse(e.getMessage(), httpExchange, 404);
            e.printStackTrace();
        }
    }
}