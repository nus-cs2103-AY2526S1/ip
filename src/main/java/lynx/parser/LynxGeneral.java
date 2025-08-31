package lynx.parser;

import java.time.LocalDateTime;

import lynx.storage.LynxFileManager;
import lynx.storage.LynxStorage;
import lynx.storage.LynxTaskList;
import lynx.ui.LynxUI;
import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.formatter.LynxDateManager;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.TodoTask;

/**
 * Contains methods to execute general commands.
 */
public abstract class LynxGeneral {

    /**
     * Attempts to create the <code>log.txt</code> data file or load its contents if it exists.
     */
    public static void reload() {
        LynxUI.line();
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        System.out.println("Load successful!");
        LynxUI.line();
    }

    /**
     * Attempts to save the task list to the <code>log.txt</code> data file. Creates the file if it does not exist.
     */
    public static void save() {
        LynxUI.line();
        LynxFileManager.createFile();
        LynxFileManager.writeToFile(LynxStorage.unloadTasks());
        System.out.println("Save successful!");
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
     * Checks that a task name is not blank, does not contain the "/" character, and does not exceed 150 characters.
     *
     * @param name Name of the task to be checked.
     * @throws LynxException If task name is invalid.
     */
    public static void checkName(String name) throws LynxException {
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
