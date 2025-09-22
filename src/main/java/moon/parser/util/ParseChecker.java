package moon.parser.util;

import moon.commands.enums.Command;
import moon.models.TaskList;
import moon.parser.exceptions.EmptyArgumentException;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.exceptions.ParseException;

/**
 * Utility class that provides validation checks for command formats and arguments.
 * <p>
 * Used by the parser layer to ensure user input adheres to the expected syntax
 * before command execution.
 */
public class ParseChecker {

    /**
     * Validates that the number of parameters for a command matches its expected format.
     * <p>
     * Expected number of parameters:
     * <ul>
     *   <li>{@link Command#TODO} → 1</li>
     *   <li>{@link Command#DEADLINE} → 2</li>
     *   <li>{@link Command#EVENT} → 3</li>
     * </ul>
     *
     * @param inputList the split user input
     * @param command   the command being validated
     * @throws ParseException if the number of parameters is less or more than expected
     */
    public static void isCommandFormatValid(String[] inputList, Command command) throws ParseException {
        int numOfParameters = switch (command) {
        case TODO -> 1;
        case DEADLINE -> 2;
        case EVENT -> 3;
        default -> throw new ParseException(command,
                "Meow! Are you sure you have the correct command?");
        };

        if (inputList.length < numOfParameters) {
            throw new ParseException(command,
                    "Meow! Are you missing a dash '/' or a command somewhere?");
        } else if (inputList.length > numOfParameters) {
            throw new ParseException(command,
                    "Meow! Are you sure you have the correct command?");
        }
    }

    /**
     * Validates that the argument for a task or time is not empty.
     *
     * @param inputString the full user input string
     * @param command     the command being validated
     * @param isTaskName  true if checking for an empty task name,
     *                    false if checking for a time argument
     * @throws EmptyArgumentException if the required argument is empty
     * @throws ParseException         if the command type is unsupported
     */
    public static void isParameterEmpty(String inputString, Command command, boolean isTaskName)
            throws ParseException {
        String exceptionMessage;

        if (isTaskName) {
            exceptionMessage = "Meow! Your task name cannot be empty!";
        } else {
            exceptionMessage = switch (command) {
            case DEADLINE -> "Meow! Your deadline time cannot be empty!";
            case EVENT -> "Meow! Both your start and end time cannot be empty!";
            default -> throw new ParseException(command,
                    "Meow! Are you sure you have the correct command?");
            };
        }

        if (inputString.split("\\s+").length <= 1) {
            System.out.println(inputString);
            throw new EmptyArgumentException(command, exceptionMessage);
        }
    }

    /**
     * Validates that an input keyword matches the expected keyword for a command.
     *
     * @param actualInput   the keyword provided by the user
     * @param expectedInput the expected keyword
     * @param command       the command being validated
     * @throws ParseException if the actual keyword does not match the expected one
     */
    public static void isKeywordValid(String actualInput, String expectedInput, Command command) throws ParseException {
        if (!actualInput.equalsIgnoreCase(expectedInput)) {
            throw new ParseException(command,
                    String.format("Meow! I think you make a mistake here: '%s'.", actualInput));
        }
    }

    /**
     * Check if the given index is out of bounds for the task list.
     *
     * @param index the index to check
     * @param list  the task list being accessed
     * @throws InvalidIndexException if the index is negative or greater than/equal to the list size
     */
    public static void isIndexOutOfRange(int index, TaskList list) throws InvalidIndexException {
        if (index < 0 || index >= list.size()) {
            throw new InvalidIndexException(Command.DELETE,
                    "Meow! Your index is out of range!");
        }
    }
}
