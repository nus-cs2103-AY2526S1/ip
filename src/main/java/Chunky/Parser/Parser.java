package Chunky.Parser;

import Chunky.ChunkyException;
import Chunky.InvalidMessageException;
import Chunky.MissingArgumentException;
import Chunky.Task.Deadlines;
import Chunky.Task.Events;
import Chunky.Task.Task;
import Chunky.Task.ToDo;

import java.util.ArrayList;

/**
 * Parses the input of the user and creates respective Task Objects
 */
public class Parser {

    public static String getCommand(String input) {
        assert input != "" : "Cant put empty string";
        return input.trim().split("\\s+")[0].toLowerCase();
    }

    public static String getArguments(String input) {
        assert input != "" : "Cant put empty string";
        String[] parts = input.trim().split("\\s+", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    /**
     * Converts input and finds task number to allow action on the specific task index
     * @param arguments the command to be acted on the task
     * @return the task index
     * @throws InvalidMessageException if theres no arguments
     * @throws MissingArgumentException if an invalid number is given
     */
    public static int parseTaskIndex(String arguments) throws InvalidMessageException, MissingArgumentException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentException(
                    "main.java.Chunky.Chunky.main.java.Chunky.Task.Task number cannot be empty!");
        }
        try {
            return Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidMessageException(
                    "main.java.Chunky.Chunky.main.java.Chunky.Task.Task number must be a valid integer!");
        }
    }

    /**
     * Takes the input and creates the respective task
     * @param input the task to be added
     * @return the respective Task object
     * @throws ChunkyException when a command not belonging to the Task class is given
     */
    public static Task parseTask(String input) throws ChunkyException {
        String command = getCommand(input);
        String arguments = getArguments(input);

        switch (command) {
            case "todo":
                if (arguments.isEmpty()) {
                    throw new MissingArgumentException("Todo description cannot be empty!");
                }
                return parseToDo(arguments);

            case "deadline":
                return parseDeadline(arguments);

            case "event":
                return parseEvent(arguments);

            default:
                throw new InvalidMessageException("Unknown task type!");
        }
    }

    /**
     * Formats based on the input given to become keywords
     * @param input the command
     * @return the keywords
     * @throws ChunkyException if theres no argument
     */
    public static String parseSearchKeyword(String input) throws ChunkyException {
        String arguments = getArguments(input);
        if (arguments.isEmpty()) {
            throw new MissingArgumentException("Find keyword cannot be empty!");
        }
        return arguments;
    }

    /**
     * Creates To Do Object
     * @param arguments the To Do Task to be formatted
     * @return To Do object
     * @throws ChunkyException when format inputted is wrong
     */
    private static Task parseToDo(String arguments) throws ChunkyException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentException("Deadline description cannot be empty!");
        }

        String[] parts = arguments.split("/at ", 2);
        if (parts.length != 2) {
            throw new InvalidMessageException(
                    "Deadline format should be: deadline <description> /by <date> /at <location>"
            );
        }

        return new ToDo(parts[0].trim(), parts[1].trim());
    }

    /**
     * Creates a Deadline object
     * @param arguments the deadline to be formatted
     * @return a Deadline object
     * @throws ChunkyException when the format inputted is wrong
     */
    private static Task parseDeadline(String arguments) throws ChunkyException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentException("Deadline description cannot be empty!");
        }

        String[] parts = arguments.split(" /by | /at ", 3);
        if (parts.length != 3) {
            throw new InvalidMessageException(
                    "Deadline format should be: deadline <description> /by <date> /at <location>"
            );
        }
        validateDate(parts[1].trim());
        return new Deadlines(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Creates an Event object
     * @param arguments the event to be formatted
     * @return an Event object
     * @throws ChunkyException when the format inputted is wrong
     */
    private static Task parseEvent(String arguments) throws ChunkyException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentException("Event description cannot be empty!");
        }

        String[] parts = arguments.split(" /from | /to | /at");
        if (parts.length != 4) {
            throw new InvalidMessageException(
                    "Event format should be: event <description> /from <start> /to <end> /at <location");
        }
        validateDate(parts[1].trim());
        validateDate(parts[2].trim());
        return new Events(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
    }

    /**
     * Ensures onlu dates and time are inputted
     * @param dateInput the date and time to be validated
     * @throws InvalidMessageException if a non date and time is given
     */
    private static void validateDate(String dateInput) throws InvalidMessageException {
        String trimmed = dateInput.trim().toLowerCase();

        if (!trimmed.matches(
                ".*(\\d|today|tomorrow|monday|tuesday|wednesday|thursday|friday|saturday|sunday|pm|am).*")) {
            throw new InvalidMessageException("Please provide a valid date!");
        }
    }
}

