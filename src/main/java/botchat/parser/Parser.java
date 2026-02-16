package botchat.parser;

import botchat.app.BotChatException;
import botchat.storage.Store;
import botchat.task.TaskList;
import botchat.task.Task;
import botchat.task.Deadline;
import botchat.task.Event;
import botchat.task.Todo;
import botchat.task.DoAfter;
import botchat.ui.Ui;
import botchat.util.DateTime;

/**
 * Parses user inputs from BotChat into commands.
 */
public class Parser {

    /**
     * Returns the index mentioned in the user input.
     *
     * @param input User input string.
     * @return index of task in TaskList.
     */
    private static Integer getInt(String input) {
        assert input != null : "Input cannot be null";

        String[] words = input.split(" ");
        return Integer.parseInt(words[1]);
    }

    /**
     * Determines if input signals end of program.
     *
     * @param input the string entered by the user.
     * @return true if command is bye, false otherwise.
     */
    public static boolean exit(String input) {
        input = input.trim();
        return input.equals("bye");
    }

    /**
     * Interprets and executes user's command.
     *
     * @param input string entered by user.
     * @param tasks the list of tasks to be changed.
     * @param ui    the user interface for showing the responses.
     * @param store to store the tasks.
     * @throws BotChatException if the command is invalid.
     */
    public static void command(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        assert input != null : "Input cannot be null";
        assert tasks != null : "Tasks cannot be null";
        assert ui != null : "Ui cannot be null";
        assert store != null : "Store cannot be null";

        input = input.trim();

        if (commandBye(input, ui)) {
            return;
        }
        if (commandList(input, tasks, ui)) {
            return;
        }
        if (commandMark(input, tasks, ui, store)) {
            return;
        }
        if (commandUnmark(input, tasks, ui, store)) {
            return;
        }
        if (commandTodo(input, tasks, ui, store)) {
            return;
        }
        if (commandDeadline(input, tasks, ui, store)) {
            return;
        }
        if (commandEvent(input, tasks, ui, store)) {
            return;
        }
        if (commandDelete(input, tasks, ui, store)) {
            return;
        }
        if (commandFind(input, tasks, ui)) {
            return;
        }
        if (commandDoAfter(input, tasks, ui, store)) {
            return;
        }

        throw new BotChatException("Unknown command: " + input);
    }

    /**
     * Handles the "doafter" command.
     *
     * @param input full user input line
     * @param tasks full user input line
     * @param ui instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     * @throws BotChatException if the command is malformed or missing the "/after" condition
     */
    private static boolean commandDoAfter(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("doafter")) {
            return false;
        }
        String removeCommand = input.substring(8).trim();
        String[] words = removeCommand.split("/after");

        if (words.length != 2 || words[1].isEmpty()) {
            throw new BotChatException("OH NO! doafter is missing a condition");
        }

