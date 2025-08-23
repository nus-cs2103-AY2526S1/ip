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
     * @return True if task list is not empty.
     */
    public static boolean printTasks() {
        LynxUI.line();
        System.out.println("Here are the tasks in your list:");

        boolean found = !COMMANDS.isEmpty();
        if (found) {
            for (int i = 0; i < COMMANDS.size(); i++) {
                System.out.println("     " + (i+1) + "." + COMMANDS.get(i));
            }
        } else {
            System.out.println("     (No tasks yet)");
        }

        LynxUI.line();
        return found;
    }

    /**
     * Prints all tasks in the task list that are active on a given date.
     *
     * @param target LocalDateTime object representing the date.
     * @return True if one or more tasks match the date.
     */
    public static boolean printTasksOnDate(LocalDateTime target) {
        LynxUI.line();
        System.out.println("Tasks occurring on " + LynxDateManager.textDateTime(target) + ":");

        boolean found = false;
        for (int i = 0; i < COMMANDS.size(); i++) {
            Task task = COMMANDS.get(i);

            if (task instanceof DeadlineTask deadlineTask) {
                // compare only date part
                if (isSameDay(deadlineTask.getDeadline(), target)) {
                    System.out.println("     " + (i+1) + "." + task);
                    found = true;
                }
            }

            if (task instanceof EventTask eventTask) {
                // target date within event range
                if (!target.isBefore(eventTask.getStart()) && !target.isAfter(eventTask.getEnd())) {
                    System.out.println("     " + (i+1) + "." + task);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("     (No tasks for this date)");
        }
        LynxUI.line();
        return found;
    }

    // Helper: checks if two LocalDateTimes are on the same day
    private static boolean isSameDay(LocalDateTime a, LocalDateTime b) {
        return a.toLocalDate().equals(b.toLocalDate());
    }

}
