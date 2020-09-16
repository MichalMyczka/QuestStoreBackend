package com.company.samuraiSatan.controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadHtml {

    public String readHtmlPage(String path, String pageName) throws IOException {
        String fileName = pageName;
        String line = "";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            line += data;
        }
        myReader.close();
        return line;
    }
}
