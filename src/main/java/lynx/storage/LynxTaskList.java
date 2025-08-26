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
     * Returns a copy of the task list.
     *
     * @return Copy of task list.
     */
    public static List<Task> getAllTasks() {
        return new ArrayList<>(COMMANDS);
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
        List<Task> tasks = new ArrayList<>();
        for (Task task : COMMANDS) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                tasks.add(task);
            }
        }
        return tasks;
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
     * @param target <code>LocalDateTime</code> object to search tasks by date.
     * @return List of tasks occurring on the given date.
     */
    public static List<Task> findTasksOnDate(LocalDateTime target) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : COMMANDS) {
            if (task instanceof DeadlineTask deadlineTask) {
                // compare only date part
                if (isSameDay(deadlineTask.getDeadline(), target)) {
                    tasks.add(task);
                }
            }

            if (task instanceof EventTask eventTask) {
                // target date within event range
                if (!target.isBefore(eventTask.getStart()) && !target.isAfter(eventTask.getEnd())) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    // Helper: checks if two LocalDateTimes are on the same day
    private static boolean isSameDay(LocalDateTime a, LocalDateTime b) {
        return a.toLocalDate().equals(b.toLocalDate());
    }

}
