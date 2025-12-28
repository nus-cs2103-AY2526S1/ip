package logic.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.exceptions.InvalidCommandException;
import logic.commands.Command;
import logic.commands.CommandType;
import logic.commands.DeadlineCommand;
import logic.commands.DeleteCommand;
import logic.commands.EventCommand;
import logic.commands.ExitCommand;
import logic.commands.FindCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.ToDoCommand;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private static final String INVALID_COMMAND_MESSAGE = "Sorry, please enter a valid command"
            + "(mark / unmark / todo / deadline / event / list / delete / bye)";

    /**
     * Parses the user command string and returns a corresponding Command object
     *
     * @param command the raw user input command
     * @return a Command object representing the parsed command
     * @throws InvalidCommandException if the command format is invalid or
     *                                 parameters are missing
     */
    public static Command parseCommand(String command) throws InvalidCommandException {
        if (command.equals(CommandType.EXIT.getInputToMatch())) {
            return new ExitCommand();

        } else if (command.equals(CommandType.LIST.getInputToMatch())) {
            return new ListCommand();
        } else if (command.startsWith(CommandType.DELETE.getInputToMatch())) {
            return parseDeleteCommand(command);
        } else if (command.startsWith(CommandType.MARK.getInputToMatch())) {
            return parseMarkCommand(command, true);
        } else if (command.startsWith(CommandType.UNMARK.getInputToMatch())) {
            return parseMarkCommand(command, false);
        } else if (command.startsWith(CommandType.ADD_TODO.getInputToMatch())) {
            return parseTodoCommand(command);
        } else if (command.startsWith(CommandType.ADD_DEADLINE.getInputToMatch())) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith(CommandType.ADD_EVENT.getInputToMatch())) {
            return parseEventCommand(command);
        } else if (command.startsWith(CommandType.FIND.getInputToMatch())) {
            return parseFindCommand(command);
        } else {
            throw new InvalidCommandException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Parses a delete command and extracts the task index
     *
     * @param command the delete command string
     * @return a DeleteCommand with the task index
     * @throws InvalidCommandException if the command format is invalid
     */
    private static Command parseDeleteCommand(String command) throws InvalidCommandException {
        Pattern pattern = Pattern.compile("^delete (\\d+)$");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            assert index > 0 : "Task index should be more than 0";
            return new DeleteCommand(index);
        } else {
            throw new InvalidCommandException("Please ensure the following format 'delete (positive integer)'");
        }
    }

    /**
     * Parses a mark/unmark command and extracts the task index and mark status
     *
     * @param command the mark/unmark command string
     * @param isDone  true for mark command, false for unmark command
     * @return a MarkCommand with the task index and mark status
     * @throws InvalidCommandException if the command format is invalid
     */
    private static Command parseMarkCommand(String command, boolean isDone) throws InvalidCommandException {
        Pattern pattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(2));
            assert index > 0 : "Task index should be more than 0";
            return new MarkCommand(index, isDone);
        } else {
            throw new InvalidCommandException("Please ensure the following format 'mark / unmark (positive integer)'");
        }
    }

    /**
     * Parses a todo command and extracts the task description
     *
     * @param command the todo command string
     * @return a ToDoCommand with the task description
     * @throws InvalidCommandException if the description is empty
     */
    private static Command parseTodoCommand(String command) throws InvalidCommandException {
        Map<String, String> params = parseCommandWithFlags(command.substring(5).trim(), "tag");

        String name = params.get("name");
        String tag = params.get("tag");

        if (name.isEmpty()) {
            throw new InvalidCommandException("Description of ToDo cannot be empty");
        }

        return new ToDoCommand(name, tag);
    }

    /**
     * Parses a deadline command and extracts the task description and due date
     *
     * @param command the deadline command string
     * @return a DeadlineCommand with description and due date
     * @throws InvalidCommandException if parameters are missing or invalid
     */
    private static Command parseDeadlineCommand(String command) throws InvalidCommandException {
        Map<String, String> params = parseCommandWithFlags(command.substring(9).trim(), "by", "tag");

        String name = params.get("name");
        String rawBy = params.get("by");
        String tag = params.get("tag");

        if (name.isEmpty()) {
            throw new InvalidCommandException("Description of Deadline cannot be empty");
        }
        if (rawBy.isEmpty()) {
            throw new InvalidCommandException("/by of Deadline cannot be empty");
        }

        try {
            LocalDate by = LocalDate.parse(rawBy);
            return new DeadlineCommand(name, by, tag);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format");
        }
    }

    /**
     * Parses an event command and extracts the task description, start date, and
     * end date
     *
     * @param command the event command string
     * @return an EventCommand with description and date range
     * @throws InvalidCommandException if parameters are missing or invalid
     */
    private static Command parseEventCommand(String command) throws InvalidCommandException {
        Map<String, String> params = parseCommandWithFlags(command.substring(6).trim(), "from", "to", "tag");

        String description = params.get("name");
        String rawFrom = params.get("from");
        String rawTo = params.get("to");
        String tag = params.get("tag");

        if (description.isEmpty()) {
            throw new InvalidCommandException("Description of Event cannot be empty");
        }
        if (rawFrom.isEmpty() || rawTo.isEmpty()) {
            throw new InvalidCommandException("/from or /to of Event cannot be empty");
        }

        try {
            LocalDate from = LocalDate.parse(rawFrom);
            LocalDate to = LocalDate.parse(rawTo);
            return new EventCommand(description, from, to, tag);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format");
        }
    }

    /**
     * Parses a find command and extracts the search keyword
     *
     * @param command the find command string
     * @return a FindCommand with the search keyword
     * @throws InvalidCommandException if the keyword is empty
     */
    private static Command parseFindCommand(String command) throws InvalidCommandException {
        String keyword = command.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new InvalidCommandException("Keyword to find must not be empty");
        }
        return new FindCommand(keyword);
    }

    /**
     * Parses a command string with flags and returns a map of parameter values.
     * The first part before any flags is stored with key "name".
     * Flags can appear in any order.
     *
     * @param commandString the command string to parse (without the command prefix)
     * @param flags         the expected flags (without the leading '/')
     * @return a map containing parameter values with flag names as keys
     * @throws InvalidCommandException if the command format is invalid
     */
    private static Map<String, String> parseCommandWithFlags(String commandString, String... flags)
            throws InvalidCommandException {
        Map<String, String> params = new HashMap<>();

        // Initialize all flags with empty strings
        for (String flag : flags) {
            params.put(flag, "");
        }

        // The main description is everything before the first flag
        int firstFlagIndex = findFirstFlagIndex(commandString, flags);
        if (firstFlagIndex == -1) {
            // No flags found, everything is the description
            params.put("name", commandString.trim());
            return params;
        }

        // Extract the description (before the first flag)
        String description = commandString.substring(0, firstFlagIndex).trim();
        params.put("name", description);

        // Process the remaining string with flags
        String remaining = commandString.substring(firstFlagIndex);

        // Use regex to extract all flag-value pairs
        Pattern flagPattern = Pattern.compile("/(\\w+)\\s+([^/]+)");
        Matcher matcher = flagPattern.matcher(remaining);

        while (matcher.find()) {
            String flagName = matcher.group(1);
            String flagValue = matcher.group(2).trim();

            // Only store flags that we expect
            if (contains(flags, flagName)) {
                params.put(flagName, flagValue);
            }
        }

        return params;
    }

    /**
     * Finds the index of the first occurrence of any flag in the given string
     *
     * @param input the string to search in
     * @param flags the flags to search for (without leading '/')
     * @return the index of the first flag found, or -1 if no flags found
     */
    private static int findFirstFlagIndex(String input, String[] flags) {
        int firstIndex = -1;
        for (String flag : flags) {
            int index = input.indexOf("/" + flag);
            if (index != -1 && (firstIndex == -1 || index < firstIndex)) {
                firstIndex = index;
            }
        }
        return firstIndex;
    }

    /**
     * Checks if an array contains a specific string
     *
     * @param array the array to search in
     * @param value the value to search for
     * @return true if the array contains the value, false otherwise
     */
    private static boolean contains(String[] array, String value) {
        for (String item : array) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
