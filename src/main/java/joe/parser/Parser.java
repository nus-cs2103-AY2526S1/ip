package joe.parser;

import joe.exception.InvalidJoeInputException;
import joe.storage.Storage;
import joe.task.Deadline;
import joe.task.Event;
import joe.task.TaskList;
import joe.task.ToDo;
import joe.task.Task;
import joe.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It connects user input with actions on the TaskList, handles storage
 * persistence, and formats output through the Ui.
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a {@code Parser} object that deals with inputs given by the user.
     *
     * @param tasks the {@link TaskList} object of tasks
     * @param storage the {@link Storage} object handling file persistence
     * @param ui the {@link Ui} object for displaying messages to the user
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.taskList = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Takes in the command input by the user and performs the respective actions.
     *
     * @param input the input command given by the user
     * @return the response message to be displayed to the user
     * @throws InvalidJoeInputException if the command is invalid or malformed
     */
    public String executeCommand(String input) throws InvalidJoeInputException {
        String[] parts = input.trim().split(" ");
        String command = parts[0];

        switch (command) {
        case "bye":
            return handleBye();
        case "list":
            return handleList();
        case "mark":
            return handleMark(parts);
        case "unmark":
            return handleUnmark(parts);
        case "todo":
            return handleTodo(input, parts);
        case "deadline":
            return handleDeadline(input, parts);
        case "event":
            return handleEvent(input, parts);
        case "delete":
            return handleDelete(parts);
        case "find":
            return handleFind(input, parts);
        default:
            throw new InvalidJoeInputException();
        }
    }

    // ---------------- Command Handlers ---------------- //

    /**
     * Handles the {@code bye} command.
     *
     * @return an empty string to indicate termination
     */
    private String handleBye() {
        return "Goodbye!";
    }

    /**
     * Handles the {@code list} command.
     *
     * @return a string representation of the current to-do list
     */
    private String handleList() {
        return ui.printTodoList(taskList);
    }

    /**
     * Handles the {@code mark} command.
     *
     * @param parts the split command input
     * @return the result of marking the task as done
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleMark(String[] parts) throws InvalidJoeInputException {
        int index = parseIndex(parts, "mark");
        String output = taskList.markTaskAsDone(index);
        storage.logTodoList(taskList);
        return output;
    }

    /**
     * Handles the {@code unmark} command.
     *
     * @param parts the split command input
     * @return the result of marking the task as not done
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleUnmark(String[] parts) throws InvalidJoeInputException {
        int index = parseIndex(parts, "unmark");
        String output = taskList.markTaskAsNotDone(index);
        storage.logTodoList(taskList);
        return output;
    }

    /**
     * Handles the {@code todo} command.
     *
     * @param input the full user input
     * @param parts the split command input
     * @return the result of adding a new to-do task
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleTodo(String input, String[] parts) throws InvalidJoeInputException {
        if (parts.length < 2) {
            throw new InvalidJoeInputException("todo", "Format to follow: todo <desc>,");
        }
        String description = input.split(" ", 2)[1];
        String output = taskList.addToList(new ToDo(description));
        storage.logTodoList(taskList);
        return output;
    }

    /**
     * Handles the {@code deadline} command.
     *
     * @param input the full user input
     * @param parts the split command input
     * @return the result of adding a new deadline task
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleDeadline(String input, String[] parts) throws InvalidJoeInputException {
        if (parts.length < 2 || !input.contains(" /by ")) {
            throw new InvalidJoeInputException("deadline", "Format to follow: deadline <desc> /by YYYY-MM-DD HHmm,");
        }
        String description = input.split(" ", 2)[1].split(" /by ")[0].trim();
        String by = input.split(" /by ")[1].trim();

        try {
            String output = taskList.addToList(new Deadline(description, by));
            storage.logTodoList(taskList);
            return output;
        } catch (DateTimeParseException e) {
            throw new InvalidJoeInputException("deadline",
                    "Date must be in format YYYY-MM-DD HHmm (e.g. 2025-09-19 2359)");
        }
    }

    /**
     * Handles the {@code event} command.
     *
     * @param input the full user input
     * @param parts the split command input
     * @return the result of adding a new event task
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleEvent(String input, String[] parts) throws InvalidJoeInputException {
        if (parts.length < 2 || !input.contains(" /from ") || !input.contains(" /to ")) {
            throw new InvalidJoeInputException("event",
                    "Format to follow: event <desc> /from YYYY-MM-DD /to YYYY-MM-DD,");
        }
        String description = input.split(" /from ")[0].split(" ", 2)[1].trim();
        String from = input.split(" /from ")[1].split(" /to ")[0].trim();
        String to = input.split(" /to ")[1].trim();

        try {
            String output = taskList.addToList(new Event(description, from, to));
            storage.logTodoList(taskList);
            return output;
        } catch (DateTimeParseException e) {
            throw new InvalidJoeInputException("event", "Dates must be in format YYYY-MM-DD (e.g. 2025-09-19)");
        }
    }

    /**
     * Handles the {@code delete} command.
     *
     * @param parts the split command input
     * @return the result of deleting the specified task
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleDelete(String[] parts) throws InvalidJoeInputException {
        int index = parseIndex(parts, "delete") - 1;
        String output = taskList.deleteFromList(index);
        storage.logTodoList(taskList);
        return output;
    }

    /**
     * Handles the {@code find} command.
     *
     * @param input the full user input
     * @param parts the split command input
     * @return a list of matching tasks
     * @throws InvalidJoeInputException if the input is invalid
     */
    private String handleFind(String input, String[] parts) throws InvalidJoeInputException {
        if (parts.length != 2) {
            throw new InvalidJoeInputException("find", "Provide exactly one keyword");
        }
        String keyword = input.split(" ", 2)[1];
        ArrayList<Task> tasks = new ArrayList<>(taskList.getTodoList());
        List<Task> matches = tasks.stream().filter(task -> Arrays.asList(task.toString().split(" ")).contains(keyword))
                .toList();
        return ui.printMatches(matches);
    }

    /**
     * Parses the index from a user command and validates it against the task list.
     *
     * @param parts the split command input
     * @param command the command name for error reporting
     * @return the validated task index
     * @throws InvalidJoeInputException if the index is missing, not a number, or
     * out of bounds
     */
    private int parseIndex(String[] parts, String command) throws InvalidJoeInputException {
        if (parts.length < 2) {
            throw new InvalidJoeInputException(command);
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidJoeInputException(command, "Index must be a number");
        }
        if (index < 1 || index > taskList.getLength()) {
            throw new InvalidJoeInputException(command, "Invalid index");
        }
        return index;
    }
}
