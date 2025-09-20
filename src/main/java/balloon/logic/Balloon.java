package balloon.logic;

import balloon.command.Command;
import balloon.exception.BalloonException;

/**
 * Provides the entry point function into the program.
 */
public class Balloon {

    private TaskList tasks;
    private Storage storage;
    private Command lastCommand = null;

    private String commandType;

    /**
     * Constructor for Balloon.
     * @param filePath a string representing the relative path of the file loaded into storage.
     */
    public Balloon(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadSavedTasks());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String trimmedInput = input.trim();
            Command command = Parser.parseUserInput(trimmedInput);
            assert command != null : "Parser should never return a null command";
            commandType = command.getClass().getSimpleName();

            command.execute(tasks, storage, this);

            // The line below must come after the above line because UndoCommand needs to
            // reference the previous command in its execution.
            // Also, this means that lastCommand will save the last command that executed
            // successfully, i.e. without throwing an exception.
            lastCommand = command;

            // Save tasks immediately after each command
            storage.save(tasks.getTasks());

            return command.getString();
        } catch (BalloonException e) {
            commandType = "ExceptionInduced";
            return "Error: " + e;
        }
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns the greeting that the user sees upon first starting the app.
     */
    public String getGreeting() {
        return "Hello I'm Balloon!\n How may I help?";
    }

    public Command getLastCommand() {
        return lastCommand;
    }

}
