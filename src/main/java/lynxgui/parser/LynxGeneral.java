package lynxgui.parser;

import lynxgui.storage.LynxFileManager;
import lynxgui.storage.LynxStorage;
import lynxgui.storage.LynxTaskList;

import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.formatter.LynxDateManager;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.TodoTask;

import java.time.LocalDateTime;

/**
 * Contains methods to execute general commands.
 */
public abstract class LynxGeneral {

    /**
     * Attempts to create the <code>log.txt</code> data file or load its contents if it exists.
     */
    public static String reloadGui() throws LynxException {
        LynxFileManager.createFile();
        LynxStorage.loadTasks(LynxFileManager.readFromFile());
        return  "Load successful!";
    }

    /**
     * Attempts to save the task list to the <code>log.txt</code> data file. Creates the file if it does not exist.
     */
    public static String saveGui() throws LynxException {
        LynxFileManager.createFile();
        LynxFileManager.writeToFile(LynxStorage.unloadTasks());
        return "Save successful!";
    }

    /**
     * Creates a <code>TodoTask</code> and adds it to the task list.
     *
     * @param input User command in the form "todo [name]".
     * @return String to indicate the task added.
     * @throws LynxException If command or name is invalid.
     */
    public static String addTodoGui(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("todo");
        }
        String name = input.substring(4).trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }
        checkName(name);

        TodoTask task = new TodoTask(name);
        return LynxTaskList.addTaskGui(task);
    }

    /**
     * Creates a <code>DeadlineTask</code> and adds it to the task list.
     *
     * @param input User command in the form "deadline [name] /by [date]".
     * @return String to indicate the task added.
     * @throws LynxException If command, name or date is invalid.
     */
    public static String addDeadlineGui(String input) throws LynxException {
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
        return LynxTaskList.addTaskGui(task);
    }

    /**
     * Creates a <code>EventTask</code> and adds it to the task list.
     *
     * @param input User command in the form "event [name] /from [start] /to [end]".
     * @return String to indicate the task added.
     * @throws LynxException If command, name or date is invalid.
     */
    public static String addEventGui(String input) throws LynxException {
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
        return LynxTaskList.addTaskGui(task);
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
