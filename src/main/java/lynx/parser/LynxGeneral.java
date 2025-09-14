package lynx.parser;

import lynx.storage.LynxFileManager;
import lynx.storage.LynxStorage;
import lynx.storage.LynxTaskList;
import objectclasses.exception.LynxException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.TodoTask;

/**
 * Contains methods for executing general commands.
 */
public abstract class LynxGeneral {

    /**
     * Attempts to create the <code>log.txt</code> data file or load its contents if it exists.
     *
     * @param fileManager <code>LynxFileManager</code> with the filepath to load from.
     * @param taskList Task list to load to.
     * @return Message representing reload status.
     * @throws LynxException If reload is unsuccessful.
     */
    public static String reload(LynxFileManager fileManager, LynxTaskList taskList) throws LynxException {
        fileManager.createFile();
        LynxStorage.loadTasks(fileManager.readFromFile(), taskList);
        return "Caught all your tasks!";
    }

    /**
     * Attempts to save the task list to the <code>log.txt</code> data file. Creates the file if it does not exist.
     *
     * @param fileManager <code>LynxFileManager</code> with the filepath to save to.
     * @param taskList Task list to load from.
     * @return Message representing save status.
     * @throws LynxException If save is unsuccessful.
     */
    public static String save(LynxFileManager fileManager, LynxTaskList taskList) throws LynxException {
        fileManager.createFile();
        fileManager.writeToFile(LynxStorage.unloadTasks(taskList));
        return "Kept all your tasks!";
    }

    /**
     * Creates a <code>TodoTask</code> and adds it to a task list.
     *
     * @param input User command in the form "todo [name]".
     * @param taskList Task list to add task to.
     * @return String to indicate the task added.
     * @throws LynxException If command or name is invalid.
     */
    public static String addTodo(String input, LynxTaskList taskList) throws LynxException {
        return taskList.addTask(TodoTask.of(input));
    }

    /**
     * Creates a <code>DeadlineTask</code> and adds it to a task list.
     *
     * @param input User command in the form "deadline [name] /by [date]".
     * @param taskList Task list to add task to.
     * @return String to indicate the task added.
     * @throws LynxException If command, name or date is invalid.
     */
    public static String addDeadline(String input, LynxTaskList taskList) throws LynxException {
        return taskList.addTask(DeadlineTask.of(input));
    }

    /**
     * Creates a <code>EventTask</code> and adds it to a task list.
     *
     * @param input User command in the form "event [name] /from [start] /to [end]".
     * @param taskList Task list to add task to.
     * @return String to indicate the task added.
     * @throws LynxException If command, name or date is invalid.
     */
    public static String addEvent(String input, LynxTaskList taskList) throws LynxException {
        return taskList.addTask(EventTask.of(input));
    }

}
