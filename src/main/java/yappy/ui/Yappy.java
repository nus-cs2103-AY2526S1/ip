package yappy.ui;

import java.nio.file.Paths;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.command.Command;
import yappy.exception.YappyException;


/**
 * The {@code Yappy} class serves as the main entry point for the Yappy application.
 * The class also manages the main program loop, which continuously reads user input,
 * parses it into a <code>Command</code>, and executes it until the user exits.
 */
public class Yappy {

    private static final String storagePath = Paths.get("./data/Yappy.txt")
            .toString();
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Initialise storage, tasks and ui variables.
     */
    public Yappy() {
        ui = new Ui();
        try {
            storage = new Storage(storagePath);
        } catch (YappyException e) {
            ui.showLoadingError();
            throw new RuntimeException(e);
        }
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (YappyException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Processes a user input string, parses it into a command, and executes the command.
     * Returns the result or error message as a string.
     *
     * @param input The user input to process.
     * @return The response message after executing the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (YappyException e) {
            return e.getMessage();
        }
    }
}
