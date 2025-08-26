package lynx.parser;

import lynx.command.*;
import lynx.exception.LynxException;
import lynx.exception.MissingArgumentException;
import lynx.formatter.LynxDateManager;
import lynx.storage.LynxFileManager;
import lynx.storage.LynxStorage;
import lynx.storage.LynxTaskList;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;
import lynx.ui.LynxUI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Class containing methods for interpreting and executing user commands.
 */
public abstract class LynxCommandManager {

    /**
     * Attempts to create the <code>log.txt</code> data file or load its contents if it exists.
     */
    public static void reload() {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        LynxUI.line();
    }

    /**
     * Attempts to save the task list to the <code>log.txt</code> data file. Creates the file if it does not exist.
     */
    public static void save() {
        LynxFileManager.createFile();
        LynxFileManager.writeToFile(LynxStorage.unloadTasks());
        LynxUI.line();
    }

    /**
     * Creates a <code>TodoTask</code> and adds it to the task list.
     *
     * @param input User command in the form "todo [name]".
     * @return <code>TodoTask</code> that is created.
     * @throws LynxException If command or name is invalid.
     */
    public static TodoTask addTodo(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("todo");
        }
        String name = input.substring(4).trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);

        TodoTask task = new TodoTask(name);
        LynxTaskList.addTask(task, true);
        return task;
    }

    /**
     * Creates a <code>DeadlineTask</code> and adds it to the task list.
     *
     * @param input User command in the form "deadline [name] /by [date]".
     * @return <code>DeadlineTask</code> that is created.
     * @throws LynxException If command, name or date is invalid.
     */
    public static DeadlineTask addDeadline(String input) throws LynxException {
        if (input.length() <= 8) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a deadline using ' /by '.");
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);

        LocalDateTime by = LynxDateManager.parseDateTime(parts[1].trim());
        DeadlineTask task = new DeadlineTask(name, by);
        LynxTaskList.addTask(task, true);
        return task;
    }

    /**
     * Creates a <code>EventTask</code> and adds it to the task list.
     *
     * @param input User command in the form "event [name] /from [start] /to [end]".
     * @return <code>EventTask</code> that is created.
     * @throws LynxException If command, name or date is invalid.
     */
    public static EventTask addEvent(String input) throws LynxException {
        if (input.length() <= 5) {
            throw new MissingArgumentException("event");
        }
        String[] parts = input.substring(5).split(" /from ", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a start time using ' /from '.");
        }
        String[] timeSplit = parts[1].split(" /to ", 2);
        if (timeSplit.length < 2) {
            throw new LynxException("Please specify an end time using ' /to '.");
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);


        LocalDateTime from = LynxDateManager.parseDateTime(timeSplit[0].trim());
        LocalDateTime to = LynxDateManager.parseDateTime(timeSplit[1].trim());
        if (from.isAfter(to)) {
            throw new LynxException("The start date/time cannot be after the end date/time.");
        }
        EventTask task = new EventTask(name, from, to);
        LynxTaskList.addTask(task, true);
        return task;
    }

    /**
     * Print all urgent (incomplete) tasks today, if any. Uses the system clock.
     */
    public static void tasksToday() {
        LocalDateTime today = LocalDateTime.now();
        Stream<Task> tasks = LynxTaskList.filterTasksByDate(LynxTaskList.getAllTasks(), today);
        List<Task> tasks1 = LynxTaskList.filterTasksByStatus(tasks, Task.Status.INCOMPLETE).toList();

        if (tasks1.isEmpty()) {
            System.out.println("You have cleared all tasks today. Good job!");
            LynxUI.line();
            return;
        }
        System.out.println("Here are the tasks requiring completion today:");
        executeOnTasks(task -> {}, tasks1, "");
        LynxUI.line();
    }

    /**
     * Print all incomplete and unexpired tasks.
     */
    public static void tasksFromToday() {
        List<Task> tasks = LynxTaskList.filterTasksByStatus(
                LynxTaskList.getAllTasks(), Task.Status.INCOMPLETE).toList();

        if (tasks.isEmpty()) {
            System.out.println("You have no outstanding tasks. Good job!");
            LynxUI.line();
            return;
        }
        System.out.println("Reminder for your outstanding tasks:");
        executeOnTasks(task -> {}, tasks, "");
        LynxUI.line();
    }

    /**
     * Marks tasks in the task list as specified by the command.
     * <p>
     * To mark all tasks, use "mark /all".
     * <p>
     * To mark tasks by date, use "mark /on [date]".
     * <p>
     * To mark a task by id, use "mark /id [id]".
     * <p>
     * To mark tasks by status, use "mark /status [status]".
     * <p>
     * To mark tasks by keyword, use "mark [keyword]".
     *
     * @param input User command staring with "mark".
     * @throws LynxException If command, date or id is invalid.
     */
    public static void markTasks(String input) throws LynxException {
        Consumer<Task> mark = Task::setComplete;
        String empty = "     (No tasks found or marked)";

        if (input.length() <= 4 || !input.startsWith("mark")) {
            throw new MissingArgumentException("mark");
        }
        MarkCommand command = new MarkCommand(input.substring(5).trim());
        List<Task> tasks = findTasks(command);
        executeOnTasks(mark, tasks, empty);
        LynxUI.line();
    }

    /**
     * Unmarks tasks in the task list as specified by the command.
     * <p>
     * To unmark all tasks, use "unmark /all".
     * <p>
     * To unmark tasks by date, use "unmark /on [date]".
     * <p>
     * To unmark a task by id, use "unmark /id [id]".
     * <p>
     * To unmark tasks by status, use "unmark /status [status]".
     * <p>
     * To unmark tasks by keyword, use "unmark [keyword]".
     *
     * @param input User command staring with "unmark".
     * @throws LynxException If command, date or id is invalid.
     */
    public static void unmarkTasks(String input) throws LynxException {
        Consumer<Task> unmark = Task::setIncomplete;
        String empty = "     (No tasks found or unmarked)";

        if (input.length() <= 6 || !input.startsWith("unmark")) {
            throw new MissingArgumentException("unmark");
        }
        UnmarkCommand command = new UnmarkCommand(input.substring(7).trim());
        List<Task> tasks = findTasks(command);
        executeOnTasks(unmark, tasks, empty);
        LynxUI.line();
    }

    /**
     * Removes tasks from the task list as specified by the command.
     * <p>
     * To remove all tasks, use "delete /all".
     * <p>
     * To remove tasks by date, use "delete /on [date]".
     * <p>
     * To remove a task by id, use "delete /id [id]".
     * <p>
     * To remove tasks by status, use "delete /status [status]".
     * <p>
     * To remove tasks by keyword, use "delete [keyword]".
     *
     * @param input User command staring with "delete".
     * @throws LynxException If command, date or id is invalid.
     */
    public static void deleteTasks(String input) throws LynxException {
        Consumer<Task> delete = task -> LynxTaskList.removeTask(task, false);
        String empty = "     (No tasks found or deleted)";

        if (input.length() <= 6 || !input.startsWith("delete")) {
            throw new MissingArgumentException("delete");
        }
        DeleteCommand command = new DeleteCommand(input.substring(7).trim());
        List<Task> tasks = findTasks(command);
        executeOnTasks(delete, tasks, empty);
        System.out.println("You currently have " + LynxTaskList.getCount() + " task(s) in your list.");
        LynxUI.line();
    }

    /**
     * Prints tasks from the task list as specified by the command.
     * <p>
     * To print all tasks, use "list /all".
     * <p>
     * To print tasks by date, use "list /on [date]".
     * <p>
     * To print a task by id, use "list /id [id]".
     * <p>
     * To print tasks by status, use "list /status [status]".
     * <p>
     * To print tasks by keyword, use "list [keyword]".
     *
     * @param input User command staring with "list".
     * @throws LynxException If command, date or id is invalid.
     */
    public static void listTasks(String input) throws LynxException {
        Consumer<Task> list = task -> {};
        String empty = "     (No tasks yet)";

        if (input.length() <= 4 || !input.startsWith("list")) {
            throw new MissingArgumentException("list");
        }
        ListCommand command = new ListCommand(input.substring(5).trim());
        List<Task> tasks = findTasks(command);
        executeOnTasks(list, tasks, empty);
        LynxUI.line();
    }

    /**
     * Searches tasks from the task list as specified by a <code>Command</code> object.
     * <p>
     * Searches based on keyword by default.
     *
     * @param command <code>Command</code> object containing a valid command.
     * @return List of tasks fulfilling the search.
     * @throws LynxException If command is of invalid format.
     */
    private static List<Task> findTasks(LynxCommand command) throws LynxException {
        String input = command.getInput();

        if (input.startsWith("/id ")) {
            try {
                int id = Integer.parseInt(input.substring(4).trim());
                List<Task> task = new ArrayList<>();
                task.add(LynxTaskList.findTaskById(id));
                LynxUI.line();
                System.out.printf("%s%s:%n", command.getMessageById(), id);
                return task;
            } catch (NumberFormatException e) {
                throw new LynxException("Sorry, that isn't a valid ID.");
            }
        }

        Stream<Task> tasks = LynxTaskList.getAllTasks();

        if (input.trim().equals("/all")) {
            LynxUI.line();
            System.out.printf("%s:%n", command.getMessageForAll());
            return tasks.toList();
        }

        if (input.startsWith("/on ")) {
            input = input.substring(4).trim();
            try {
                LocalDateTime dateTime = LynxDateManager.parseDateTime(input);
                LynxUI.line();
                System.out.printf("%s%s:%n", command.getMessageByDate(), LynxDateManager.textDateTime(dateTime));
                return LynxTaskList.filterTasksByDate(tasks, dateTime).toList();
            } catch (LynxException e) {
                LynxUI.printBox("Invalid date format: " + e.getMessage());
            }
        }

        if (input.startsWith("/status ")) {
            input = input.substring(8).trim().toLowerCase();
            Task.Status status = Task.Status.matchSymbol(input);
            LynxUI.line();
            System.out.printf("%s%s:%n", command.getMessageByStatus(), status);
            return LynxTaskList.filterTasksByStatus(tasks, status).toList();
        }

        String keyword = input.trim();
        checkName(keyword);
        LynxUI.line();
        System.out.printf("%s\"%s\":%n", command.getMessageByKeyword(), keyword);
        return LynxTaskList.filterTasksByKeyword(tasks, keyword).toList();
    }

    /**
     * Maps a <code>Consumer</code> to a list of tasks and prints each task.
     *
     * @param consumer <code>Consumer</code> containing the action to execute on each task.
     * @param tasks List of tasks to perform action on.
     * @param empty String to be printed instead if list is empty.
     */
    private static void executeOnTasks(Consumer<Task> consumer, List<Task> tasks, String empty) {
        int count = 0;
        for (Task task : tasks) {
            count++;
            consumer.accept(task);
            System.out.println("     " + count + "." + task);
        }
        if (count == 0) {
            System.out.println(empty);
        }
    }

    // Checks that task name is within 150-character limit and does not contain the special character "/".
    private static void checkName(String name) throws LynxException {
        if (name.isBlank()) {
            throw new LynxException("Task name cannot be blank.");
        }
        if (name.contains("/")) {
            throw new LynxException("Task name cannot contain the \"/\" character.");
        }
        if (name.length() > 150) {
            throw new LynxException("Task name cannot exceed 150 characters.");
        }
    }

}
