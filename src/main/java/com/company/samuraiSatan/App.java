
package com.company.samuraiSatan;

import com.company.samuraiSatan.controller.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/login", new LoginHandler());
        server.createContext("/static", new Static());
        server.createContext("/creepAddMentor", new MentorAddHandler());
        server.createContext("/createExperienceLvl", new ExperienceAddHandler());
        server.createContext("/creepAddClass", new AddClassHandler());
        server.createContext("/creepEditMentor", new MentorEditHandler());
        server.createContext("/seeMentorProfile", new SeeMentorProfileHandler());
        server.createContext("/mentorAddUser", new MentorAddCodeCoolerHandler());
        server.createContext("/addQuest", new QuestAddHandler());
        server.createContext("/updateQuest", new QuestUpdateHandler());
        server.createContext("/mentorAddArtifact", new AddArtifactHandler());
        server.createContext("/updateArtifact", new UpdateArtifactHandler());
        server.createContext("/seeCodecoolerProfile", new SeeCodecoolerProfileHandler());
        server.createContext("/codecoolerShowQuests", new QuestUpdateHandler());
        server.createContext("/codecoolerAddQuests", new ActivateQuestHandler());
        server.createContext("/codecoolerActivateSoloArtifacts", new ActivateSoloArtifactHandler());
        server.createContext("/codecoolerActivateGroupArtifacts", new ActivateGroupArtifactHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server has started on port " + server.getAddress().getPort());

    }
}