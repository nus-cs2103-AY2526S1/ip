package hermione.parsers;

import hermione.commands.Command;
import hermione.exceptions.DateUtilsException;
import hermione.exceptions.InvalidCommandException;
import hermione.exceptions.NumberUtilsException;
import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;

/**
 * Responsible for processing user input commands in the Hermione application.
 */
public class InputParser {

    private final TaskStorage storage;

    /**
     * Constructs an InputParser with the specified TaskStorage.
     *
     * @param storage The TaskStorage instance to be used for task management.
     */
    public InputParser(TaskStorage storage) {
        this.storage = storage;
    }

    /**
     * Parses user input and returns the response result.
     *
     * @param message The user input command as a string.
     * @return The response result containing the message and error status.
     */
    public ResponseResult parseInput(String message) {
        String commandString = getCommandString(message);
        String argument = getArgument(message);
        return executeCommand(commandString, argument);
    }

    private String getCommandString(String message) {
        return message.trim().split(" ")[0];
    }

    private String getArgument(String message) {
        String commandWord = getCommandString(message);
        return message.substring(commandWord.length()).trim();
    }

    private ResponseResult executeCommand(String commandString, String argument) {
        try {
            Command command = CommandParser.parse(commandString, argument, storage);
            String response = command.execute();
            return new ResponseResult(response, false);
        } catch (DateUtilsException | InvalidCommandException | TaskValidationException | NumberUtilsException e) {
            return new ResponseResult(e.getMessage(), true);
        } catch (Exception e) {
            return new ResponseResult("An unexpected error occurred. Please try again.", true);
        }
    }
}
