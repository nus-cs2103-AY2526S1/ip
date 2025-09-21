package parser;

import command.*;
import task.DukeAction;

/**
 * A utility class for parsing user input and returning the
 * corresponding {@link Command} object or {@link DukeAction} enum value.
 */
public class Parser {

    /**
     * Parses a string as user input and returns a {@link Command} object.
     * <p>
     * The code returns a {@link Command} based on the first keyword in the
     * input string (separated by space). The resulting {@link Command} is
     * initialised with the input for its own use during its execution.
     * </p>
     * @param input the user input
     * @return the {@link Command} corresponding to the input
     */
    public static Command parse(String input) {
        if (input.startsWith("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("bye")) {
            return new ExitCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else {
            return new UnknownCommand(input);
        }
    }


    /**
     * Parses a string as user input and returns a {@link DukeAction} enum value.
     * <p>
     * The code returns a {@link DukeAction} enum value based on the first keyword in the
     * input string (separated by space). The resulting {@link DukeAction} enum value
     * can be used to run command-specific logic.
     * </p>
     * @param input the user input
     * @return the {@link DukeAction} enum value corresponding to the input
     */
    public static DukeAction parseInput(String input) {
        if (input.startsWith("list")) {
            return DukeAction.LIST_TASKS;
        } else if (input.startsWith("bye")) {
            return DukeAction.EXIT;
        } else if (input.startsWith("mark")) {
            return DukeAction.MARK_ITEM;
        } else if (input.startsWith("unmark")) {
            return DukeAction.UNMARK_ITEM;
        } else if (input.startsWith("delete")) {
            return DukeAction.DELETE;
        } else if (input.startsWith("todo")) {
            return DukeAction.CREATE_TODO;
        } else if (input.startsWith("deadline")) {
            return DukeAction.CREATE_DEADLINE;
        } else if (input.startsWith("event")) {
            return DukeAction.CREATE_EVENT;
        } else {
            return DukeAction.ERROR;
        }
    }
}
