package aries;

import aries.command.Command;
import aries.command.CommandParser;
import aries.command.CommandResult;
import aries.storage.Storage;
import aries.task.TaskList;
import aries.ui.Ui;

/**
 * The main class for the Aries application.
 * It initializes the UI, storage, and task list, and handles the main command loop.
 */
public class Aries {
    private static final String DEFAULT_FILE_PATH = "data/aries_tasks.ser";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private String commandType;

    /**
     * Constructor for the Aries application.
     */
    public Aries() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_FILE_PATH);
        this.tasks = storage.loadTaskList();
        try {
            tasks.rebuildKeys();
        } catch (AriesException e) {
            System.out.println("Error rebuilding task keys: " + e.getMessage());
        }
    }

    /**
     * Processes a user input command and returns the response.
     *
     * @param input The user input command.
     * @return The response from executing the command.
     */
    public String getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            CommandResult result = command.execute(tasks, ui);
            boolean hasChanged = result.isChanged();
            boolean isExit = result.isExit();
            commandType = command.getClass().getSimpleName();

            if (hasChanged) {
                storage.saveTaskList(tasks);
            }

            if (isExit) {
                return "Goodbye! Hope to see you again soon!";
            }
            return result.getResponse();
        } catch (AriesException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }

    /**
     * Gets the type of the last executed command.
     *
     * @return The command type as a string.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Gets the welcome message from the UI.
     *
     * @return The welcome message string.
     */
    public String getWelcomeMessage() {
        return ui.showWelcomeMessage();
    }

    /**
     * Checks if the given input is an exit command.
     *
     * @param input The user input command.
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExitCommand(String input) {
        try {
            Command command = CommandParser.parse(input);
            CommandResult result = command.execute(tasks, ui);
            return result.isExit();
        } catch (AriesException e) {
            return false;
        }
    }
}
