package dobby;

import dobby.exceptions.DobbyException;
import dobby.TaskList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dobby {

    private static final Path FILE_PATH = Paths.get("data", "dobby.txt");
    private static Storage storage = new Storage(FILE_PATH);
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser(taskList, storage);

    /**
     * Entry point for GUI: takes user input and returns Dobby's response.
     */
    public static String getResponse(String input) {
        try {
            return parser.handleCommand(input);
        } catch (DobbyException e) {
            return "Error: " + e.getMessage();
        }
    }
}
