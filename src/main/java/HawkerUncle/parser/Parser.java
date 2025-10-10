package HawkerUncle.parser;

import HawkerUncle.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parses user input commands and creates the appropriate command objects.
 * This class is responsible for interpreting the full command string from user input.
 */
public class Parser {
    /**
     * Parses the full user command string adn returns the corresponding Command object.
     * @param fullCommand The full command string input by the user.
     * @return The appropriate 'Command' object based on the user input.
     * @throws UnsupportedOperationException If the command is unknown or not recognised.
     */
    public static Command parse(String fullCommand) throws UnsupportedOperationException {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        }
        else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.toLowerCase().startsWith("mark")) {
            return parseMarkCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("unmark")) {
            return parseUnmarkCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("delete")) {
            return parseDeleteCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("todo")) {
            return parseToDoCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("deadline")) {
            return parseDeadlineCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("event")) {
            return parseEventCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("find")) {
          return parseFindCommand(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("remind")) {
            return parseRemindCommand(fullCommand);
        } else {
            throw new UnsupportedOperationException("I'm sorry, I don't know what that means.");
        }
    }

    /**
     * Parses the "mark" command and returns a "MarkCommand" object
     * @param fullCommand The full command string input by the user.
     * @return A 'MarkCommand' object with the task index to mark as done.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    private static MarkCommand parseMarkCommand(String fullCommand) throws IllegalArgumentException {
        try {
            int idx = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new MarkCommand(idx);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number for 'mark'.");
        }
    }

    /**
     * Parses the "unmark" command and returns a "UnmarkCommand" object
     * @param fullCommand The full command string input by the user.
     * @return A 'UnmarkCommand' object with the task index to mark as not completed.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    private static UnmarkCommand parseUnmarkCommand(String fullCommand) throws IllegalArgumentException {
        try {
            int idx = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new UnmarkCommand(idx);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number for 'unmark'.");
        }
    }

    /**
     * Parses the "delete" command and returns a "DeleteCommand" object
     * @param fullCommand The full command string input by the user.
     * @return A 'DeleteCommand' object with the task index to delete.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    private static DeleteCommand parseDeleteCommand(String fullCommand) throws IllegalArgumentException {
        try {
            int idx = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteCommand(idx);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number for 'delete'.");
        }
    }

    /**
     * Parses the "todo" command and returns an "AddCommand" object
     * @param fullCommand The full command string input by the user.
     * @return A 'AddCommand' object with the provided description
     * @throws IllegalArgumentException If the description is empty.
     */
    private static Command parseToDoCommand(String fullCommand) throws IllegalArgumentException {
        ArrayList<String> parsedData = new ArrayList<>();

        String description = fullCommand.substring(4).trim();
        if (description.isBlank()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        parsedData.add("todo");
        parsedData.add(description);
        return new AddCommand(parsedData);
    }

    /**
     * Parses the "Deadline" command and returns an "AddCommand" object
     * @param fullCommand The full command string input by the user.
     * @return A 'AddCommand' object with the provided description and deadline timing
     * @throws IllegalArgumentException If the description or deadline time are empty or invalid.
     */
    private static Command parseDeadlineCommand(String fullCommand) throws IllegalArgumentException {
        ArrayList<String> parsedData = new ArrayList<>();

        if (!fullCommand.contains(" /by")) {
            throw new IllegalArgumentException("Deadline must contain /by.");
        }
        String[] sections = fullCommand.substring(8).split(" /by", 2);
        String description = sections[0].trim();
        String by = sections[1].trim();
        if (description.isBlank() || by.isBlank()) {
            throw new IllegalArgumentException("Deadline description and /by cannot be empty.");
        }
        parsedData.add("deadline");
        parsedData.add(description);
        parsedData.add(by);
        return new AddCommand(parsedData);
    }

    /**
     * Parses the "Event" command and returns an "AddCommand" object
     * @param fullCommand The full command string input by the user.
     * @return A 'AddCommand' object with the provided description and event timings.
     * @throws IllegalArgumentException If the description or event timings are empty or invalid.
     */
    private static Command parseEventCommand(String fullCommand) throws IllegalArgumentException {
        ArrayList<String> parsedData = new ArrayList<>();
        if (!fullCommand.contains(" /from") || !fullCommand.contains(" /to")) {
            throw new IllegalArgumentException("Event must contain /from and /to.");
        }
        String[] sections = fullCommand.substring(5).split(" /from| /to", 3);
        String description = sections[0].trim();
        String from = sections[1].trim();
        String to = sections[2].trim();
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("Event description, /from, and /to cannot be empty.");
        }
        parsedData.add("event");
        parsedData.add(description);
        parsedData.add(from);
        parsedData.add(to);
        return new AddCommand(parsedData);
    }

    /**
     * Parses the 'Find' command and returns a "FindCommand" object.
     * @param fullCommand The full command string input by the user.
     * @return A 'FindCommand' object with the provided keyword
     * @throws IllegalArgumentException If the keyword is empty.
     */
    private static FindCommand parseFindCommand(String fullCommand) throws IllegalArgumentException {
        String word = fullCommand.substring(4).trim();

        if (word.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty.");
        } else {
            return new FindCommand(word);
        }
    }

    /**
     * Parses the date and time string to a 'LocalDateTime' object
     * The expected format is "yyyy-MM-dd HHmm"
     * @param dateTimeStr The date adn time string to parse.
     * @return The parsed 'LocalDateTime' object
     * @throws IllegalArgumentException If the date and time format is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws IllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm' format.");
        }
    }

    public static RemindCommand parseRemindCommand(String fullCommand) {
        return new RemindCommand();
    }
}