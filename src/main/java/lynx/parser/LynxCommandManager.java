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
     * Marks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "mark".
     * @throws LynxException If command is invalid.
     */
    public static void markTasks(String input) throws LynxException {
        Consumer<Task> mark = Task::setComplete;
        String empty = "     (No tasks found or marked)";

        if (input.length() <= 4 || !input.startsWith("mark")) {
            throw new MissingArgumentException("mark");
        }
        LynxCommand2 command = new LynxCommand2(input.substring(5).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Marked %s", command.getSearchString()));
        executeOnTasks(mark, command.getSearchResult(), empty);
        LynxUI.line();
    }

    /**
     * Unmarks tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "unmark".
     * @throws LynxException If command is invalid.
     */
    public static void unmarkTasks(String input) throws LynxException {
        Consumer<Task> unmark = Task::setIncomplete;
        String empty = "     (No tasks found or unmarked)";

        if (input.length() <= 6 || !input.startsWith("unmark")) {
            throw new MissingArgumentException("unmark");
        }
        LynxCommand2 command = new LynxCommand2(input.substring(7).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Unmarked %s", command.getSearchString()));
        executeOnTasks(unmark, command.getSearchResult(), empty);
        LynxUI.line();
    }

    /**
     * Removes tasks from the task list as specified by the input command.
     *
     * @param input Command staring with "delete".
     * @throws LynxException If command is invalid.
     */
    public static void deleteTasks(String input) throws LynxException {
        Consumer<Task> delete = task -> LynxTaskList.removeTask(task, false);
        String empty = "     (No tasks found or deleted)";

        if (input.length() <= 6 || !input.startsWith("delete")) {
            throw new MissingArgumentException("delete");
        }
        LynxCommand2 command = new LynxCommand2(input.substring(7).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Deleted %s", command.getSearchString()));
        executeOnTasks(delete, command.getSearchResult(), empty);
        System.out.println("You currently have " + LynxTaskList.getCount() + " task(s) in your list.");
        LynxUI.line();
    }

    /**
     * Prints tasks in the task list as specified by the input command.
     *
     * @param input Command staring with "list".
     * @throws LynxException If command is invalid.
     */
    public static void listTasks(String input) throws LynxException {
        Consumer<Task> list = task -> {};
        String empty = "     (No tasks yet)";

        if (input.length() <= 4 || !input.startsWith("list")) {
            throw new MissingArgumentException("list");
        }
        LynxCommand2 command = new LynxCommand2(input.substring(5).trim());
        findTasks(command, LynxTaskList.getAllTasks());
        LynxUI.line();
        System.out.println(String.format("Here are %s", command.getSearchString()));
        executeOnTasks(list, command.getSearchResult(), empty);
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
    public static void findTasks(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String input = command.getNextCommand();

        if (input.isBlank()) {
            command.setSearchResult(stream.toList());
            return;
        }

        switch (input) {
            case "/all" -> findTasks(command, stream);
            case "/id" -> findTasksById(command, stream);
            case "/key" -> findTasksByKeyword(command, stream);
            case "/on" -> findTasksByDate(command, stream);
            case "/sts" -> findTasksByStatus(command, stream);
            case "/type" -> findTasksByType(command, stream);
            default -> throw new LynxException("Non matching command detected.");
        }

    }

    private static void findTasksById(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        try {
            String id = command.getNextCommand();
            command.setId(id);
            findTasks(command, LynxTaskList.filterTasksById(stream, Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            throw new LynxException("Sorry, that isn't a valid ID.");
        }
    }

    private static void findTasksByKeyword(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String keyword = command.getNextCommand();
        checkName(keyword);
        command.setKeyword(keyword);
        findTasks(command, LynxTaskList.filterTasksByKeyword(stream, keyword));
    }

    private static void findTasksByDate(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String date = command.getNextCommand();
        LocalDateTime dateTime = LynxDateManager.parseDateTime(date);
        command.setDate(LynxDateManager.textDateTime(dateTime));
        findTasks(command, LynxTaskList.filterTasksByDate(stream, dateTime));
    }

    private static void findTasksByStatus(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String symbol = command.getNextCommand();
        Task.Status status = Task.Status.matchSymbol(symbol);
        command.setStatus(symbol);
        findTasks(command, LynxTaskList.filterTasksByStatus(stream, status));
    }

    private static void findTasksByType(LynxCommand2 command, Stream<Task> stream) throws LynxException {
        String symbol = command.getNextCommand();
        Task.TaskType type = Task.TaskType.matchSymbol(symbol);
        command.setType(symbol);
        findTasks(command, LynxTaskList.filterTasksByType(stream, type));
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
