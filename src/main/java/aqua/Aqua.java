package aqua;

import java.nio.file.Path;
import java.nio.file.Paths;

import aqua.command.Command;
import aqua.command.parser.CommandParser;
import aqua.exception.AquaException;
import aqua.exception.IllegalDataException;
import aqua.exception.StorageException;
import aqua.task.TaskList;

/**
 * Main class for the Aqua chatbot application.
 */
public class Aqua {
    private final TaskList taskList;

    /**
     * Initializes Aqua with the specified storage file.
     *
     * @param fileName Name of the file to store data
     */
    public Aqua(String fileName) {
        Path savePath = Paths.get("data", fileName);
        taskList = new TaskList(savePath);
        try {
            taskList.loadFromStorage();
        } catch (StorageException | IllegalDataException e) {
            // Proceed with an empty task list if loading fails
        }
    }

    /**
     * Returns a greeting message.
     *
     * @return Greeting message
     */
    public static String greet() {
        return "Hello! I'm Aqua\nWhat can I do for you?";
    }

    /**
     * Processes user input and returns the corresponding response.
     *
     * @param input User input string
     * @return Response message
     */
    public String getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            return command.execute(taskList);
        } catch (AquaException e) {
            return e.getMessage();
        }
    }
}
