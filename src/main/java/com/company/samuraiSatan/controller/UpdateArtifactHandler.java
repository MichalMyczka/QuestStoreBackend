package com.company.samuraiSatan.controller;

import com.company.samuraiSatan.dao.ArtifactDao;
import com.company.samuraiSatan.helpers.Parser;
import com.company.samuraiSatan.models.Artifact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

public class UpdateArtifactHandler implements HttpHandler {
    ArtifactDao artifactDao = new ArtifactDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            String method = httpExchange.getRequestMethod();
            if(method.equals("GET")){
                List artifactList = artifactDao.getArtifacts();

                String artifactJSON = objectMapper.writeValueAsString(artifactList);
                HttpCookie cookie = new HttpCookie("artifactList", artifactJSON);
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                HttpCommunication.sendResponse(artifactJSON, httpExchange, 200);
            }
            if(method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);

                Map<String, String> data = Parser.parseFormData(br.readLine());
                int artifactID = Integer.parseInt(data.get("artifactID"));
                String artifactName = data.get("artifactName");
                int codecoinsCost = Integer.parseInt(data.get("codecoinsCost"));
                boolean isActive = Boolean.parseBoolean(data.get("artifactIsActive"));
                String artifactDescription = data.get("artifactDescription");
                boolean isSolo = Boolean.parseBoolean(data.get("artifactIsSolo"));

                System.out.println(artifactName);

                Artifact artifact = new Artifact(artifactID,artifactName, codecoinsCost, isActive,artifactDescription, isSolo);
                artifactDao.updateArtifact(artifact);
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