        Task task = new DoAfter(words[0].trim(), words[1].trim());
        tasks.addTask(task);
        ui.displayAdd(task, tasks);
        store.saveTasks(tasks.getTasks());
        return true;
    }

    /**
     * Handles the "bye" command
     * @param input full user input line
     * @param ui instance used to display feedback
     * @return {@code true} if the input equals "bye"; {@code false} otherwise
     */
    private static boolean commandBye(String input, Ui ui) {
        if (!input.equals("bye")) {
            return false;
        }
        ui.displayBye();
        return true;
    }

    /**
     * Handles the "list" command.
     *
     * @param input full user input line
     * @param tasks the current task list
     * @param ui instance used to display feedback
     * @return {@code true} if the input equals "list"; {@code false} otherwise
     */
    private static boolean commandList(String input, TaskList tasks, Ui ui) {
        if (!input.equals("list")) {
            return false;
        }
        ui.displayList(tasks);
        return true;
    }

    /**
     * Handles the "mark" command.
     * @param input full user input line
     * @param tasks the current task list
     * @param ui instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     */
    private static boolean commandMark(String input, TaskList tasks, Ui ui, Store store) {
        if (!input.startsWith("mark")) {
            return false;
        }
        Integer index = getInt(input);
        if (index != null && index <= tasks.size() && index > 0) {
            tasks.get(index - 1).markAsDone();
            ui.displayMark(tasks.get(index - 1));
        }
        store.saveTasks(tasks.getTasks());
        return true;
    }

    /**
     * Handles the "unmark" command.
     *
     * @param input  full user input line
     * @param tasks the current task list
     * @param ui  instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     */
    private static boolean commandUnmark(String input, TaskList tasks, Ui ui, Store store) {
        if (!input.startsWith("unmark")) {
            return false;
        }
        Integer index = getInt(input);
        if (index != null && index <= tasks.size() && index > 0) {
            tasks.get(index - 1).markAsNotDone();
            ui.displayUnmark(tasks.get(index - 1));
        }
        store.saveTasks(tasks.getTasks());
        return true;

    }

    /**
     * Handles the "todo" command.
     *
     * @param input full user input line
     * @param tasks the current task list
     * @param ui instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     * @throws BotChatException if the description is missing or empty
     */
    private static boolean commandTodo(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("todo")) {
            return false;
        }
        if (input.length() <= 5) {
            throw new BotChatException("OH NO! todo is missing a description");
        }
        String description = input.substring(5).trim();
        Task t = new Todo(description);
        tasks.addTask(t);
        ui.displayAdd(t, tasks);
        store.saveTasks(tasks.getTasks());
        return true;

    }

    /**
     * Handles the "deadline" command.
     *
     * @param input full user input line
     * @param tasks the current task list
     * @param ui instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     * @throws BotChatException if the description or /by value is missing, or the date string is invalid
     */
    private static boolean commandDeadline(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("deadline")) {
            return false;
        }
        if (input.length() <= 9) {
            throw new BotChatException("OH NO! deadline is missing a description");
        }

        String removeDeadline = input.substring(9).trim();
        String[] remainingCommand = removeDeadline.split("/by");

        checkLength(remainingCommand, 2 , "OH NO! deadline is missing a deadline");
        DateTime.validateDateString(remainingCommand[1]);

        Task t = new Deadline(remainingCommand[0].trim(), remainingCommand[1].trim());
        tasks.addTask(t);
        ui.displayAdd(t, tasks);
        store.saveTasks(tasks.getTasks());
        return true;
    }

    /**
     * Handles the "event" command.
     *
     * @param input full user input line
     * @param tasks the current task list
     * @param ui instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     * @throws BotChatException BotChatException if description is missing, /from or /to are missing/empty,
     *      or date-time strings are invalid.
     */
    private static boolean commandEvent(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("event")) {
            return false;
        }
        if (input.length() <= 6) {
            throw new BotChatException("OH NO! event is missing a description");
        }

        String removeEvent = input.substring(6).trim();
        String[] remaining = removeEvent.split("/from");
        checkLength(remaining , 2 , "OH NO! event must include a /from and /to");

        String description = remaining[0].trim();
        String[] remainingDate = remaining[1].split("/to");
        checkLength(remainingDate , 2 , "OH NO! event must include a /from and /to");

        String from = remainingDate[0].trim();
        String to = remainingDate[1].trim();
        DateTime.validateDateTimeString(to);
        DateTime.validateDateTimeString(from);

        Task t = new Event(description, from, to);
        tasks.addTask(t);
        ui.displayAdd(t, tasks);
        store.saveTasks(tasks.getTasks());
        return true;

    }

    private static void checkLength(String[] input, int length, String message) throws BotChatException {
        if (input.length < length) {
            throw new BotChatException(message);
        }
    }

    /**
     * Handles the "delete" command.
     *
     * @param input full user input line
     * @param tasks the current task list
     * @param ui instance used to display feedback
     * @param store persistent storage for tasks
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     * @throws BotChatException BotChatException if the command omits a required index
     */
    private static boolean commandDelete(String input, TaskList tasks, Ui ui, Store store) throws BotChatException {
        if (!input.startsWith("delete")) {
            return false;
        }

        if (input.length() <= 7) {
            throw new BotChatException("OH NO! delete is missing a value");
        }

        Integer index = getInt(input);

        if (index != null && index > 0 && index <= tasks.size()) {
            Task remove = tasks.deleteTask(index - 1);
            ui.displayDelete(remove, tasks);
        }

        store.saveTasks(tasks.getTasks());
        return true;

    }

    /**
     * Handles the "find" command.
     *
     * @param input full user input line
     * @param tasks the current task lis
     * @param ui instance used to display feedback
     * @return {@code true} if this handler recognized the command; {@code false} otherwise
     * @throws BotChatException if the keyword is missing or empty
     */
    private static boolean commandFind(String input, TaskList tasks, Ui ui) throws BotChatException {
        if (!input.startsWith("find")) {
            return false;
        }
        if (input.length() <= 5) {
            throw new BotChatException("OH NO! find is missing a value");
        }

        String find = input.substring(5).trim();
        TaskList findResults = tasks.find(find);
        ui.displayFind(findResults);
        return true;
    }
}

