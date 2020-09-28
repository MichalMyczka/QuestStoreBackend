package com.company.samuraiSatan.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONreader {

    public Map<String, String> JSONread() {
        Map<String, String> connectionData = new HashMap<String, String>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/DBdata.json"));
            JSONObject jsonObject =  (JSONObject) obj;

            connectionData.put("connection", (String) jsonObject.get("connection"));
            connectionData.put("user", (String) jsonObject.get("user"));
            connectionData.put("password", (String) jsonObject.get("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return connectionData;
    }

}