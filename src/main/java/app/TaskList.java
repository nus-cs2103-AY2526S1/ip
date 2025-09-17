package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import errors.BoopError;
import tasks.Task;

/**
 * Represents a list of tasks with persistent storage support.
 * Provides methods to add, remove, update, and query tasks.
 */
public final class TaskList {
    private List<Task> tasks;
    private SaveHandler saveHandler;

    private List<Task> prevState;
    private String prevStateChangeCommandString;

    /**
     * Creates a new task list that uses a save handler for persistence,
     * initializing it with the given save file path.
     *
     * @param savePathName the path to the file used for saving and loading tasks
     */
    public TaskList(String savePathName) {
        tasks = new ArrayList<>();
        saveHandler = new SaveHandler(savePathName);
    }

    /**
     * Creates a new task list that uses the specified save handler for persistence.
     * This allows injecting a custom save handler (e.g., for testing).
     *
     * @param saveHandler the save handler to use for saving and loading tasks
     */
    public TaskList(SaveHandler saveHandler) {
        assert saveHandler != null : "SaveHandler must not be null";
        tasks = new ArrayList<>();
        this.saveHandler = saveHandler;
    }

    /**
     * Uses savehandler to retrieve saved tasks and converts them back to tasks
     *
     * @throws BoopError
     */
    public void loadTasks() throws BoopError {
        try {
            String[] saveStrings = saveHandler.load();
            for (String saveString : saveStrings) {
                tasks.add(Task.fromSaveString(saveString));
            }
        } catch (IOException e) {
            tasks = new ArrayList<>();
            throw new BoopError(Messages.ERROR_LOAD_SAVE_FILE);
        }
    }

    /**
     * Uses savehandler to save tasks and write them into save file
     *
     * @throws BoopError
     */
    private void saveTasks() {
        try {
            String[] saveStrings = new String[tasks.size()];
            for (int i = 0; i < tasks.size(); i++) {
                saveStrings[i] = tasks.get(i).toSaveString();
            }

            saveHandler.save(saveStrings);
        } catch (IOException e) {
            throw new BoopError(Messages.ERROR_SAVE_SAVE_FILE);
        }
    }

    /**
     * Adds a new task to the task list
     *
     * @param newTask Task to be added
     */
    public void addToList(Task newTask) {
        assert newTask != null : "Cannot add null task";
        storePrevState();
        tasks.add(newTask);
        saveTasks();
    }

    /**
     * Deletes a task at the given index of the task list
     *
     * @param index Starts from 1
     * @return Task that was deleted
     */
    public Task deleteTask(int index) {
        assert isValidIndex(index) : "Index out of bounds for deleteTask: " + index;
        storePrevState();
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        saveTasks();
        return task;
    }

    /**
     * Returns the length of the list
     *
     * @return Integer length of list
     */
    public int getTaskslistLength() {
        return tasks.size();
    }

    /**
     * Returns the String representation of the Task list
     * Only includes tasks that fit the filter regex
     *
     * @param regex Regex used to filter tasks
     * @return String representation of the Task list
     */
    public String filterDisplay(String regex) {
        assert regex != null : "Regex must not be null";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            String taskStr = tasks.get(i - 1).toString();
            if (pattern.matcher(taskStr).find()) {
                sb.append("%d.\t".formatted(i))
                        .append(taskStr)
                        .append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Returns the String representation of the Task list
     *
     * @return String representation of the Task list
     */
    public String display() {
        return filterDisplay("");
    }

    /**
     * Marks a task at the given index of the task list
     *
     * @param index Starts from 1
     * @return Task that was deleted
     */
    public Task mark(int index) {
        assert isValidIndex(index) : "Index out of bounds for deleteTask: " + index;
        storePrevState();
        Task task = tasks.get(index - 1);
        task.complete();
        saveTasks();
        return task;
    }

    /**
     * Unmarks a task at the given index of the task list
     *
     * @param index Starts from 1
     * @return Task that was deleted
     */
    public Task unmark(int index) {
        assert isValidIndex(index) : "Index out of bounds for deleteTask: " + index;
        storePrevState();
        Task task = tasks.get(index - 1);
        task.uncomplete();
        saveTasks();
        return task;
    }

    /**
     * Checks if a given index is valid
     *
     * @param index Starts from 1
     * @return Whether the index is valid
     */
    public boolean isValidIndex(int index) {
        return index <= tasks.size() && index >= 1;
    }

    /**
     * Stores the command string that changed the task state
     * Used for undo functionality
     *
     * @param command the command string of the command used to change the state
     */
    public void setStateChangeCommmandString(String command) {
        prevStateChangeCommandString = command;
    }

    private void storePrevState() {
        prevState = new ArrayList<>();

        for (Task task : tasks) {
            prevState.add(task.copy());
        }
    }

    /**
     * Restores the previous state of the task list.
     * Used for undo functionality.
     *
     * @return Command String that previously changed state that was just undo
     * @throws BoopError if there is no previous state to restore
     */
    public String undo() {
        if (prevState == null) {
            throw new BoopError("Nothing to undo!");
        }

        assert prevStateChangeCommandString != null && !prevStateChangeCommandString.isEmpty()
                : "Prev state change command string should be non empty";

        tasks = prevState;
        saveTasks();

        prevState = null;
        String tmp = prevStateChangeCommandString;
        prevStateChangeCommandString = null;

        return tmp;
    }
}
