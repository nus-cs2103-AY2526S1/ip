package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DeleteTagCommand;
import command.EventCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TagCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import tasklist.TaskList;

/**
 * Parses user input strings into executable Command objects.
 * Handles validation and conversion of user commands.
 */
public class Parser {
    // Regex patterns for command validation
    private static final String TODO_PATTERN = "todo\\s.*";
    private static final String DEADLINE_PATTERN = "deadline\\s.*\\s/by\\s.*";
    private static final String EVENT_PATTERN = "event\\s.*\\s/from\\s.*\\s/to\\s.*";
    private static final String INDEX_COMMAND_PATTERN = "%s\\s\\d+";
    private static final String TAG_PATTERN = "^#.*";
    // Error messages
    private static final String EMPTY_COMMAND_MSG = "Empty command";
    private static final String UNRECOGNIZED_COMMAND_MSG = "bara-bara cannot recognize this command -> please try again";
    private static final String FIND_KEYWORD_MSG = "bara-bara needs a keyword to find related tasks";
    private static final String TODO_DESCRIPTION_MSG = "todo tasks need a description :O\ncorrect usage: todo <task_description>";
    private static final String DEADLINE_REQUIRED_MSG = ":( deadline tasks need deadlines\ncorrect usage: deadline <task_description> /by <task_deadline>";
    private static final String EVENT_DATES_MSG = ":( events needs start and end dates\ncorrect usage: event <event_description> /from <start_date> /to <end_date>";
    private static final String EXTRA_ARGS_MSG = "Please do not add anything behind %s command\ncorrect usage: %s";
    private static final String INDEX_REQUIRED_MSG = "%s requires an integer argument!\ncorrect usage: %s <task_number>\nwhere task_number is the number in front of the task after the list command";
    private static final String INDEX_OUT_OF_BOUNDS_MSG = "bara-bara can't find this task in the list :(\nrange: %d - %d";
    public static final String TAG_ERROR_MSG = "tag command format is wrong \nCorrect usage: tag <task_index> <#tag1> <#tag2> ...";
    private static final String DTAG_ERROR_MSG = "dtag command format is wrong \nCorrect usage: dtag <task_index> <#tag1> <#tag2> ...";

