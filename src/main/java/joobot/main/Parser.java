package joobot.main;

import joobot.command.AddCommand;
import joobot.command.Command;
import joobot.command.DeleteCommand;
import joobot.command.ExitCommand;
import joobot.command.FindCommand;
import joobot.command.ListCommand;
import joobot.command.MarkCommand;
import joobot.command.SortCommand;
import joobot.command.UnmarkCommand;
import joobot.task.Deadline;
import joobot.task.Event;
import joobot.task.ToDo;

/**
 * Handles parsing of user input into {@link Command} objects.
 * Encapsulates the logic for interpreting task-related commands and date strings.
 */
public class Parser {

    /**
     * Parses user input into the appropriate {@link Command}.
     *
     * @param input the raw user input string
     * @return a {@link Command} representing the user’s action
     * @throws JooException if the input is invalid or missing required fields
     */
    public static Command parse(String input) throws JooException {
        if (input == null || input.trim().isEmpty()) {
            throw new JooException(JooException.ErrorType.EMPTY_INPUT);
        }

        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0];
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark": {
            if (args.isEmpty()) {
                throw new JooException(JooException.ErrorType.NO_INDEX);
            }
            int index = parseIndex(args);
            return new MarkCommand(index);
        }

        case "unmark": {
            if (args.isEmpty()) {
                throw new JooException(JooException.ErrorType.NO_INDEX);
            }
            int index = parseIndex(args);
            return new UnmarkCommand(index);
        }

        case "delete": {
            if (args.isEmpty()) {
                throw new JooException(JooException.ErrorType.NO_INDEX);
            }
            int index = parseIndex(args);
            return new DeleteCommand(index);
        }

        case "todo": {
            if (args.isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_DESC);
            }
            return new AddCommand(new ToDo(args));
        }

        case "deadline": {
            if (args.isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_DESC);
            }
            String[] texts = args.split("/by", 2);
            if (texts.length < 2 || texts[1].trim().isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_BY_DATE);
            }
            return new AddCommand(new Deadline(texts[0].trim(), texts[1].trim()));
        }

        case "event": {
            if (args.isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_DESC);
            }
            String[] texts = args.split("/from", 2);
            if (texts.length < 2 || texts[1].trim().isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_FROM_TO);
            }
            String[] times = texts[1].split("/to", 2);
            if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_FROM_TO);
            }
            return new AddCommand(new Event(texts[0].trim(), times[0].trim(), times[1].trim()));
        }

        case "find":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new JooException(JooException.ErrorType.MISSING_DESC);
            }
            return new FindCommand(parts[1].trim());

        case "sort":
            return new SortCommand();

        default:
            throw new JooException(JooException.ErrorType.DEFAULT);
        }
    }


    private static int parseIndex(String arg) throws JooException {
        try {
            return Integer.parseInt(arg) - 1; // convert to 0-based index
        } catch (NumberFormatException e) {
            throw new JooException(JooException.ErrorType.NO_INDEX);
        }
    }
}
