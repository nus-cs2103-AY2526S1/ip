package nyanchan.app;

import nyanchan.exceptions.NyanException;
import nyanchan.exceptions.IncorrectFormatException;
import nyanchan.tasks.Task;
import nyanchan.tasks.Deadline;
import nyanchan.tasks.Todo;
import nyanchan.tasks.Event;


/**
 * Handles parsing and executing user commands for Nyanchan.
 */
public class Parser {

    /**
     * Parses and runs a user command, returning Nyanchan’s response.
     *
     * @param input     user command input
     * @param taskList  current task list
     * @param ui        UI for message formatting
     * @param storage   storage handler for saving tasks
     * @return response message
     * @throws NyanException if command format or execution fails
     */
    public static String handleCommand(String input, TaskList taskList, Ui ui, Storage storage)
            throws NyanException {
        input = input.trim();
        String command = extractCommand(input);

        return switch (command) {
            case "bye" -> ui.showGoodbye();
            case "list" -> ui.showTaskList(taskList);
            case "mark" -> handleMark(input, taskList, ui, storage, true);
            case "unmark" -> handleMark(input, taskList, ui, storage, false);
            case "delete" -> handleDelete(input, taskList, ui, storage);
            case "todo" -> handleTodo(input, taskList, ui, storage);
            case "deadline" -> handleDeadline(input, taskList, ui, storage);
            case "event" -> handleEvent(input, taskList, ui, storage);
            case "find" -> handleFind(input, taskList, ui);
            default -> throw new NyanException("I don't recognize that command.");
        };
    }

    // ---------------- Utilities ---------------- //

    /** Extracts the command keyword from user input. */
    private static String extractCommand(String input) {
        return input.contains(" ") ? input.substring(0, input.indexOf(" ")) : input;
    }

    /** Parses a task index from the input. */
    private static int parseIndex(String input, TaskList taskList) throws NyanException {
        String[] tokens = input.split(" ");
        if (tokens.length < 2) {
            throw new NyanException("Command must include a task index.");
        }
        try {
            int index = Integer.parseInt(tokens[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                throw new NyanException("Invalid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NyanException("Task number should be an integer.");
        }
    }

    /** Saves the current task list to storage. */
    private static void saveTasks(Storage storage, TaskList taskList) throws NyanException {
        storage.save(taskList.getAll());
    }

    // ---------------- Command Handlers ---------------- //

    /** Marks or unmarks a task based on user input. */
    private static String handleMark(String input, TaskList taskList, Ui ui, Storage storage, boolean mark)
            throws NyanException {
        int index = parseIndex(input, taskList);
        Task task = taskList.get(index);
        if (mark) task.markAsDone();
        else task.markAsNotDone();
        saveTasks(storage, taskList);
        return mark ? ui.showMarkTask(task) : ui.showUnmarkTask(task);
    }

    /** Deletes a task by index. */
    private static String handleDelete(String input, TaskList taskList, Ui ui, Storage storage)
            throws NyanException {
        int index = parseIndex(input, taskList);
        Task task = taskList.delete(index);
        saveTasks(storage, taskList);
        return ui.showDeleteTask(taskList, task);
    }

    /** Adds a new Todo task. */
    private static String handleTodo(String input, TaskList taskList, Ui ui, Storage storage) throws NyanException {
        String description = parseTodoDescription(input);
        Task task = new Todo(description);
        taskList.add(task);
        saveTasks(storage, taskList);
        return description;
    }

    /** Parses Todo description from input. */
    private static String parseTodoDescription(String input) throws NyanException {
        String description = input.length() > 5 ? input.substring(5).trim() : "";
        if (description.isEmpty()) {
            throw new NyanException("The description of a todo cannot be empty.");
        }
        return description;
    }

    /** Adds a new Deadline task. */
    private static String handleDeadline(String input, TaskList taskList, Ui ui, Storage storage) throws NyanException {
        String[] parts = parseDeadlineInput(input);
        try {
            Task task = new Deadline(parts[0], parts[1]);
            taskList.add(task);
            saveTasks(storage, taskList);
            return ui.showAddTask(taskList, task);
        } catch (IncorrectFormatException e) {
            return ui.showError("Invalid date/time format. Use dd/MM/yyyy.");
        }
    }

    /** Parses Deadline description and time. */
    private static String[] parseDeadlineInput(String input) throws NyanException {
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new NyanException("Deadline format should be: deadline <desc> /by <time>.");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    /** Adds a new Event task. */
    private static String handleEvent(String input, TaskList taskList, Ui ui, Storage storage) throws NyanException {
        String[] eventParts = parseEventInput(input);
        try {
            Task task = new Event(eventParts[0], eventParts[1], eventParts[2]);
            taskList.add(task);
            saveTasks(storage, taskList);
            return ui.showAddTask(taskList, task);
        } catch (IncorrectFormatException e) {
            return ui.showError("Invalid date/time format for event. Use dd/MM/yyyy.");
        }
    }

    /** Parses Event description, start, and end times. */
    private static String[] parseEventInput(String input) throws NyanException {
        String[] parts = input.substring(6).split("/from", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new NyanException("Event format should be: event <desc> /from <start> /to <end>.");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new NyanException("Event format should be: event <desc> /from <start> /to <end>.");
        }
        return new String[]{description, timeParts[0].trim(), timeParts[1].trim()};
    }

    /** Finds and lists tasks that contain the given keyword. */
    private static String handleFind(String input, TaskList taskList, Ui ui) throws NyanException {
        String keyword = parseFindKeyword(input); // find already fulfills C-BetterSearch
        TaskList matchedTasks = findMatchingTasks(taskList, keyword);
        return ui.showFindResults(matchedTasks, keyword);
    }

    /** Parses the keyword for the find command. */
    private static String parseFindKeyword(String input) throws NyanException {
        String keyword = input.length() > 5 ? input.substring(5).trim() : "";
        if (keyword.isEmpty()) {
            throw new NyanException("Please provide a keyword to search.");
        }
        return keyword;
    }

    /** Returns all tasks containing the keyword. */
    private static TaskList findMatchingTasks(TaskList taskList, String keyword) {
        TaskList matchedTasks = new TaskList();
        for (Task task : taskList.getAll()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
