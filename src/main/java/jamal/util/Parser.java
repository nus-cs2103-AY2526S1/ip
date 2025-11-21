package jamal.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jamal.command.Command;
import jamal.command.DeadlineTaskCommand;
import jamal.command.DeleteCommand;
import jamal.command.EventTaskCommand;
import jamal.command.ExitCommand;
import jamal.command.FindCommand;
import jamal.command.HelpCommand;
import jamal.command.ListCommand;
import jamal.command.ListOngoingCommand;
import jamal.command.ListOverdueCommand;
import jamal.command.ListUpcomingCommand;
import jamal.command.MarkCommand;
import jamal.command.PrioritizeCommand;
import jamal.command.ToDoTaskCommand;
import jamal.command.UnmarkCommand;
import jamal.exception.InvalidCommandException;
import jamal.task.Deadline;
import jamal.task.Event;
import jamal.task.ToDo;

/**
 * Parses user input received from Ui into Commands
 *
 */
public class Parser {
    /**
     * Parses user input received from Ui into Commands
     *
     * @param input String input to be parsed
     * @return Command based on parsed input
     * @throws InvalidCommandException if input is not of correct format
     */
    public static Command parse(String input) throws InvalidCommandException {
        input = input.trim();

        if (input.toLowerCase().startsWith("list")) {
            return parseListCommand(input);
        }
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        }
        if (input.toLowerCase().startsWith("mark")) {
            return parseMarkCommand(input);
        }
        if (input.toLowerCase().startsWith("unmark")) {
            return parseUnmarkCommand(input);
        }
        if (input.toLowerCase().startsWith("prioritize")) {
            return parsePrioritizeCommand(input);
        }
        if (input.toLowerCase().startsWith("delete")) {
            return parseDeleteCommand(input);
        }
        if (input.toLowerCase().startsWith("find")) {
            return parseFindCommand(input);
        }
        if (input.toLowerCase().startsWith("todo")) {
            return parseTodoCommand(input);
        }
        if (input.toLowerCase().startsWith("deadline")) {
            return parseDeadlineCommand(input);
        }
        if (input.toLowerCase().startsWith("event")) {
            return parseEventCommand(input);
        }
        throw new InvalidCommandException("Invalid Command!");
    }

    /**
     * Parses list-related commands.
     *
     * @param input The full user input string.
     * @return A corresponding ListCommand variant.
     * @throws InvalidCommandException if the list command is malformed or unrecognized.
     */
    private static Command parseListCommand(String input) throws InvalidCommandException {
        String[] listSeq = input.trim().split(" ");
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (listSeq.length == 2) {
            switch (listSeq[1].toLowerCase()) {
            case "ongoing":
                return new ListOngoingCommand();
            case "upcoming":
                return new ListUpcomingCommand();
            case "overdue":
                return new ListOverdueCommand();
            default:
                throw new InvalidCommandException("Invalid list command!");
            }
        } else {
            throw new InvalidCommandException("Invalid list command!");
        }
    }

    /**
     * Parses the 'mark' command input and returns the corresponding Command.
     *
     * @param input Full user input string starting with 'mark'.
     * @return MarkCommand with the appropriate task number.
     * @throws InvalidCommandException if format is incorrect or task number is invalid.
     */
    private static Command parseMarkCommand(String input) throws InvalidCommandException {
        Pattern markRegexPattern = Pattern.compile("^mark\\s\\d{1,3}$");
        if (!markRegexPattern.matcher(input.toLowerCase()).matches()) {
            throw new InvalidCommandException("Invalid Mark Command!");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber < 1) {
            throw new InvalidCommandException("Invalid Mark Command!");
        }
        return new MarkCommand(taskNumber - 1);
    }

    /**
     * Parses the 'unmark' command and returns the corresponding Command.
     *
     * @param input User input starting with 'unmark'.
     * @return UnmarkCommand with the task number.
     * @throws InvalidCommandException if format or number is invalid.
     */
    private static Command parseUnmarkCommand(String input) throws InvalidCommandException {
        Pattern unmarkRegexPattern = Pattern.compile("^unmark\\s\\d{1,3}$");
        if (!unmarkRegexPattern.matcher(input.toLowerCase()).matches()) {
            throw new InvalidCommandException("Invalid Unmark Command!");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber < 1) {
            throw new InvalidCommandException("Invalid Unmark Command!");
        }
        return new UnmarkCommand(taskNumber - 1);
    }

    /**
     * Parses the 'prioritize' command and returns the appropriate Command.
     *
     * @param input User input starting with 'prioritize'.
     * @return PrioritizeCommand with task number and priority.
     * @throws InvalidCommandException if format is incorrect.
     */
    private static Command parsePrioritizeCommand(String input) throws InvalidCommandException {
        Pattern prioritizeRegexPattern = Pattern.compile("^prioritize\\s\\d{1,3}\\s\\d{1,3}$");
        if (!prioritizeRegexPattern.matcher(input.toLowerCase()).matches()) {
            throw new InvalidCommandException("Invalid Prioritize Command!");
        }
        String[] priorityInfo = input.split(" ");
        int taskNumber = Integer.parseInt(priorityInfo[1]);
        int priority = Integer.parseInt(priorityInfo[2]);
        if (taskNumber < 1) {
            throw new InvalidCommandException("Invalid Prioritize Command!");
        }
        return new PrioritizeCommand(taskNumber - 1, priority);
    }

    /**
     * Parses the 'delete' command and returns the corresponding DeleteCommand.
     *
     * @param input User input starting with 'delete'.
     * @return DeleteCommand with the specified task number.
     * @throws InvalidCommandException if format or task number is invalid.
     */
    private static Command parseDeleteCommand(String input) throws InvalidCommandException {
        Pattern deleteRegexPattern = Pattern.compile("delete\\s\\d{1,3}$");
        if (!deleteRegexPattern.matcher(input.toLowerCase()).matches()) {
            throw new InvalidCommandException("Invalid Delete Command!");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber < 1) {
            throw new InvalidCommandException("Invalid Delete Command!");
        }
        return new DeleteCommand(taskNumber - 1);
    }

    /**
     * Parses the 'find' command to extract the search keyword.
     *
     * @param input User input starting with 'find'.
     * @return FindCommand with the search keyword.
     * @throws InvalidCommandException if no keyword is provided.
     */
    private static Command parseFindCommand(String input) throws InvalidCommandException {
        try {
            String keyword = input.split("\\s+", 2)[1];
            return new FindCommand(keyword);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid Find Command!");
        }
    }

    /**
     * Parses the 'todo' command to create a ToDo task.
     *
     * @param input User input starting with 'todo'.
     * @return ToDoTaskCommand containing the ToDo object.
     * @throws InvalidCommandException if no description is provided.
     */
    private static Command parseTodoCommand(String input) throws InvalidCommandException {
        try {
            String description = input.split("\\s+", 2)[1];
            return new ToDoTaskCommand(new ToDo(description));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid Todo Command!");
        }
    }

    /**
     * Parses the 'deadline' command and returns a DeadlineTaskCommand.
     *
     * @param input User input starting with 'deadline'.
     * @return DeadlineTaskCommand with the Deadline object.
     * @throws InvalidCommandException if input format is invalid.
     */
    private static Command parseDeadlineCommand(String input) throws InvalidCommandException {
        Pattern dateTimePattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}:\\d{2}$");

        try {
            String details = input.split("\\s+", 2)[1];
            String[] deadlineInfo = details.split("/by");
            String description = deadlineInfo[0].trim();
            String dateTime = deadlineInfo[1].trim();

            if (!dateTimePattern.matcher(dateTime).matches()) {
                throw new InvalidCommandException("Invalid Deadline Command!");
            }

            return new DeadlineTaskCommand(new Deadline(description, dateTime));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid Deadline Command!");
        }
    }

    /**
     * Parses the 'event' command to return an EventTaskCommand.
     *
     * @param input User input starting with 'event'.
     * @return EventTaskCommand containing the Event object.
     * @throws InvalidCommandException if format is invalid.
     */
    private static Command parseEventCommand(String input) throws InvalidCommandException {
        Pattern eventInfoRegexPattern = Pattern.compile(
                "^(.+?)/from (\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}:\\d{2}) /to (\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}:\\d{2})$"
        );

        try {
            String content = input.split("\\s+", 2)[1];
            Matcher eventMatcher = eventInfoRegexPattern.matcher(content);
            if (!eventMatcher.matches()) {
                throw new InvalidCommandException("Invalid Event Command!");
            }

            return new EventTaskCommand(new Event(
                    eventMatcher.group(1).trim(),
                    eventMatcher.group(2).trim(),
                    eventMatcher.group(3).trim()
            ));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid Event Command!");
        }
    }
}
