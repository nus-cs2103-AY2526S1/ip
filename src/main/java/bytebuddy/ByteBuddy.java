package bytebuddy;

import bytebuddy.command.Command;
import bytebuddy.exception.ByteBuddyException;
import bytebuddy.parser.Parser;
import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;
import java.io.IOException;

/**
 * Represents the main ByteBuddy chatbot application.
 * <p>
 * This class is responsible for initializing the application, handling user input,
 * executing commands, and managing the task list.
 */
public class ByteBuddy {

    /** Default file path for storing tasks. */
    private static final String FILE_PATH = "src/main/data/tasks.txt";

    /** Handles storage and retrieval of tasks from disk. */
    private Storage storage;

    /** The list of tasks currently managed by the application. */
    private TaskList tasks;

    /** Handles user interaction, including input and output. */
    private Ui ui;

    /**
     * Constructs a new ByteBuddy chatbot instance.
     *
     * @param filePath the file path to load and save tasks
     */
    public ByteBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.getLoadingErrorMessage();
            ui.getFarewellMessage();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            // Instead of printing, let Command return a response String
            String response = c.execute(storage, tasks, ui);
            storage.save(tasks);
            return response;
        } catch (ByteBuddyException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
}
