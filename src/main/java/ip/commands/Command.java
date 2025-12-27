package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * An interface describing commands that can be called by users
 */
public interface Command {

    /**
     * Executes a command based on user input
     *
     * @param input User Input
     * @param ui UI used to output responses
     * @param storage Storage of the data file
     * @param tasks TaskList storing all tasks
     * @throws UnknownInputException When user input is missing parameters
     * @throws FileNotFoundException If data file cannot be found
     * @throws FileCorruptedException If data file has been modified and does not have correct format
     */
    String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileNotFoundException, FileCorruptedException;

    /**
     * Checks if command is an exit command
     * @return true only for exit command
     */
    default boolean isExit() {
        return false;
    }
}
