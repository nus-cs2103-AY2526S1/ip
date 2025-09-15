package chirp.io;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import chirp.actions.Action;
import chirp.actions.AddAction;
import chirp.actions.Command;
import chirp.actions.DeleteAction;
import chirp.actions.ExitAction;
import chirp.actions.FindAction;
import chirp.actions.ListAction;
import chirp.actions.MarkAction;
import chirp.exceptions.ChirpException;
import chirp.exceptions.InvalidAttributeException;
import chirp.exceptions.InvalidCommandException;

/**
 * Contains helper functions for input processing
 */
public class Parser {
    /**
     * Given an input string extracts attribute string
     *
     * @param input     The input string
     * @param attribute Attribute to search for
     * @return String immediately after the attribute in the input string
     *     until delimiter of / is reached. If the attribute is not found
     *     an empty string is returned.
     */
    public static String extractAttribute(String input, String attribute) {
        int startIndex = input.indexOf(attribute);
        if (startIndex != -1) {
            // Move past the attribute
            startIndex += attribute.length();

            if (startIndex < input.length() && input.charAt(startIndex) != ' ') {
                // If attribute is not a word but a prefix of another word
                return "";
            }

            // Find the next '/' after the attribute
            int endIndex = input.indexOf("/", startIndex);
            if (endIndex == -1) {
                // No delimiter after attribute
                endIndex = input.length();
            }

            // Extract the substring
            return input.substring(startIndex, endIndex).trim();
        } else {
            return "";
        }
    }

    /**
     * Converts a yyyy-MM-dd string format to LocalDate type
     *
     * @param data      Input string
     * @param attribute Attribute name
     * @return LocalDate value
     * @throws InvalidAttributeException
     */
    public static LocalDate convertDateAttr(String data, String attribute) throws InvalidAttributeException {
        try {
            return LocalDate.parse(data);
        } catch (DateTimeException e) {
            throw new InvalidAttributeException(data, attribute, "Not in yyyy-MM-dd format.");
        }
    }

    /**
     * Parsers the user input string into a Action to be executed
     *
     * @param input User input
     * @return A action class containing the relevant data to be executed
     * @throws ChirpException
     */
    public static Action parse(String input) throws ChirpException {
        Scanner inputSc = new Scanner(input);
        if (!inputSc.hasNext()) {
            throw new InvalidCommandException();
        }
        Command command = Command.fromString(inputSc.next());
        switch (command) {
        case LIST -> {
            // List tasks
            return new ListAction(input);
        }
        case FIND -> {
            // Find tasks with filter string
            return new FindAction(input);
        }
        case MARK, UNMARK -> {
            // Mark / Unmark task
            return new MarkAction(command, input);
        }
        case DELETE -> {
            // Delete task
            return new DeleteAction(input);
        }
        case TODO, DEADLINE, EVENT -> {
            // Add task
            return new AddAction(command, input);
        }
        case BYE -> {
            // Exit chat bot
            return new ExitAction();
        }
        default -> {
            throw new InvalidCommandException();
        }
        }
    }
}
