package lynx.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Stream;

import lynx.ui.LynxUI;
import objectclasses.task.Task;

/**
 * Contains methods that directly access the task list.
 */
public abstract class LynxTaskList {

    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return Number of tasks.
     */
    public static int getCount() {
        return COMMANDS.size();
    }

    /**
     * Returns the task list as a stream.
     *
     * @return Stream of tasks.
     */
    public static Stream<Task> getAllTasks() {
        return COMMANDS.stream();
    }

    /**
     * Clears the task list of all tasks.
     *
     * @param dialogue Option to print a dialogue.
     */
    public static void clearTasks(boolean dialogue) {
        COMMANDS.clear();
        if (dialogue) {
            LynxUI.printBox("Removed all tasks."
                    + "\nNow you have " + getCount() + " task(s) in your list.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @param dialogue Option to print a dialogue.
     */
    public static void addTask(Task task, boolean dialogue) {
        COMMANDS.add(task);
        if (dialogue) {
            LynxUI.printBox("Added:\n     " + task
                    + "\nYou currently have " + getCount() + " task(s) in your list.");
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param task Task to be removed.
     * @param dialogue Option to print a dialogue.
     */
    public static void removeTask(Task task, boolean dialogue) {
        COMMANDS.remove(task);
        if (dialogue) {
            LynxUI.printBox("Removed:\n     " + task
                    + "\nYou currently have " + getCount() + " task(s) in your list.");
        }
    }

    /**
     * Returns the task with a given id.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param id Id of task to be retrieved.
     * @return Stream containing a single task.
     */
    public static Stream<Task> filterTasksById(Stream<Task> tasks, int id) {
        return tasks.filter(task -> task.getId() == id);
    }

    /**
     * Returns all tasks in a stream with a given keyword in its name.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param keyword Keyword used to filter tasks by name.
     * @return Task stream filtered by keyword.
     */
    public static Stream<Task> filterTasksByKeyword(Stream<Task> tasks, String keyword) {
        return tasks.filter(task -> task.getName().replaceAll("\\s+", "")
                .toLowerCase().contains(keyword.toLowerCase()));
    }

    /**
     * Returns all tasks in a stream that are active on a given date.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param dateTime <code>LocalDateTime</code> object to filter tasks by date.
     * @return Task stream filtered by date.
     */
    public static Stream<Task> filterTasksByDate(Stream<Task> tasks, LocalDateTime dateTime) {
        return tasks.filter(task -> task.isActive(dateTime));
    }

    /**
     * Returns all tasks in a stream that match a given <code>Status</code>.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param status <code>Status</code> used to filter tasks.
     * @return Task stream filtered by status.
     */
    public static Stream<Task> filterTasksByStatus(Stream<Task> tasks, Task.Status status) {
        return tasks.filter(task -> task.getStatus().equals(status));
    }

    /**
     * Returns all tasks in a stream that match a given <code>TaskType</code>.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param type <code>TaskType</code> used to filter tasks.
     * @return Task stream filtered by type.
     */
    public static Stream<Task> filterTasksByType(Stream<Task> tasks, Task.TaskType type) {
        return tasks.filter(task -> task.getType().equals(type));
    }

}
