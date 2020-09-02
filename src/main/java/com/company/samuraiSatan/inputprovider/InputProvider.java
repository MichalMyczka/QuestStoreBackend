package com.company.samuraiSatan.inputprovider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputProvider {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String takeUserInput(String message) {
        String userInput;
        Scanner scan = new Scanner(System.in);
        boolean invalidInput;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9@._]");

        do{
            System.out.println(message);
            userInput = scan.nextLine();
            invalidInput = pattern.matcher(userInput).find();

            if(invalidInput){
                System.out.println("That's not a valid input");
            }
        }while (invalidInput);
        return userInput;
    }

    private String readNextLine(BufferedReader in) throws IOException {
        return in.readLine();
    }

    private int convertStringToInt(String valueString) throws NumberFormatException {
        return Integer.parseInt(valueString);
    }
    public int getNumberFromUser(String title) {
        while (true) { // we end the loop by a return, not by a condition.
            System.out.println(title);
            try {
                String valueString = readNextLine(reader);
                // return leaves the method and therefor the loop too.
                return convertStringToInt(valueString);
                // this method will not return
                // and the loop will not end if convertStringToInt() throws an
                // exception!
            } catch (IOException ex) {
                System.err.println("could not acquire next line from system input: " + ex.getMessage());
            } catch (NumberFormatException ex) {
//                System.err.println("could not convert input string: " + ex.getMessage());
                System.err.println("Invalid input, please try again.");
            }
        }
    }
}