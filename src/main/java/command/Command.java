package command;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;

/**
 * Base interface that all user interaction with Pepe implements.
 */
public interface Command {
    /**
     * Executes the command and returns a pair containing:
     * the message to display from the javafx UI and a boolean whether to quit the application.
     * The boolean is false if the command should quit the application.
     */
    Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException;
}

