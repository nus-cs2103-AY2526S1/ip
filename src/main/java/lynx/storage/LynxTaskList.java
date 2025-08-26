package lynx.storage;

import lynx.exception.LynxException;
import lynx.formatter.LynxDateManager;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.ui.LynxUI;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class containing methods that directly access the task list.
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
            LynxUI.printBox("Removed all tasks." +
                    "\nNow you have " + getCount() + " task(s) in your list.");
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
            LynxUI.printBox("Added:\n     " + task +
                    "\nYou currently have " + getCount() + " task(s) in your list.");
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
            LynxUI.printBox("Removed:\n     " + task +
                    "\nYou currently have " + getCount() + " task(s) in your list.");
        }
    }

    /**
     * Returns all tasks in the task list with a given keyword in its name.
     *
     * @param keyword Keyword used to search tasks by name.
     * @return List of tasks with names fulfilling the search.
     */
    public static List<Task> findTasksContaining(String keyword) {
        return filterTasksByKeyword(COMMANDS.stream(), keyword).toList();
    }

    /**
     * Returns the task in the task list with the given id.
     *
     * @param id Id of task to be retrieved.
     * @return Task with matching id.
     * @throws LynxException If no matching task is found.
     */
    public static Task findTaskById(int id) throws LynxException {
        for (Task task : COMMANDS) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new LynxException("Task not found.");
    }

    /**
     * Returns all tasks in the task list that are active on a given date.
     *
     * @param dateTime <code>LocalDateTime</code> object to search tasks by date.
     * @return List of tasks occurring on the given date.
     */
    public static List<Task> findTasksOnDate(LocalDateTime dateTime) {
        return filterTasksByDate(COMMANDS.stream(), dateTime).toList();
    }

    /**
     * Returns all tasks in a stream with a given keyword in its name.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param keyword Keyword used to filter tasks by name.
     * @return Task stream filtered by keyword.
     */
    public static Stream<Task> filterTasksByKeyword(Stream<Task> tasks, String keyword) {
        return tasks.filter(task -> task.getName().toLowerCase().contains(keyword.toLowerCase()));
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
     * Returns all tasks in a stream that match a given status.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param status <code>Status</code> used to filter tasks.
     * @return Task stream filtered by status.
     */
    public static Stream<Task> filterTasksByStatus(Stream<Task> tasks, Task.Status status) {
        return tasks.filter(task -> task.getStatus().equals(status));
    }

}
