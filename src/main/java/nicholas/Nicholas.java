package nicholas;

import exception.NicholasException;
import parsers.Parser;
import tasks.TaskList;
import storage.Storage;
import gui.Main;

import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner; //Import the Scanner Class
import javafx.application.Application;

/**
 * Nicholas chatbot application
 * <p>
 * The core of the application where input is retrieved and displayed for users
 * to interact with.
 */
public class Nicholas {
    private static final Scanner scanner = new Scanner(System.in); // Creating new Scanner Object
    private static final String EXIT_COMMAND = "bye";
    private static final TaskList taskList = new TaskList();
    private static final Storage defaultStorage = new Storage();
    private static final Parser parser = new Parser();


    public static void main(String... args) {
        defaultStorage.fileSetup();
        defaultStorage.loadFile(taskList);
        Application.launch(Main.class, args);
    }

    public String getResponse(String input) {
        String errorMessage;
        try {
            String returnText = parser.parseCommand(input, taskList);

            defaultStorage.saveToFile(taskList);

            return returnText;
        } catch (NicholasException e){
            errorMessage = e.getMessage();
            return errorMessage;
        } catch (DateTimeParseException e) {
            errorMessage = "I dont understand!!!! Please input the datetime format in (yyyy-mm-dd HH:mm):";

            return errorMessage;
        } catch (NumberFormatException e) {
            errorMessage = "WHAT??? Invalid number! Please enter in a valid number.";

           return errorMessage;
        } catch (ArrayIndexOutOfBoundsException e) {
            errorMessage = "I need a number :D. Please indicate the task number.";

            return errorMessage;
        }
    }
}
