package simon.parser;

import simon.command.*;

import simon.exceptions.SimonException;

import simon.task.Deadline;
import simon.task.Event;
import simon.task.Task;
import simon.task.Todo;

/**
 * Handles the parsing of user input commands and file lines into Task objects.
 */
public class Parser {
    /**
     * Parses user input string and returns corresponding Command object.
     *
     * @param input User input string.
     * @return Corresponding Command object.
     * @throws SimonException.EmptyTaskException      If the command is missing required arguments.
     * @throws SimonException.UnknownCommandException If the command is not recognized.
     */
    // Command keywords
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_ON = "on";
    private static final String CMD_FIND = "find";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_HELP = "help";

    // Error messages
    private static final String ERROR_MARK = " Invalid mark command. Enter an integer after \"mark \",.";
    private static final String ERROR_UNMARK = " Invalid unmark command. Enter an integer after \"unmark \",.";
    private static final String ERROR_DELETE = " The index of the task to delete cannot be empty. Follow the format: delete <task index>.";
    private static final String ERROR_ON = " Invalid date for on command. Follow the format: on <yyyy-MM-dd>.";
    private static final String ERROR_FIND = " Invalid find command. Follow the format: find <keyword>.";
    private static final String ERROR_TODO = " The description of a todo cannot be empty. Follow the format: todo <description>.";
    private static final String ERROR_DEADLINE = " The description and deadline of a deadline task cannot be empty. Follow the format: deadline <description> /by <due date>.\nDates can follow the formats:\nd/M/yyyy HHmm,\nd/M/yyyy,\nyyyy-MM-dd HHmm,\nyyyy-MM-dd\n";
    private static final String ERROR_EVENT = " The description, start, and end of an event cannot be empty. Follow the format: event <description> /from <start date> /to <end date>.\nDates can follow the formats:\nd/M/yyyy HHmm,\nd/M/yyyy,\nyyyy-MM-dd HHmm,\nyyyy-MM-dd\n";
    private static final String ERROR_UNKNOWN = " Sorry, not trained for that. Use\n 'todo <description>',\n 'deadline <description> /by <due date>',\n and 'event <description> /from <start date> /to <end date>'\n to add a task :)";
    private static final String ERROR_EMPTY = " Input cannot be empty. Type 'help' for available commands.";

