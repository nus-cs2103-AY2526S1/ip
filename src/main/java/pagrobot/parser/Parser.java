package pagrobot.parser;

import java.time.format.DateTimeParseException;
import java.util.Locale;

import pagrobot.errors.InvalidCommandException;
import pagrobot.errors.InvalidTaskException;
import pagrobot.tasklist.TaskList;
import pagrobot.tasks.Deadline;
import pagrobot.tasks.Event;
import pagrobot.tasks.Task;
import pagrobot.tasks.ToDo;
import pagrobot.ui.Ui;

/**
 * Interprets user input and converting it into commands or tasks.
 */
public class Parser {
    private String[] taskTypes = { "TODO", "DEADLINE", "EVENT" };
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Creates a Parser that will process user input into commands.
     *
     * @param taskList the list of tasks to operate on.
     * @param ui the user interface to interact with.
     */
    public Parser(TaskList taskList, Ui ui) {
        assert taskList != null : "taskList must not be null";
        assert ui != null : "ui must not be null";
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses a user input line, executes the corresponding action, and returns
     * the UI-rendered response. Throws when the command is invalid.
     *
     * @param input raw user input.
     * @return response string to display.
     * @throws InvalidCommandException if the command or its arguments are invalid.
     */
    public String handleMessage(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidCommandException();
        }

        String trimmed = input.trim();
        int space = trimmed.indexOf(' ');
        String command = (space == -1 ? trimmed : trimmed.substring(0, space)).toLowerCase(Locale.ROOT);
        String rest = (space == -1 ? "" : trimmed.substring(space + 1)).trim();

        switch (command) {
        case "list":
            return ui.list(taskList);
        case "mark": {
            int idx = parsePositiveIndex(rest);
            ensureInRange(idx, taskList.size());
            Task marked = taskList.mark(idx - 1);
            return ui.mark(marked);
        }
        case "unmark": {
            int idx = parsePositiveIndex(rest);
            ensureInRange(idx, taskList.size());
            Task unmarked = taskList.unmark(idx - 1);
            return ui.unmark(unmarked);
        }
        case "delete": {
            int idx = parsePositiveIndex(rest);
            ensureInRange(idx, taskList.size());
            Task deleted = taskList.remove(idx - 1);
            return ui.delete(deleted, taskList.size());
        }
        case "find": {
            if (rest.isBlank()) {
                throw new InvalidCommandException();
            }
            TaskList results = this.findTask(rest);
            return ui.findTask(results);
        }
        case "sort":
            taskList.sortSmart();
            return ui.sort(taskList);

        case "todo":
        case "deadline":
        case "event": {
            Task t = this.createTask(trimmed, new String[] { command });
            taskList.add(t);
            return ui.add(t, taskList.size());
        }
        case "help":
            return ui.help();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses a positive 1-based index from a string.
     *
     * @param s string that should contain a decimal integer.
     * @return parsed positive integer (>= 1).
     * @throws InvalidCommandException if missing, non-numeric, or not positive.
     */
    private static int parsePositiveIndex(String s) {
        if (s == null || s.isBlank()) {
            throw new InvalidCommandException();
        }
        try {
            int v = Integer.parseInt(s.trim());
            if (v <= 0) {
                throw new InvalidCommandException();
            }
            return v;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Ensures a 1-based index lies within [1, size].
     *
     * @param oneBased 1-based index to validate.
     * @param size list size.
     * @throws InvalidCommandException if out of range.
     */
    private static void ensureInRange(int oneBased, int size) {
        if (oneBased < 1 || oneBased > size) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Creates a Task object from the given user input.
     *
     * Returns a Task (ToDo, Deadline, Event) if parsing is successful; null
     * otherwise.
     *
     * @param input the raw input string from the user.
     * @param parts the input split into words.
     * @return a Task object or null if input is invalid.
     */
    public Task createTask(String input, String[] parts) throws InvalidTaskException {
        String taskType = parts[0];
        int first = input.indexOf(" ");
        int last = input.indexOf("/");
        try {
            switch (taskType) {
            case "todo":
                return new ToDo(input.substring(first + 1, input.length()));
            case "deadline":
                if (input.split("/by ").length < 2) {
                    throw new InvalidTaskException(taskType);
                }
                return new Deadline(input.substring(first + 1, last), input.split(" /by ")[1]);
            case "event":
                if (input.split("/from |/to ").length < 3) {
                    throw new InvalidTaskException(taskType);
                }
                return new Event(input.substring(first + 1, last), input.split(" /from | /to ")[1],
                        input.split(" /from | /to ")[2], false);
            default:
                throw new InvalidTaskException(taskType);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException(taskType);
        }
    }

    /**
     * Searches for tasks in the task list that contain the given keyword.
     *
     * Returns a TaskList containing all tasks whose description
     * includes the specified keyword. If no tasks match, the returned list will
     * be empty.
     *
     * @param taskName the keyword to search for within task descriptions.
     * @return a TaskList of tasks matching the given keyword.
     */
    public TaskList findTask(String taskName) {
        return this.taskList.findTask(taskName);
    }

}
