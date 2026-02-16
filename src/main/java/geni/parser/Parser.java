package geni.parser;

import java.util.ArrayList;

import geni.exception.GeniException;
import geni.storage.Storage;
import geni.task.Deadline;
import geni.task.Event;
import geni.task.Task;
import geni.task.TaskList;
import geni.task.Todo;
import geni.ui.UI;


/**
 * Parses user input and executes commands.
 * Supports adding, deleting, listing, and updating tasks.
 */
public class Parser {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a {@code Parser}.
     *
     * @param ui      UI for displaying messages
     * @param storage storage handler for saving tasks
     * @param tasks   task list to operate on
     */
    public Parser(UI ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }
    /**
     * Parses and executes a command string.
     *
     * @param inp raw user input
     * @throws GeniException if input is invalid or exit is requested
     */
    public String parseAndExecute(String inp) throws GeniException {
        assert inp != null : "parseAndExecute(): input must not be null";

        inp = inp.trim();
        String[] inpt = splitted(inp, " ");
        String command = inpt[0];

        if (isExitCommand(command)) {
            throw new GeniException("exit");
        }

        if (inp.isEmpty()) {
            throw new GeniException("Invalid input, type something.");
        }

        if (isChangingStatus(command)) {
            return handleChangeStatus(inpt);
        } else if (command.equals("list")) {
            return ui.formatList(tasks);
        } else if (command.equals("todo")) {
            return handleAddTodo(inp);
        } else if (command.equals("deadline")) {
            return handleAddDeadline(inp);
        } else if (command.equals("event")) {
            return handleAddEvent(inp);
        } else if (command.equals("delete")) {
            return handleDelete(inpt);
        } else if (command.equals("find")) {
            return handleFind(inp);
        } else if (command.equals("free")) {
            return handleFindFree(inp);
        } else {
            throw new GeniException("Sorry, I don’t know what \"" + command + "\" means.");
        }
    }
    /**
     * Handles marking or unmarking a task.
     *
     * @param inpt user input split into tokens
     * @throws GeniException if task number is invalid
     */
    private String handleChangeStatus(String[] inpt) throws GeniException {
        if (inpt.length < 2) {
            throw new GeniException("Please provide a task number to " + inpt[0] + ".");
        }
        int i = Integer.parseInt(inpt[1]) - 1;
        if (i < 0 || i >= tasks.size()) {
            throw new GeniException("Please enter a valid task number.");
        }

        Task task = tasks.get(i);
        if (inpt[0].equals("mark")) {
            if (!task.getStatusIcon().equals("X")) {
                task.markAsDone();
            }
            storage.saveMarkReplace(task, i);
            return ui.formatMark(task, true);
        } else {
            if (task.getStatusIcon().equals("X")) {
                task.markAsUndone();
            }
            storage.saveMarkReplace(task, i);
            return ui.formatMark(task, false);
        }
    }

    /**
     * Handles adding a {@code Todo} task.
     *
     * @param inp full input string
     * @throws GeniException if description is missing
     */
    private String handleAddTodo(String inp) throws GeniException {
        if (inp.length() <= 4) {
            throw new GeniException("A todo cannot be empty! Please add a description.");
        }
        Task task = new Todo(inp.substring(5).trim());
        tasks.add(task);
        storage.saveAdd(task);
        return ui.formatAdded(task, tasks.size());
    }
    /**
     * Handles adding a {@code Deadline} task.
     *
     * @param inp full input string
     * @throws GeniException if format is invalid
     */
    private String handleAddDeadline(String inp) throws GeniException {
        String localInp = inp.substring(9);
        String[] localOne = localInp.split("/by");
        if (localOne.length < 2) {
            throw new GeniException(
                    "Incorrect entry, enter both description of deadline and time it is due "
                            + "separated by /by.");
        }
        Task task = new Deadline(localOne[0].trim(), localOne[1].trim());
        tasks.add(task);
        storage.saveAdd(task);
        return ui.formatAdded(task, tasks.size());
    }
    /**
     * Handles adding an {@code Event} task.
     *
     * @param inp full input string
     * @throws GeniException if format is invalid
     */
    private String handleAddEvent(String inp) throws GeniException {
        String localInp = inp.substring(6);
        String[] localOne = localInp.split("/from");
        if (localOne.length < 2) {
            throw new GeniException("Incorrect format, enter in format: event description /from time /to time.");
        }
        String desc = localOne[0].trim();
        String[] localTwo = localOne[1].trim().split("/to");
        if (localTwo.length < 2) {
            throw new GeniException("Incorrect format, enter in format: event description /from time /to time.");
        }
        String from = localTwo[0].trim();
        String to = localTwo[1].trim();
        Task task = new Event(desc, from, to);
        tasks.add(task);
        storage.saveAdd(task);
        return ui.formatAdded(task, tasks.size());
    }

    /**
     * Handles deleting a task.
     *
     * @param inpt user input split into tokens
     * @throws GeniException if task number is invalid
     */
    private String handleDelete(String[] inpt) throws GeniException {
        if (inpt.length < 2) {
            throw new GeniException("Please provide the task number to delete.");
        }
        int x = Integer.parseInt(inpt[1]) - 1;
        if (x < 0 || x >= tasks.size()) {
            throw new GeniException("Task number out of range, cannot delete.");
        }
        Task removed = tasks.delete(x);
        storage.savedelete(removed);
        return ui.formatDeleted(removed, tasks.size());
    }
    private String handleFind(String inp) throws GeniException {
        if (inp.length() <= 5) {
            throw new GeniException("Please specify a keyword to search for.");
        }
        String keyword = inp.substring(5).trim();
        ArrayList<Task> foundTasks = tasks.find(keyword);
        return ui.formatFoundTasks(foundTasks);
    }
    /**
     * Checks if a command is an exit command.
     *
     * @param input command string
     * @return true if input is "bye"
     */
    public boolean isExitCommand(String input) {
        return "bye".equals(input);
    }

    /**
     * Splits a string by a delimiter.
     *
     * @param string   input string
     * @param splitter delimiter
     * @return array of split strings
     */
    public String[] splitted(String string, String splitter) {
        return string.split(splitter);
    }
    /**
     * Checks if a command changes task status.
     *
     * @param string command string
     * @return true if "mark" or "unmark"
     */
    public boolean isChangingStatus(String string) {
        return string.equals("mark") || string.equals("unmark");
    }

    private String handleFindFree(String inp) throws GeniException {
        if (inp.length() <= 5) {
            throw new GeniException("Please specify a duration in hours, e.g., free 4.");
        }
        int hours;
        try {
            hours = Integer.parseInt(inp.substring(5).trim());
        } catch (NumberFormatException e) {
            throw new GeniException("Please enter a valid number of hours.");
        }
        return tasks.findFreeSlot(hours);
    }

}
