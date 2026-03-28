package simon;

import simon.command.Command;
import simon.exceptions.SimonException;
import simon.parser.Parser;
import simon.storage.Storage;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Entry point for the Simon chatbot application.
 * A <code>Simon</code> object manages the main loop and state of the program.
 */
public class Simon {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Constructs a Simon chatbot with the inputted file path for storage.
     * Loads tasks from the file if possible, otherwise initializes an empty task list.
     *
     * @param filePath Path to the file for saving/loading tasks.
     */
    public Simon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     * Parses the input, executes the corresponding command, and returns the result.
     *
     * @param input The user's input string.
     * @return The response message from executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parsed command should not be null";
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (SimonException.EmptyTaskException | SimonException.UnknownCommandException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
    
    /**
     * Gets the type of the last executed command.
     *
     * @return The simple class name of the last executed command.
     */
    public String getCommandType() {
        return commandType;
    }
}