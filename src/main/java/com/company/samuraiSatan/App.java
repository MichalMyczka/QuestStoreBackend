package com.company.samuraiSatan;

import com.company.samuraiSatan.controller.CreepStartingPageHandler;
import com.company.samuraiSatan.controller.LoginHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/login", new LoginHandler());
        server.createContext("/static", new Static());
        server.createContext("/creepStarting", new CreepStartingPageHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server has started on port " + server.getAddress().getPort());
    }
}