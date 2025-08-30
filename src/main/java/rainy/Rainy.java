package rainy;

import commands.Command;
import commands.ErrorCommand;
import exception.RainyException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Main entry point for the Rainy task manager application.
 * Handles initialization of storage, tasks, and UI,
 * and manages the main event loop for processing user commands.
 */
public class Rainy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Constructs a Rainy instance with the given file path for persistent storage.
     *
     * @param filePath the file path where tasks will be saved and loaded from
     */
    public Rainy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public Rainy() {
        this("data/rainy.txt"); // call the existing constructor with default path
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }


    /**
     * Runs the main event loop of the application.
     * Displays the welcome message, continuously reads user input,
     * parses commands, executes them, and saves task state.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RainyException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Main method to start the Rainy application.
     */
    public static void main(String[] args) {
        new Rainy("data/rainy.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws RainyException {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getMessage();
        } catch (RainyException e) {
            Command c = new ErrorCommand(e.getMessage());
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
