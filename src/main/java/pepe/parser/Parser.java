package pepe.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pepe.command.Command;
import pepe.command.DeadlineCommand;
import pepe.command.DeleteCommand;
import pepe.command.EventCommand;
import pepe.command.ExitCommand;
import pepe.command.FindCommand;
import pepe.command.ListCommand;
import pepe.command.MarkCommand;
import pepe.command.TodoCommand;
import pepe.command.UnmarkCommand;
import pepe.exception.PepeExceptions;
import pepe.task.Deadlines;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.ToDos;

/**
 * Parser class for converting user input strings into executable Commands.
 * <p>
 * It uses regular expressions to identify the type of command and extracts
 * necessary arguments for task creation or manipulation.
 */
public class Parser {

    //Regex Patterns
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo\\s+(.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to"
                    + "\\s+(.+)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern MARK_PATTERN = Pattern.compile("^mark\\s+(\\d+(?:\\s+\\d+)*)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern UNMARK_PATTERN = Pattern.compile("^unmark\\s+(\\d+(?:\\s+\\d+)*)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete\\s+(\\d+(?:\\s+\\d+)*)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern FIND_PATTERN = Pattern.compile("^find\\s+(.+)$", Pattern.CASE_INSENSITIVE);

    /**
     * Parses a user input string and returns the corresponding Command object.
     * <p>
     * Supported commands include:
     * <ul>
     *     <li>bye</li>
     *     <li>list</li>
     *     <li>mark &lt;task-index&gt;</li>
     *     <li>unmark &lt;task-index&gt;</li>
     *     <li>delete &lt;task-index&gt;</li>
     *     <li>todo &lt;task-name&gt;</li>
     *     <li>deadline &lt;task-name&gt; /by &lt;deadline&gt;</li>
     *     <li>event &lt;task-name&gt; /from &lt;start-time&gt; /to &lt;end-time&gt;</li>
     * </ul>
     *
     * @param input the raw input string from the user
     * @return a Command object representing the action to be executed
     * @throws PepeExceptions if the input does not match any valid command format
     */
    public static Command parse(String input) throws PepeExceptions {
        String command = input.split(" ", 2)[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(input);
        case "unmark":
            return parseUnmarkCommand(input);
        case "delete":
            return parseDeleteCommand(input);
        case "todo":
            return parseToDoCommand(input);
        case "event":
            return parseEventCommand(input);
        case "deadline":
            return parseDeadlineCommand(input);
        case "find":
            return parseFindCommand(input);
        default:
            throw new PepeExceptions("These are the commands:\n"
                    + "Add Tasks: todo, deadline, event\n"
                    + "Mark/Unmark Tasks: mark, unmark\n"
                    + "View Tasks: list\n"
                    + "Delete Task: delete\n"
                    + "Find task: find\n");
        }

    }
    /**
     * Parses a 'mark' command.
     *
     * @param input user input string
     * @return MarkCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseMarkCommand(String input) throws PepeExceptions {
        Matcher markMatcher = MARK_PATTERN.matcher(input);
        if (markMatcher.matches()) {
            String[] parts = markMatcher.group(1).trim().split("\\s+");
            int[] indices = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                indices[i] = Integer.parseInt(parts[i]) - 1;
            }
            return new MarkCommand(indices);
        } else {
            throw new PepeExceptions("To mark a task: mark <task-index> (task-index is a valid number)");
        }
    }
    /**
     * Parses an 'unmark' command.
     *
     * @param input user input string
     * @return UnmarkCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseUnmarkCommand(String input) throws PepeExceptions {
        Matcher unmarkMatcher = UNMARK_PATTERN.matcher(input);
        if (unmarkMatcher.matches()) {
            String[] parts = unmarkMatcher.group(1).trim().split("\\s+");
            int[] indices = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                indices[i] = Integer.parseInt(parts[i]) - 1;
            }
            return new UnmarkCommand(indices);
        } else {
            throw new PepeExceptions("To unmark a task: unmark <task-index> (task-index is a valid number)");
        }
    }
    /**
     * Parses a 'delete' command.
     *
     * @param input user input string
     * @return DeleteCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseDeleteCommand(String input) throws PepeExceptions {
        Matcher deleteMatcher = DELETE_PATTERN.matcher(input);
        if (deleteMatcher.matches()) {
            String[] parts = deleteMatcher.group(1).trim().split("\\s+");
            int[] indices = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                indices[i] = Integer.parseInt(parts[i]) - 1;
            }
            return new DeleteCommand(indices);
        } else {
            throw new PepeExceptions("To delete a task: delete <task-index> (task-index is a valid number)");
        }
    }
    /**
     * Parses a 'todo' command.
     *
     * @param input user input string
     * @return TodoCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseToDoCommand(String input) throws PepeExceptions {
        Matcher todoMatcher = TODO_PATTERN.matcher(input);
        if (todoMatcher.matches()) {
            String taskName = todoMatcher.group(1);
            Task task = new ToDos(taskName);
            return new TodoCommand(task);
        } else {
            throw new PepeExceptions("Add a ToDo task: todo <task-name>");
        }
    }
    /**
     * Parses an 'event' command.
     *
     * @param input user input string
     * @return EventCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseEventCommand(String input) throws PepeExceptions {
        Matcher eventMatcher = EVENT_PATTERN.matcher(input);
        if (eventMatcher.matches()) {
            String taskName = eventMatcher.group(1);
            String startTime = eventMatcher.group(2);
            String endTime = eventMatcher.group(3);
            Task task = new Events(taskName, startTime, endTime);
            return new EventCommand(task);
        } else {
            throw new PepeExceptions("Add an Event Task: event <task-name> "
                    + "/from <start-time> "
                    + "/to <end-time> (In the format: yyyy-mm-dd)");
        }
    }
    /**
     * Parses a 'deadline' command.
     *
     * @param input user input string
     * @return DeadlineCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseDeadlineCommand(String input) throws PepeExceptions {
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(input);
        if (deadlineMatcher.matches()) {
            String taskName = deadlineMatcher.group(1);
            String deadline = deadlineMatcher.group(2);
            Task task = new Deadlines(taskName, deadline);
            return new DeadlineCommand(task);
        } else {
            throw new PepeExceptions("Add a Deadline Task: deadline <task-name> "
                    + "/by <deadline> (In the format: yyyy-mm-dd)");
        }
    }
    /**
     * Parses a 'find' command.
     *
     * @param input user input string
     * @return FindCommand object with task index
     * @throws PepeExceptions if format is invalid
     */
    private static Command parseFindCommand(String input) throws PepeExceptions {
        Matcher findMatcher = FIND_PATTERN.matcher(input);
        if (findMatcher.matches()) {
            String taskName = findMatcher.group(1);
            return new FindCommand(taskName);
        } else {
            throw new PepeExceptions("Find a Task: find <task-name>");
        }
    }
}
