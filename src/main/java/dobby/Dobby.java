package dobby;

import dobby.exceptions.DobbyException;
import dobby.TaskList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dobby {
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;

    public static void init(Storage s, TaskList t) {
        storage = s;
        taskList = t;
        parser = new Parser(taskList, storage);
    }

    public static String getResponse(String input) {
        try {
            return parser.handleCommand(input);
        } catch (DobbyException e) {
            return "Error: " + e.getMessage();
        }
    }
}
