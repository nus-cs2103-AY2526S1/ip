package bestbot;

import bestbot.command.*;
import bestbot.exception.BestbotException;

/**
 * Parses user input strings into Command objects.
 */
public class Parser {

    /**
     * Parses the full user command text into a Command.
     *
     * @param fullCommand The raw input line. Must not be null.
     * @return An instantiated {@link Command}.
     * @throws BestbotException If the command is invalid or malformed.
     */
    public static Command parse(String fullCommand) throws BestbotException {
        assert fullCommand != null : "User input command should not be null";

        String[] parts = fullCommand.trim().split(" ", 2);
        String cmd = parts[0].toLowerCase();
        assert cmd.length() > 0 : "Command keyword must not be empty";

        switch (cmd) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "done":
                if (parts.length < 2) throw new BestbotException("Please specify a task number.");
                assert parts[1].matches("\\d+") : "Task number for 'done' must be numeric";
                return new DoneCommand(Integer.parseInt(parts[1]));

            case "delete":
                if (parts.length < 2) throw new BestbotException("Please specify a task number.");
                assert parts[1].matches("\\d+") : "Task number for 'delete' must be numeric";
                return new DeleteCommand(Integer.parseInt(parts[1]));

            case "todo":
                if (parts.length < 2) throw new BestbotException("The description of a todo cannot be empty.");
                return new AddTodoCommand(parts[1]);

            case "deadline":
                if (parts.length < 2 || !parts[1].contains("/by")) {
                    throw new BestbotException("Deadline needs description and /by <date>.");
                }
                String[] deadlineParts = parts[1].split("/by", 2);
                assert deadlineParts.length == 2 : "Deadline command must contain description and /by";
                return new AddDeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());

            case "event":
                if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                    throw new BestbotException("Event needs description, /from <time>, /to <time>.");
                }
                String[] eParts = parts[1].split("/from", 2);
                String desc = eParts[0].trim();
                String[] times = eParts[1].split("/to", 2);
                assert times.length == 2 : "Event command must contain /from and /to";
                return new AddEventCommand(desc, times[0].trim(), times[1].trim());

            case "find":
                if (parts.length < 2) throw new BestbotException("Please specify a keyword to find.");
                return new FindCommand(parts[1].trim());

            case "sort":
                return new SortCommand();

            case "mark":
                if (parts.length < 2) throw new BestbotException("Please specify a task number.");
                assert parts[1].matches("\\d+") : "Task number for 'mark' must be numeric";
                return new MarkCommand(Integer.parseInt(parts[1]));

            case "unmark":
                if (parts.length < 2) throw new BestbotException("Please specify a task number.");
                assert parts[1].matches("\\d+") : "Task number for 'unmark' must be numeric";
                return new UnmarkCommand(Integer.parseInt(parts[1]));

            default:
                throw new BestbotException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
