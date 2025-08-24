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

// Parses command details and executes them
public abstract class LynxCommandManager {

    /**
     * Attempts to create the log.txt data file or load its contents if it exists.
     */
    public static void reload() {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        LynxUI.line();
    }

    /**
     * Creates a TodoTask and adds it to the task list.
     *
     * @param input User command in the form "todo [name]".
     * @return TodoTask that is created.
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
     * Creates a DeadlineTask and adds it to the task list.
     *
     * @param input User command in the form "deadline [name] /by [date]".
     * @return DeadlineTask that is created.
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
     * Creates a EventTask and adds it to the task list.
     *
     * @param input User command in the form "event [name] /from [start] /to [end]".
     * @return EventTask that is created.
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

    public static void markTasks(String input) throws LynxException {
        Consumer<Task> mark = Task::setComplete;
        String empty = "     (No tasks found or marked)";

        if (input.length() <= 4 || !input.startsWith("mark")) {
            throw new MissingArgumentException("mark");
        }
        MarkCommand command = new MarkCommand(input.substring(5).trim());
        List<Task> tasks = findTasks(command);
        mapTasks(mark, tasks, empty);
    }

    public static void unmarkTasks(String input) throws LynxException {
        Consumer<Task> unmark = Task::setIncomplete;
        String empty = "     (No tasks found or unmarked)";

        if (input.length() <= 6 || !input.startsWith("unmark")) {
            throw new MissingArgumentException("unmark");
        }
        UnmarkCommand command = new UnmarkCommand(input.substring(7).trim());
        List<Task> tasks = findTasks(command);
        mapTasks(unmark, tasks, empty);
    }

    public static void deleteTasks(String input) throws LynxException {
        Consumer<Task> delete = task -> LynxTaskList.removeTask(task, false);
        String empty = "     (No tasks found or deleted)";

        if (input.length() <= 6 || !input.startsWith("delete")) {
            throw new MissingArgumentException("delete");
        }
        DeleteCommand command = new DeleteCommand(input.substring(7).trim());
        List<Task> tasks = findTasks(command);
        mapTasks(delete, tasks, empty);
        System.out.println("You currently have " + LynxTaskList.getCount() + " task(s) in your list.");
    }

    public static void listTasks(String input) throws LynxException {
        Consumer<Task> list = task -> {};
        String empty = "     (No tasks yet)";

        if (input.length() <= 4 || !input.startsWith("list")) {
            throw new MissingArgumentException("list");
        }
        ListCommand command = new ListCommand(input.substring(5).trim());
        List<Task> tasks = findTasks(command);
        mapTasks(list, tasks, empty);
    }

    private static List<Task> findTasks(LynxCommand command) throws LynxException {
        String input = command.getInput();
        if (input.trim().equals("/all")) {
            LynxUI.line();
            System.out.printf("%s:%n", command.getMessageForAll());
            return LynxTaskList.getAllTasks();
        }

        if (input.startsWith("/on ")) {
            input = input.substring(4).trim();
            try {
                LocalDateTime dateTime = LynxDateManager.parseDateTime(input);
                LynxUI.line();
                System.out.printf("%s%s:%n", command.getMessageByDate(), LynxDateManager.textDateTime(dateTime));
                return LynxTaskList.findTasksOnDate(dateTime);
            } catch (LynxException e) {
                LynxUI.printBox("Invalid date format: " + e.getMessage());
            }
        }

        if (input.startsWith("/id ")) {
            try {
                int id = Integer.parseInt(input.substring(3).trim());
                List<Task> task = new ArrayList<>();
                task.add(LynxTaskList.findTaskById(id));
                LynxUI.line();
                System.out.printf("%s%s:%n", command.getMessageById(), id);
                return task;
            } catch (NumberFormatException e) {
                throw new LynxException("Sorry, that isn't a valid ID.");
            }
        }

        String keyword = input.trim();
        LynxUI.line();
        System.out.printf("%s\"%s\":%n", command.getMessageByKeyword(), keyword);
        return LynxTaskList.findTasksContaining(keyword);
    }

    private static void mapTasks(Consumer<Task> consumer, List<Task> tasks, String empty) {
        int count = 0;
        for (Task task : tasks) {
            count++;
            consumer.accept(task);
            System.out.println("     " + count + "." + task);
        }
        if (count == 0) {
            System.out.println(empty);
        }
        LynxUI.line();
    }

    private static void checkName(String name) throws LynxException {
        if (name.contains("/")) {
            throw new LynxException("Task name cannot contain the \"/\" character.");
        } else if (name.length() > 150) {
            throw new LynxException("Task name cannot exceed 150 characters.");
        }
    }

}
