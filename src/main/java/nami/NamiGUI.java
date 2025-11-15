package nami;

import nami.command.Command;
import nami.exception.DukeException;
import nami.parser.Parser;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.task.Tasks;
import nami.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main GUI handler class for the Nami application.
 * It manages the interaction between user input, task storage,
 * and application responses.
 */
public class NamiGUI {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean exit;

    /**
     * Constructs a {@code NamiGUI} instance.
     *
     * @throws IOException if the task file cannot be loaded.
     */
    public NamiGUI() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        ArrayList<Tasks> loadedTasks = storage.load();
        this.tasks = new TaskList(loadedTasks);
    }

    /**
     * Processes a user input and returns the corresponding response message.
     *
     * @param input user input string
     * @return the response message
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String message = command.execute(tasks, ui, storage);
            this.exit = command.isExit();
            return message;
        } catch (DukeException e) {
            return "☹ OOPS!!! " + e.getMessage();
        } catch (Exception e) {
            // Keep generic fallback for unexpected errors
            return "Unexpected error: " + e.getMessage();
        }
    }

    /**
     * Checks whether the application should exit.
     *
     * @return {@code true} if the application should exit, {@code false} otherwise.
     */
    public boolean shouldExit() {
        return exit;
    }
}
//testing123