    /**
     * Parses the user input string and returns the corresponding Command object.
     * Validates input format and handles various command types with appropriate error messages.
     *
     * @param input    the user input string to parse
     * @param taskList the current task list used for validation (e.g., index bounds checking)
     * @return a Command object corresponding to the parsed input, or InvalidCommand on error
     */
    public static Command parse(String input, TaskList taskList) {
        String[] tokens = input.split("\\s");

        if (tokens.length == 0) {
            return new InvalidCommand(EMPTY_COMMAND_MSG);
        }

        String commandWord = tokens[0];

        try {
            return switch (commandWord) {
                case "bye" -> parseBye(tokens);
                case "list" -> parseList(tokens);
                case "mark" -> parseMark(input, taskList, tokens);
                case "unmark" -> parseUnmark(input, taskList, tokens);
                case "todo" -> parseTodo(input);
                case "deadline" -> parseDeadline(input);
                case "event" -> parseEvent(input);
                case "delete" -> parseDelete(input, taskList, tokens);
                case "find" -> parseFind(tokens);
                case "tag" -> parseTag(tokens, taskList);
                case "dtag" -> parseDTag(tokens, taskList);
                default -> parseInvalid();
            };
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Parses the 'dtag' command to remove tags from a specific task.
     * Validates the command format, extracts the task index and tags to remove,
     * and returns a DeleteTagCommand object.
     *
     * @param tokens the tokenized user input command
     * @param taskList the current task list used for index validation
     * @return a DeleteTagCommand object for execution
     * @throws IllegalArgumentException if the command format is invalid
     * @throws IndexOutOfBoundsException if the task index is out of bounds
     * <p>
     * AI-Assisted Development: This method was refined with Deepseek assistance
     * to handle immutable list conversion issues. The AI suggested using
     * `new ArrayList<>(Arrays.asList(tagsArr))` instead of `Arrays.stream(tagsArr).toList()`
     * to avoid UnsupportedOperationException when modifying tags later in the execution flow.
     * This ensures the tags list remains mutable throughout the command processing.
     */

    private static Command parseDTag(String[] tokens, TaskList taskList) {
        int index = validateTagAndGetIndex(taskList.size(), tokens);
        String[] tagsArr = Arrays.copyOfRange(tokens, 2, tokens.length);
        List<String> tags = new ArrayList<>(Arrays.asList(tagsArr));
        return new DeleteTagCommand(index, tags);
    }

    private static Command parseTag(String[] tokens, TaskList taskList) {
        int index = validateTagAndGetIndex(taskList.size(), tokens);
        String[] tagsArr = Arrays.copyOfRange(tokens, 2, tokens.length);
        List<String> tags = Arrays.stream(tagsArr).toList();
        return new TagCommand(index, tags);

    }

    private static InvalidCommand parseInvalid() {
        return new InvalidCommand(UNRECOGNIZED_COMMAND_MSG);
    }

    private static Command parseFind(String[] tokens) {
        if (!checkCommandLongerThan(tokens, 2)) {
            return new InvalidCommand(FIND_KEYWORD_MSG);
        }
        String[] searchTerms = Arrays.copyOfRange(tokens, 1, tokens.length);
        return new FindCommand(searchTerms);
    }

    private static DeleteCommand parseDelete(String input, TaskList taskList, String[] tokens) {
        int deleteIndex = validateAndGetIndex(tokens, input, "delete", taskList.size());
        assert taskList.size() >= 0 : "size of task list cannot be negative";
        return new DeleteCommand(deleteIndex);
    }

    private static EventCommand parseEvent(String input) {
        validateEvent(input);
        String eventInput = input.replaceFirst("event\\s", "");
        String[] eventParts = eventInput.split("\\s/from\\s");
        String[] dates = eventParts[1].split("\\s/to\\s");
        return new EventCommand(eventParts[0], dates[0], dates[1]);
    }

    private static DeadlineCommand parseDeadline(String input) {
        validateDeadline(input);
        String deadlineInput = input.replaceFirst("deadline\\s", "");
        String[] deadlineParts = deadlineInput.split("\\s/by\\s");
        return new DeadlineCommand(deadlineParts[0], deadlineParts[1]);
    }

    private static TodoCommand parseTodo(String input) {
        validateTodo(input);
        String todoDesc = input.replaceFirst("todo\\s", "");
        return new TodoCommand(todoDesc);
    }

    private static UnmarkCommand parseUnmark(String input, TaskList taskList, String[] tokens) {
        int unmarkIndex = validateAndGetIndex(tokens, input, "unmark", taskList.size());
        assert taskList.size() >= 0 : "size of task list cannot be negative";
        return new UnmarkCommand(unmarkIndex);
    }

    private static MarkCommand parseMark(String input, TaskList taskList, String[] tokens) {
        int markIndex = validateAndGetIndex(tokens, input, "mark", taskList.size());
        assert taskList.size() >= 0 : "size of task list cannot be negative";
        return new MarkCommand(markIndex);
    }

    private static ListCommand parseList(String[] tokens) {
        validateNoExtraArguments(tokens, "list");
        return new ListCommand();
    }

    private static ByeCommand parseBye(String[] tokens) {
        validateNoExtraArguments(tokens, "bye");
        return new ByeCommand();

    }

    private static void validateNoExtraArguments(String[] tokens, String command) {
        if (tokens.length != 1) {
            throw new IllegalArgumentException(String.format(EXTRA_ARGS_MSG, command, command));
        }
    }

    private static int validateAndGetIndex(String[] tokens, String input, String command, int size) {
        String pattern = String.format(INDEX_COMMAND_PATTERN, command);
        if (tokens.length != 2 || !Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(String.format(INDEX_REQUIRED_MSG, command, command));
        }

        int index = Integer.parseInt(tokens[1]) - 1;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format(INDEX_OUT_OF_BOUNDS_MSG, 1, size));
        }

        return index;
    }

    private static int validateTagAndGetIndex(int size, String... tokens) {
        if(tokens.length < 3) {
            String errorMsg = tokens[0].equals("tag") ? TAG_ERROR_MSG : DTAG_ERROR_MSG;
            throw new IllegalArgumentException(errorMsg);
        }
        int index = Integer.parseInt(tokens[1]) - 1;
        for (int i = 2; i < tokens.length; i++) {
            String potentialTag = tokens[i];
            if (!Pattern.matches(TAG_PATTERN, potentialTag)) {
                String errorMsg = tokens[0].equals("tag") ? TAG_ERROR_MSG : DTAG_ERROR_MSG;
                throw new IllegalArgumentException(errorMsg);
            }
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format(INDEX_OUT_OF_BOUNDS_MSG, 1, size));
        }
        return index;

    }

    private static void validateTodo(String input) {
        if (!Pattern.matches(TODO_PATTERN, input)) {
            throw new IllegalArgumentException(TODO_DESCRIPTION_MSG);
        }
    }

    private static void validateDeadline(String input) {
        if (!Pattern.matches(DEADLINE_PATTERN, input)) {
            throw new IllegalArgumentException(DEADLINE_REQUIRED_MSG);
        }
    }

    private static void validateEvent(String input) {
        if (!Pattern.matches(EVENT_PATTERN, input)) {
            throw new IllegalArgumentException(EVENT_DATES_MSG);
        }
    }

    private static boolean checkCommandLongerThan(String[] tokens, int n) {
        return tokens.length >= n;
    }
}