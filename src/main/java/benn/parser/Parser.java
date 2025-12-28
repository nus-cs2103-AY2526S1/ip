package benn.parser;

import benn.commands.*;
import benn.patterns.InputPattern;

/**
 * Parses raw user input strings and converts them into executable {@link benn.commands.Command} objects.
 *
 * <p>The {@code Parser} class is responsible for matching user input against
 * predefined command patterns (defined in {@link benn.patterns.InputPattern}) and
 * creating the corresponding command object. If the input does not match any
 * known command, an {@link benn.commands.InvalidCommand} is returned instead.</p>
 */
public class Parser {

    /**
     * Parses the given raw input string and returns a corresponding {@link benn.commands.Command}.
     *
     * <p>This method checks the input against various regex patterns in
     * {@link benn.patterns.InputPattern}, in the following order:</p>
     * <ul>
     *     <li>{@code todo} → {@link benn.commands.AddTodoCommand}</li>
     *     <li>{@code deadline} → {@link benn.commands.AddDeadlineCommand}</li>
     *     <li>{@code event} → {@link benn.commands.AddEventCommand}</li>
     *     <li>{@code list} → {@link benn.commands.ListCommand}</li>
     *     <li>{@code mark} → {@link benn.commands.MarkCommand}</li>
     *     <li>{@code unmark} → {@link benn.commands.UnmarkCommand}</li>
     *     <li>{@code delete} → {@link benn.commands.DeleteCommand}</li>
     *     <li>{@code bye} → {@link benn.commands.ExitCommand}</li>
     * </ul>
     *
     * <p>If none of the patterns match, an {@link benn.commands.InvalidCommand} is returned.</p>
     *
     * @param input the raw user input string to be parsed
     * @return a {@link benn.commands.Command} object corresponding to the input
     */
    public static Command parse(String input) {
        if (InputPattern.ADD_TODO.matcher(input).matches()) {
            return new AddTodoCommand(input);
        } else if (InputPattern.ADD_DEADLINE.matcher(input).matches()) {
            return new AddDeadlineCommand(input);
        } else if (InputPattern.ADD_EVENT.matcher(input).matches()) {
            return new AddEventCommand(input);
        } else if (InputPattern.LIST.matcher(input).matches()) {
            return new ListCommand(input);
        } else if (InputPattern.MARK_TASK.matcher(input).matches()) {
            return new MarkCommand(input);
        } else if (InputPattern.UNMARK_TASK.matcher(input).matches()) {
            return new UnmarkCommand(input);
        } else if (InputPattern.DELETE_TASK.matcher(input).matches()) {
            return new DeleteCommand(input);
        } else if (InputPattern.FIND_ALL_TASKS_CONTAINING_KEYWORD.matcher(input).matches()) {
            return new FindCommand(input);
        } else if (InputPattern.HELP.matcher(input).matches()) {
            return new HelpCommand(input);
        } else if (InputPattern.BYE.matcher(input).matches()) {
            return new ExitCommand(input);
        } else {
            return new InvalidCommand(input);
        }
    }
}

