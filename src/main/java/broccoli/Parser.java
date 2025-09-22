package broccoli;

import java.util.Scanner;
import broccoli.Command.*;

/**
 * Handles parsing and processing of user input commands.
 * Coordinates between userInterface and task storage operations.
 */
public class Parser {
    private Storage storage;
    private Scanner scanner;
    private Ui userInterface;

    public Parser(Storage storage, Ui userInterface, Scanner scanner) {
        assert storage != null : "Storage cannot be null";
        assert userInterface != null : "User interface cannot be null";
        assert scanner != null : "Scanner cannot be null";
        this.storage = storage;
        this.userInterface = userInterface;
        this.scanner = scanner;
    }


    /**
     * Processes a single user input command and returns the appropriate response.
     * Handles commands like list, mark, unmark, delete, find, and task addition.
     * Commands are case-insensitive and parsed to extract command and arguments.
     *
     * @param userInput The raw user input string to be processed
     * @param taskList The task list to operate on for command execution
     * @return A string response message indicating the result of the command
     * @throws IllegalArgumentException if the command format is invalid or arguments are missing
     */
    public String processCommand(String userInput, TaskList taskList) {

        String input = userInput.trim();
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1].trim() : "";
        try {
            switch (command.toLowerCase()) {
            case "bye":
                return userInterface.exiting();
            case "list":
                return userInterface.displayList(taskList);
            case "help":
                return userInterface.displayHelpMessage();
            case "mark":
                validateArgument(argument, "mark");
                return new MarkCommand(Integer.parseInt(argument)).execute(taskList, userInterface, storage);
            case "unmark":
                validateArgument(argument, "unmark");
                return new UnmarkCommand(Integer.parseInt(argument)).execute(taskList, userInterface, storage);
            case "delete":
                validateArgument(argument, "delete");
                return new DeleteCommand(Integer.parseInt(argument)).execute(taskList, userInterface, storage);
            case "find":
                validateArgument(argument, "find");
                return new FindCommand(argument).execute(taskList, userInterface, storage);
            default:
                if (!input.isEmpty()) {
                    return new AddCommand(input).execute(taskList, userInterface, storage);
                } else {
                    throw new IllegalArgumentException("Please do not give me any empty command");
                }
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Validates that the provided argument is not empty for the specified command.
     * Used internally to ensure commands that require arguments have valid input.
     *
     * @param argument The argument string to validate
     * @param description The command description for error message formatting
     * @throws IllegalArgumentException if the argument is empty or null
     */
    private void validateArgument(String argument, String description) {
        if (argument.isEmpty()) {
            throw new IllegalArgumentException("Please specify the task number to " + description +".");
        }
    }
}

