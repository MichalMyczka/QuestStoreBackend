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

        // server.createContext("/quest", new QuestHandler());
        /*
        /quest/update/1
        /quest/add
        /quest/delete/1
        /quest/1
        /quest/all

        look at: https://github.com/adrianwii-codecool/web-basic-backend/blob/6d9a7fb9ae78ce9294552d4c6483bedc673f8ca8/src/main/java/com/codecool/controller/UserController.java#L15
         */
        server.createContext("/addQuest", new QuestAddHandler());
        server.createContext("/updateQuest", new QuestUpdateHandler());

        //        server.createContext("/artifact", new ArtifactHandler());
        /*
        /artifact/add
        /artifact/update/5
        ...
         */
        server.createContext("/mentorAddArtifact", new AddArtifactHandler());
        server.createContext("/updateArtifact", new UpdateArtifactHandler());

        //        server.createContext("/mentor", new MentorHandler());
        //        server.createContext("/student", new StudentHandler());


        server.setExecutor(null);
        server.start();

        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}