package sunoo.ui;

import java.io.IOException;

import sunoo.command.Command;
import sunoo.command.IncorrectCommand;
import sunoo.exception.SunooException;
import sunoo.parser.Parser;
import sunoo.storage.Storage;
import sunoo.task.TaskList;

/**
 * Represents a chatbot that helps a user to manage a list of tasks.
 */
public class Sunoo {
    private static boolean isExitNext = false;
    private static TaskList tasks = new TaskList();
    private static String commandType;

    public static String getResponse(String userInput) throws IOException {
        String response;
        tasks = Storage.loadTasks();
        try {
            Command c = Parser.parse(userInput);
            response = c.execute(tasks);
            commandType = c.getClass().getSimpleName();
            isExitNext = c.shouldExit();
        } catch (SunooException e) {
            response = new IncorrectCommand(e.getMessage()).execute(tasks);
            commandType = "IncorrectCommand";
        } finally {
            Storage.updateTaskListInTxt(tasks);
        }
        assert response != null;
        return response;
    }

    public static boolean getShouldExit() {
        return isExitNext;
    }

    public static String getCommandType() {
        return commandType;
    }
}
