package cortana.core;

import java.io.IOException;

import cortana.command.Command;
import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * The cortana.core.Cortana chatbot class responsible for initializing,
 * and handling user input.
 */
public class Cortana {

    private final FileHandler fileHandler;
    private TaskList tasks;

    /**
     * Initializes a new instance of the Cortana chatbot with
     * the specified UI and file handler components.
     *
     * @param fileHandler The file handler used for task persistence.
     */
    public Cortana(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Initializes the chatbot by ensuring the task file exists, preparing it,
     * loading tasks, and displaying relevant output messages.
     * If loading tasks fails due to IO or Cortana exceptions, a new task list is created,
     * and an appropriate message is displayed.
     * Finally, it shows a greeting message to the user.
     */
    public String initialize() {
        try {
            fileHandler.ensureFileExists();
            fileHandler.checkAndPrepareFile();
            tasks = fileHandler.loadTasks();
            assert tasks != null : "loadTasks() method should not return null";
            return "Welcome back chief!\n\nInput 'help' to view a list of available commands."
                    + "\n\nYour past data has been loaded from: " + fileHandler.getFilePath();
        } catch (IOException | CortanaException e) {
            tasks = new TaskList();
            return "Welcome back!\nSomething went wrong, a new file has been created at: " + fileHandler.getFilePath();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, fileHandler);
        } catch (IOException | CortanaException e) {
            return e.getMessage();
        }
    }
}
