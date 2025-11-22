package watermelon;

import watermelon.command.Command;
import watermelon.exception.WatermelonException;

/**
 * Entry point of the Watermelon application.
 *
 * <p> This class wires together the main.css components:
 * {@link Storage}, {@link Ui}, {@link TaskList} and {@link Parser}. </p>
 */
public class Watermelon {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Creates a new Watermelon instance with the specified file path.
     *
     * <p> This sets up the storage, UI, task list (by loading existing tasks), and command parser. </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Watermelon(String filePath) {
        storage = new Storage(filePath); // initialise storage
        ui = new Ui();
        taskList = new TaskList(storage.loadTasks()); // load existing tasks from storage
        parser = new Parser(taskList, storage, ui);
    }

    /**
     * Creates a new Watermelon instance with the default file path.
     */
    public Watermelon() {
        storage = new Storage("./data/watermelon.txt"); // initialise storage
        ui = new Ui();
        taskList = new TaskList(storage.loadTasks()); // load existing tasks from storage
        parser = new Parser(taskList, storage, ui);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.endChat();
        }

        try {
            Command command = parser.parseCommand(input);
            assert command != null : "command should not be null";
            command.execute();
            return command.getMessage();
        } catch (WatermelonException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return ui.showWelcomeMessage();
    }
}
