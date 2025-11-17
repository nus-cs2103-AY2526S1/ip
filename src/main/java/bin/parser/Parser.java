package bin.parser;

import bin.exception.BinException;
import bin.exception.NoInputException;
import bin.exception.NoTaskDescriptionException;
import bin.exception.NoTaskNumberException;
import bin.task.Deadline;
import bin.task.Event;
import bin.task.TaskList;
import bin.task.Todo;

// use of ChatGPT to improve structure of code

/**
 * Responsible for parsing user commands and delegating them
 * to appropriate operations on the task list.
 */
public class Parser {
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_FIND = "find";

    private final TaskList tasks;
    private final String command;

    /**
     * Creates a Parser object that processes the given command
     * on the provided task list.
     *
     * @param command The raw user input.
     * @param tasks   The list of tasks to operate on.
     */
    public Parser(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
     * Parses the user's input and executes the corresponding command.
     *
     * @return A message indicating the result of executing the command.
     */
    public String parseCommand() {
        try {
            String[] commandParts = command.split("\\s+", 2);
            String action = commandParts[0];
            String args = commandParts.length > 1 ? commandParts[1] : "";

            return switch (action) {
                case CMD_LIST -> handleList();
                case CMD_MARK -> handleMark(args);
                case CMD_UNMARK -> handleUnmark(args);
                case CMD_TODO -> handleTodo(args);
                case CMD_DEADLINE -> handleDeadline(args);
                case CMD_EVENT -> handleEvent(args);
                case CMD_DELETE -> handleDelete(args);
                case CMD_FIND -> handleFind(args);
                default -> throw new NoInputException("instruction");
            };
        } catch (BinException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "list" command.
     *
     * @return The string representation of the task list.
     */
    private String handleList() {
        return tasks.toString();
    }

    /**
     * Handles the "mark" command.
     *
     * @param args The argument string containing the task number.
     * @return Message indicating the task has been marked.
     * @throws NoTaskNumberException If the task number is missing or invalid.
     */
    private String handleMark(String args) throws NoTaskNumberException {
        int taskIndex = parseTaskNumber(args);
        return tasks.mark(taskIndex);
    }

    /**
     * Handles the "unmark" command.
     *
     * @param args The argument string containing the task number.
     * @return Message indicating the task has been unmarked.
     * @throws NoTaskNumberException If the task number is missing or invalid.
     */
    private String handleUnmark(String args) throws NoTaskNumberException {
        int taskIndex = parseTaskNumber(args);
        return tasks.unmark(taskIndex);
    }

    /**
     * Handles the "todo" command.
     *
     * @param args The description of the todo task.
     * @return Message confirming the todo was added.
     * @throws NoTaskDescriptionException If no description is provided.
     */
    private String handleTodo(String args) throws NoTaskDescriptionException {
        if (args.isBlank()) {
            throw new NoTaskDescriptionException();
        }

        Todo task = new Todo(false, args.trim());
        if (tasks.checkDuplicate(task)) {
            return tasks.add(task);
        }
        return ""; // Silently ignore duplicates
    }

    /**
     * Handles the "deadline" command.
     *
     * @param args The description and deadline time.
     * @return Message confirming the deadline was added.
     * @throws BinException If required parts are missing or invalid.
     */
    private String handleDeadline(String args) throws BinException {
        if (args.isBlank()) {
            throw new NoTaskDescriptionException();
        }

        String[] parts = args.split("/by", 2);
        if (parts.length < 2) {
            throw new NoInputException("deadline");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        Deadline deadline = new Deadline(false, description, by);

        if (tasks.checkDuplicate(deadline)) {
            return tasks.add(deadline);
        }
        return "";
    }

    /**
     * Handles the "event" command.
     *
     * @param args The description, start time (/from), and end time (/to).
     * @return Message confirming the event was added.
     * @throws BinException If required parts are missing or invalid.
     */
    private String handleEvent(String args) throws BinException {
        if (args.isBlank()) {
            throw new NoTaskDescriptionException();
        }

        String[] partsFrom = args.split("/from", 2);
        if (partsFrom.length < 2) {
            throw new NoInputException("start time");
        }

        String description = partsFrom[0].trim();
        String[] partsTo = partsFrom[1].split("/to", 2);
        if (partsTo.length < 2) {
            throw new NoInputException("end time");
        }

        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        Event event = new Event(false, description, from, to);
        if (tasks.checkDuplicate(event)) {
            return tasks.add(event);
        }
        return "";
    }

    /**
     * Handles the "delete" command.
     *
     * @param args The task number to delete.
     * @return Message confirming the task was deleted.
     * @throws NoTaskNumberException If task number is missing or invalid.
     */
    private String handleDelete(String args) throws NoTaskNumberException {
        int taskIndex = parseTaskNumber(args);
        return tasks.delete(taskIndex);
    }

    /**
     * Handles the "find" command.
     *
     * @param args The keyword to search for.
     * @return Search results from the task list.
     * @throws NoTaskDescriptionException If no keyword is provided.
     */
    private String handleFind(String args) throws NoTaskDescriptionException {
        if (args.isBlank()) {
            throw new NoTaskDescriptionException();
        }
        return tasks.search(args.trim());
    }

    /**
     * Parses a string into a valid task number.
     *
     * @param input The string representing the task number.
     * @return The integer index of the task.
     * @throws NoTaskNumberException If the input is invalid or out of range.
     */
    private int parseTaskNumber(String input) throws NoTaskNumberException {
        if (input == null || input.isBlank()) {
            throw new NoTaskNumberException();
        }

        try {
            int index = Integer.parseInt(input.trim());
            if (index <= 0 || index > tasks.size()) {
                throw new NoTaskNumberException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException();
        }
    }
}
