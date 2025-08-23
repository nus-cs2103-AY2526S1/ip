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

// All methods directly interacting with the lynx.task.Task List
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
                    "\nNow you have " + getCount() + " task(s) in your list.");
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
                    "\nNow you have " + getCount() + " task(s) in your list.");
        }
    }

    /**
     * Searches for a task in the task list using its id.
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
     * Searches for a task in the task list using its position.
     *
     * @param position Position of task in the task list, starting from 1.
     * @return Task in matching position.
     * @throws LynxException If position < 1 or > LynxTaskList.getCount().
     */
    public static Task findTaskByPosition(int position) throws LynxException {
        if (position < 1 || position > COMMANDS.size()) {
            throw new LynxException("Sorry, no task at that position.");
        }
        return COMMANDS.get(position - 1);
    }

    /**
     * Prints all tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public static int printTasks() {
        LynxUI.line();
        System.out.println("Here are the tasks in your list:");

        int count = 0;
        for (Task task : COMMANDS) {
            count++;
            System.out.println("     " + count + "." + task);
        }

        if (count == 0){
            System.out.println("     (No tasks yet)");
        }
        LynxUI.line();
        return count;
    }

    /**
     * Prints all tasks in the task list with a given word in its name.
     *
     * @param keyword Substring used to search tasks by name.
     * @return Number of tasks with names fulfilling the search.
     */
    public static int printTasksContaining(String keyword) {
        LynxUI.line();
        System.out.println("Tasks containing \"" + keyword + "\":");

        int count = 0;
        for (Task task : COMMANDS) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                System.out.println("     " + count + "." + task);
            }
        }

        if (count == 0) {
            System.out.println("     (No tasks found with this substring)");
        }
        LynxUI.line();
        return count;
    }

    /**
     * Prints all tasks in the task list that are active on a given date.
     *
     * @param target LocalDateTime object to search tasks by date.
     * @return Number of tasks occurring on the given date.
     */
    public static int printTasksOnDate(LocalDateTime target) {
        LynxUI.line();
        System.out.println("Tasks occurring on " + LynxDateManager.textDateTime(target) + ":");

        int count = 0;
        for (Task task : COMMANDS) {
            if (task instanceof DeadlineTask deadlineTask) {
                // compare only date part
                if (isSameDay(deadlineTask.getDeadline(), target)) {
                    count++;
                    System.out.println("     " + count + "." + task);
                }
            }

            if (task instanceof EventTask eventTask) {
                // target date within event range
                if (!target.isBefore(eventTask.getStart()) && !target.isAfter(eventTask.getEnd())) {
                    count++;
                    System.out.println("     " + count + "." + task);
                }
            }
        }

        if (count == 0) {
            System.out.println("     (No tasks for this date)");
        }
        LynxUI.line();
        return count;
    }

    /**
     * Prints the task in the task list with a given id.
     *
     * @param id Id of task to be printed.
     * @return Number of tasks with the task id. Either 1 or 0.
     */
    public static int printTaskById(int id) {
        LynxUI.line();
        System.out.println("Task with id " + id + ":");

        try {
            Task task = findTaskById(id);
            System.out.println("     " + task);
            LynxUI.line();
            return 1;
        } catch (LynxException e) {
            System.out.println("     (No task with this id)");
            LynxUI.line();
            return 0;
        }
    }

    // Helper: checks if two LocalDateTimes are on the same day
    private static boolean isSameDay(LocalDateTime a, LocalDateTime b) {
        return a.toLocalDate().equals(b.toLocalDate());
    }

}
