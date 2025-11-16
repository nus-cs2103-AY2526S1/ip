package tuesday.parser;

import tuesday.command.Command;
import tuesday.command.ListCommand;
import tuesday.command.EndCommand;
import tuesday.command.AddCommand;
import tuesday.command.DeleteCommand;
import tuesday.command.StatusCommand;
import tuesday.command.SortCommand;
import tuesday.command.FindCommand;
import tuesday.command.CommandEnums;
import tuesday.exception.TuesdayException;
import tuesday.command.CommandEnums.StatusAction;
import tuesday.task.TaskEnums.TaskType;

import java.util.Arrays;
import java.util.Map;


public class Parser {

    private static final String USE_FORMAT = "Use format: ";
    private static final String TODO_FORMAT = USE_FORMAT + "todo <desc>";
    private static final String DEADLINE_FORMAT = USE_FORMAT + "deadline <desc> /by dd-MM-yyyy HHmm";
    private static final String EVENT_FORMAT = USE_FORMAT + "event <desc> /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm";
    private static final String MISSING_INDEX = "Missing task index!";
    private static final String SORT_TIME_FORMAT = USE_FORMAT + "sort-time /by <type>";
    private static final String SORT_TYPE_FORMAT = USE_FORMAT + "sort-type /by <type>";
    private static final String LIST_COMMAND = "\"'. Valid commands include: todo, deadline, " +
            "event, list, mark, unmark, delete, find, sort-type, sort-time, bye.\"";


    private static final Map<String, CommandEnums.SortAction> SORT_COMMANDS = Map.of(
            "sort-type", CommandEnums.SortAction.TYPE,
            "sort-time", CommandEnums.SortAction.TIME
    );


    /**
     * Parse a user input string and returns a corresponding command object.
     * @param input: Input from user
     * @return Command representing the parsed input
     * @throws TuesdayException If the input cannot be parsed into a valid command
     */
    public static Command parse(String input) throws TuesdayException {

        if (input == null || input.trim().isEmpty()) {
            throw new TuesdayException("Empty input! Please enter a command.");
        }

        String[] words = input.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
        case "bye":
            return new EndCommand();

        case "list":
            return new ListCommand();

        case "mark":
        case "unmark":
            return parseStatus(words, commandWord);

        case "delete":
            return parseDelete(words);

        case "todo":
            return parseTodo(words);

        case "deadline":
            return parseDeadline(input);

        case "event":
            return parseEvent(input);

        case "find":
            return parseFind(input);
        case "sort-type":
        case "sort-time":
            return parseSort(input, SORT_COMMANDS.get(commandWord));
        default:
            throw new TuesdayException(
                    "Unknown command: '" + commandWord + LIST_COMMAND
            );
        }
    }

    /**
     * Parse a Status command
     * @param words: List of words from input
     * @param action: MARK or UNMARK
     * @return: Return StatusCommand
     * @throws TuesdayException
     */
    private static Command parseStatus(String[] words, String action) throws TuesdayException {
        if (words.length < 2) {
            throw new TuesdayException(MISSING_INDEX);
        }
        StatusAction status = action.equals("mark") ? StatusAction.MARK : StatusAction.UNMARK;
        return new StatusCommand(status, words[1]);
    }

    /**
     * Parse a AddCommand for To-do Task
     * @param words: Input list of words
     * @return: AddCommand with To-do constructors
     * @throws TuesdayException
     */
    private static Command parseTodo(String[] words) throws TuesdayException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new TuesdayException(TODO_FORMAT);
        }
        return new AddCommand(words[1].trim(), TaskType.TODO);
    }

    /**
     * Parse a command to add a Deadline Task
     * @param input: The untouch input from user
     * @return AddCommend with Deadline constructor
     * @throws TuesdayException
     */
    private static Command parseDeadline(String input) throws TuesdayException {
        String content = input.replaceFirst("(?i)^deadline\\s+", "");
        String[] parts = content.split("\\s*/by\\s*", 2);

        if (parts.length != 2) {
            throw new TuesdayException("Invalid deadline format. " + DEADLINE_FORMAT);
        }

        return new AddCommand(parts[0].trim(), TaskType.DEADLINE, parts[1].trim());
    }

    /**
     * Parse a command to add Event task
     * @param input: The untouched input from user
     * @return AddCommend with Event constructor
     * @throws TuesdayException
     */
    private static Command parseEvent(String input) throws TuesdayException {
        String content = input.replaceFirst("(?i)^event\\s+", "");
        String[] parts = content.split("\\s*/from\\s*|\\s*/to\\s*", 3);

        if (parts.length != 3) {
            throw new TuesdayException("Invalid event format. " + EVENT_FORMAT);
        }
        return new AddCommand(parts[0].trim(), TaskType.EVENT, parts[1].trim(), parts[2].trim());
    }

    /**
     * Parse Find command
     * @param input: untouched input from user
     * @return FindCommand
     * @throws TuesdayException
     */
    private static Command parseFind(String input) throws TuesdayException {
        String content = input.replaceFirst("(?i)^find\\s*", "").trim();
        if (content.isEmpty()) {
            throw new TuesdayException("Missing find keyword! Example: find book");
        }
        return new FindCommand(content);
    }

    /**
     * Parse Delete command
     * @param words: list of words from users
     * @return DeleteCommand
     * @throws TuesdayException
     */
    private static Command parseDelete(String[] words) throws TuesdayException {
        if (words.length < 2) {
            throw new TuesdayException(MISSING_INDEX);
        }
        return new DeleteCommand(words[1].trim());
    }

    /**
     * Parse any sort command in exist in this app
     * @param input: Raw input from user
     * @param action: Sort by time or type
     * @return SortCommand
     * @throws TuesdayException
     */
    private static Command parseSort(String input, CommandEnums.SortAction action) throws TuesdayException {
        // sort-type /by <type> OR sort-time /by <time>
        if (!input.contains("/by")) {
            throw new TuesdayException("Invalid sort format. " + SORT_TIME_FORMAT + " or " + SORT_TYPE_FORMAT);
        }

        String[] parts = input.split("\\s*/by\\s*", 2);
        if (parts.length != 2) {
            throw new TuesdayException("Invalid sort format. " + SORT_TIME_FORMAT + " or " + SORT_TYPE_FORMAT);
        }

        try {
            TaskType type = TaskType.valueOf(parts[1].trim().toUpperCase());
            return new SortCommand(action, type);
        } catch (IllegalArgumentException e) {
            throw new TuesdayException("Unknown task type for sorting: " + parts[1].trim());
        }
    }
}