    public static Command parse(String input) throws SimonException.EmptyTaskException,
            SimonException.UnknownCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_EMPTY);
        }
        String[] words = input.trim().split(" ", 2);
        assert words.length > 0 : "Input command should not be empty";

        String command = words[0];
        String args = words.length > 1 ? words[1].trim() : "";

        switch (command) {
        case CMD_BYE:
            return new ExitCommand();
        case CMD_LIST:
            return new ListCommand();
        case CMD_MARK:
            return parseMark(args);
        case CMD_UNMARK:
            return parseUnmark(args);
        case CMD_DELETE:
            return parseDelete(args);
        case CMD_ON:
            return parseOn(args);
        case CMD_FIND:
            return parseFind(args);
        case CMD_TODO:
            return parseTodo(args);
        case CMD_DEADLINE:
            return parseDeadline(args);
        case CMD_EVENT:
            return parseEvent(args);
        case CMD_HELP:
            return new HelpCommand();
        default:
            throw new SimonException.UnknownCommandException(ERROR_UNKNOWN);
        }
    }

    /**
     * Parses the arguments for the mark command and returns a MarkCommand object.
     *
     * @param args The arguments string containing the task index to mark.
     * @return MarkCommand object with the specified task index.
     * @throws SimonException.EmptyTaskException If the arguments are empty or invalid.
     */
    private static Command parseMark(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_MARK);
        }
        try {
            int markIdx = Integer.parseInt(args) - 1;
            assert markIdx >= 0 : "Task index should be non-negative";
            return new MarkCommand(markIdx);
        } catch (NumberFormatException e) {
            throw new SimonException.EmptyTaskException(ERROR_MARK);
        }
    }

    /**
     * Parses the arguments for the unmark command and returns an UnmarkCommand object.
     *
     * @param args The arguments string containing the task index to unmark.
     * @return UnmarkCommand object with the specified task index.
     * @throws SimonException.EmptyTaskException If the arguments are empty or invalid.
     */
    private static Command parseUnmark(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_UNMARK);
        }
        try {
            int unmarkIdx = Integer.parseInt(args) - 1;
            assert unmarkIdx >= 0 : "Task index should be non-negative";
            return new UnmarkCommand(unmarkIdx);
        } catch (NumberFormatException e) {
            throw new SimonException.EmptyTaskException(ERROR_UNMARK);
        }
    }

    /**
     * Parses the arguments for the delete command and returns a DeleteCommand object.
     *
     * @param args The arguments string containing the task index to delete.
     * @return DeleteCommand object with the specified task index.
     * @throws SimonException.EmptyTaskException If the arguments are empty or invalid.
     */
    private static Command parseDelete(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_DELETE);
        }
        try {
            int delIdx = Integer.parseInt(args) - 1;
            assert delIdx >= 0 : "Task index should be non-negative";
            return new DeleteCommand(delIdx);
        } catch (NumberFormatException e) {
            throw new SimonException.EmptyTaskException(ERROR_DELETE);
        }
    }

    /**
     * Parses the arguments for the on command and returns an OnCommand object.
     *
     * @param args The arguments string containing the date to search for.
     * @return OnCommand object with the specified date.
     * @throws SimonException.EmptyTaskException If the arguments are empty.
     */
    private static Command parseOn(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_ON);
        }
        return new OnCommand(args);
    }

    /**
     * Parses the arguments for the find command and returns a FindCommand object.
     *
     * @param args The arguments string containing the keyword to search for.
     * @return FindCommand object with the specified keyword.
     * @throws SimonException.EmptyTaskException If the arguments are empty.
     */
    private static Command parseFind(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_FIND);
        }
        return new FindCommand(args);
    }

    /**
     * Parses the arguments for the todo command and returns an AddCommand object with a Todo task.
     *
     * @param args The arguments string containing the todo description.
     * @return AddCommand object with a Todo task.
     * @throws SimonException.EmptyTaskException If the arguments are empty.
     */
    private static Command parseTodo(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_TODO);
        }
        return new AddCommand(new Todo(args));
    }

    /**
     * Parses the arguments for the deadline command and returns an AddCommand object with a Deadline task.
     *
     * @param args The arguments string containing the deadline description and due date.
     * @return AddCommand object with a Deadline task.
     * @throws SimonException.EmptyTaskException If the arguments are empty or malformed.
     */
    private static Command parseDeadline(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_DEADLINE);
        }
        String[] deadlineParts = args.split(" /by ", 2);
        boolean valid = deadlineParts.length == 2 && !deadlineParts[0].trim().isEmpty() && !deadlineParts[1].trim().isEmpty();
        if (!valid) {
            throw new SimonException.EmptyTaskException(ERROR_DEADLINE);
        }
        return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
    }

    /**
     * Parses the arguments for the event command and returns an AddCommand object with an Event task.
     *
     * @param args The arguments string containing the event description, start date, and end date.
     * @return AddCommand object with an Event task.
     * @throws SimonException.EmptyTaskException If the arguments are empty or malformed.
     */
    private static Command parseEvent(String args) throws SimonException.EmptyTaskException {
        if (args.isEmpty()) {
            throw new SimonException.EmptyTaskException(ERROR_EVENT);
        }
        String[] eventParts = args.split(" /from | /to ", 3);
        boolean valid = eventParts.length == 3 && !eventParts[0].trim().isEmpty() && !eventParts[1].trim().isEmpty() && !eventParts[2].trim().isEmpty();
        if (!valid) {
            throw new SimonException.EmptyTaskException(ERROR_EVENT);
        }
        return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
    }

    /**
     * Parses a line from the save file and returns the corresponding Task object.
     * Returns null if the the type of task is not recognised.
     *
     * @param line Line from the save file.
     * @return Corresponding Task object.
     */
    public static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task = null;
            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                task = new Deadline(description, by);
                break;
            case "E":
                String start = parts.length > 3 ? parts[3] : "";
                String end = parts.length > 4 ? parts[4] : "";
                task = new Event(description, start, end);
                break;
            default:
                return null;
            }
            if (isDone && task != null) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            System.err.println("Failed to parse task from file: " + line);
            return null;
        }
    }

    /**
     * Converts Task object to string representation for saving to file.
     *
     * @param task Task object to convert.
     * @return String representation of the task for file storage.
     */
    public static String taskToFileString(Task task) {
        String type;
        String done = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            type = "T";
            return String.format("%s | %s | %s", type, done, task.getDescription());
        } else if (task instanceof Deadline) {
            type = "D";
            return String.format("%s | %s | %s | %s", type, done, task.getDescription(), ((Deadline) task).getBy());
        } else if (task instanceof Event) {
            type = "E";
            return String.format("%s | %s | %s | %s | %s", type, done, task.getDescription(), ((Event) task).getStart(), ((Event) task).getEnd());
        }
        return "";
    }
}