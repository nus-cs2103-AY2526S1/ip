package main;

import exceptions.UserInputException;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

public class LittleBabyMan {
    /**
     * gui.Main file
     * @param args
     */
    public static void main(String[] args) {
        
    }
    public static String getResponse(String userInput) {
        String response = "";
        try {
            response = Parser.processCommand(userInput);
        } catch (UserInputException e) {
            response = e.toString();
        } catch (NumberFormatException e) {
            response = "HOW AM I SUPPOSED TO WORK ON NON-NUMBERS";
        }
        return response;
    }
}
