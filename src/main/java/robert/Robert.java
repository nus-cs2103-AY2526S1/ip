package robert;

import robert.command.Command;
import robert.exception.DuplicateTaskException;
import robert.exception.RobertException;
import robert.parser.Parser;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Main class for the Robert chatbot application.
 * Handles the main program flow and user command processing.
 */
public class Robert {
    private static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Robert instance with the specified file path for storage.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     */
    public Robert(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = loadTasks();
    }

    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            return new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DuplicateTaskException e) {
            return formatDuplicateError(e);
        } catch (RobertException | IOException e) {
            return e.getMessage();
        }
    }

    private String formatDuplicateError(DuplicateTaskException e) {
        return "This task already exists in your list:\n  " + e.getMessage();
    }

    /**
     * Runs the command-line interface version of Robert.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        
        while (!isExit) {
            isExit = processUserCommand();
        }
        
        ui.close();
    }

    private boolean processUserCommand() {
        String input = ui.readCommand();
        ui.showLine();

        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            ui.showResponse(response);
            return command.isExit();
        } catch (DuplicateTaskException e) {
            ui.showError(formatDuplicateError(e));
        } catch (RobertException | IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }

        return false;
    }

    /**
     * Main entry point for the Robert chatbot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Robert(FILE_PATH).run();
    }
}
