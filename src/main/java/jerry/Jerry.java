package jerry;

import javafx.application.Platform;
import jerry.command.Command;
import jerry.exceptions.JerryException;
import jerry.parser.Parser;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * The main entry point for the Jerry task manager application.
 * This class handles the initialization of the application such as storage system ,
 * task list and all user commands in the main loop until an exit command is issued.
 */
public class Jerry {

    private static Storage storage;
    private static Ui ui;
    private static TaskList taskList;

    /**
     * Constructs a new Jerry application with a specified file path for storage.
     * The UI and storage are initialized here with attempts to load the task list.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Jerry(String filePath) throws JerryException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (JerryException e) {
            ui.showLoadingError();
            taskList = new TaskList();
            throw new JerryException("Error! Failed loading file!");
        }
    }

    /**
     * The entry point of Jerry application.
     *
     * @param args command line arguments.
     * @throws JerryException to handle error when initializing the application.
     */
    public static void main(String[] args) throws JerryException {
        new Jerry("data/jerry.txt").run();
    }

    /**
     * Runs the main loop of Jerry application, with error messages displayed when occur.
     * The user input is parsed, read and executed continuously until the exit command is entered.
     */
    public void run() {
        ui.showWelcome(this.getWelcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (JerryException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getWelcomeMessage() {
        String welcome = "Hello, nice to meet you! I'm Jerry!\n What can I do for you today?";
        return welcome;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public static String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
            return ui.getLastOutput();
        } catch (JerryException e) {
            return e.getMessage();
        }
    }
}
