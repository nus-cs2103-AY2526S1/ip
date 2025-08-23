package bobbywasabi.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobbywasabi.BobbyWasabi;
import bobbywasabi.exceptions.BobbyWasabiException;


/**
 * Utility class for parsing user input into structured commands
 * and extracting relevant details like descriptions, dates, and indices.
 */
public class Parser {

    /**
     * Validates whether the given input string contains a valid integer within the range of the task list.
     *
     * @param s       The full user input string (e.g. "mark 2").
     * @param arrLen  The length of the current task list, used for bounds checking.
     * @return        True if the input contains a valid integer within range.
     * @throws BobbyWasabiException If the input format is invalid or the index is out of range.
     */
    public static boolean isValidInteger(String s, int arrLen) throws BobbyWasabiException {
        String[] wordList = s.split(" ");

        // not valid command length
        if (wordList.length != 2) {
            throw new BobbyWasabiException("We only accept two inputs - the command and the integer");
        }


        // not a valid integer
        try {
            int indx = Integer.parseInt(wordList[1]);
            if (indx > arrLen) {
                throw new BobbyWasabiException("Index given in input is out of range, "
                        + "please try an index within the range of your list");
            }

        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("Please input an index following your command");
        }

        return true;
    }

    /**
     * Parses and returns the integer index from the user's command input.
     *
     * @param userInput The full input string containing a command and an index (e.g. "delete 3").
     * @param taskSize  The size of the current task list.
     * @return          The parsed index as an integer.
     * @throws BobbyWasabiException If the index is invalid or out of range.
     */
    public static int parseCommandIndex(String userInput, int taskSize) throws BobbyWasabiException {
        if (Parser.isValidInteger(userInput, taskSize)) {
            String[] wordList = userInput.split(" ");
            int indx = Integer.parseInt(wordList[1]);
            return indx;
        } else {
            throw new BobbyWasabiException("Not valid index given as argument!");
        }
    }

    /**
     * Parses and returns the keyword from a "find" command input.
     *
     * @param userInput The full input string (e.g. "find book").
     * @return          The keyword to search for.
     * @throws BobbyWasabiException If the command is malformed or no keyword is provided.
     */
    public static String parseFindCommand(String userInput) throws BobbyWasabiException {
        String[] wordList = userInput.split(" ");
        if (wordList.length > 2) {
            throw new BobbyWasabiException("Please input a single keyword!");
        }

        if (wordList.length != 2) {
            throw new BobbyWasabiException("Please input the command and the keyword to find!");
        }

        String keyword = wordList[1];
        if (keyword.trim().isEmpty()) {
            throw new BobbyWasabiException("Please input a keyword!");
        }

        return keyword;
    }

    /**
     * Extracts the task description from a "todo" command input.
     *
     * @param userInput The full input string (e.g. "todo read book").
     * @return          The extracted task description.
     * @throws BobbyWasabiException If the description is missing or blank.
     */
    public static String parseTodo(String userInput) throws BobbyWasabiException {
        String[] descriptions = userInput.split("todo ");

        if (descriptions.length <= 1) {
            throw new BobbyWasabiException("Please provide a description for todo");
        }

        String description = descriptions[1]; //

        if (description.trim().isEmpty()) {
            throw new BobbyWasabiException("Please provide a description for todo");
        }


        return description;

    }

    /**
     * Extracts the task description and deadline from a "deadline" command.
     *
     * @param userInput The full input string (e.g. "deadline submit report /by 30/8/2025 1800").
     * @return          A string array with two elements: [description, deadline string].
     * @throws BobbyWasabiException If required components are missing or blank.
     */
    public static String[] parseDeadline(String userInput) throws BobbyWasabiException {
        String[] bySplit = userInput.split("/by", 2);

        if (bySplit.length < 2) {
            throw new BobbyWasabiException("You did not provide the deadline!");
        }

        String[] descriptions = bySplit[0].split("deadline ");

        if (descriptions.length < 2) {
            throw new BobbyWasabiException("The deadline task description cannot be blank!");
        }

        if (descriptions[1].trim().isEmpty()) {
            throw new BobbyWasabiException("The deadline task description cannot be blank!");
        }

        String deadline = bySplit[1];

        if (deadline.trim().isEmpty()) {
            throw new BobbyWasabiException("The deadline cannot be blank!");
        }

        return new String[] {descriptions[1], deadline};

    }

    /**
     * Extracts the task description, start time, and end time from an "event" command.
     *
     * @param userInput The full input string (e.g. "event project meeting /from 29/8/2025 1200 /to 29/8/2025 1400").
     * @return          A string array with three elements: [description, start time, end time].
     * @throws BobbyWasabiException If required parts of the command are missing or blank.
     */
    public static String[] parseEvent(String userInput) throws BobbyWasabiException {

        String[] fromSplit = userInput.split("/from", 2);

        // there was no /from command
        if (fromSplit.length < 2) {
            throw new BobbyWasabiException("You did not provide the start duration!");
        }

        String[] toSplit = fromSplit[1].split("/to", 2);

        // there was no /to command
        if (toSplit.length < 2) {
            throw new BobbyWasabiException("You did not provide the end duration!");
        }

        // starting description is empty
        if (toSplit[0].trim().isEmpty()) {
            throw new BobbyWasabiException("Your starting duration is blank!");
        } else if (toSplit[1].trim().isEmpty()) {
            throw new BobbyWasabiException("Your ending duration is blank!");
        }

        String[] descriptions = fromSplit[0].split("event ");

        if (descriptions.length < 2) {
            throw new BobbyWasabiException("The event description cannot be blank!");
        }


        if (descriptions[1].trim().isEmpty()) {
            throw new BobbyWasabiException("You did not provide a description of your event!");
        }

        return new String[] {
                descriptions[1],
                toSplit[0],
                toSplit[1]
        };

    }

    /**
     * Parses a string representation of a date into a {@link LocalDateTime} object.
     * The expected format is "d/M/yyyy HHmm" (e.g. "22/8/2025 1930").
     *
     * @param date The input string representing the date and time.
     * @return     A {@link LocalDateTime} object parsed from the input string.
     * @throws BobbyWasabiException If the date format is incorrect or parsing fails.
     */
    public static LocalDateTime parseDateString(String date) throws BobbyWasabiException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim(), formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new BobbyWasabiException(e.getMessage());
        }
    }

    /**
     * Extracts and converts the first word in the user's input into a {@link BobbyWasabi.Command}.
     *
     * @param userInput The full command input string (e.g. "todo read book").
     * @return          The corresponding {@link BobbyWasabi.Command} enum value.
     */
    public static BobbyWasabi.Command parseCommand(String userInput) {
        return BobbyWasabi.Command.toCommand(userInput.split(" ")[0]);
    }




}
