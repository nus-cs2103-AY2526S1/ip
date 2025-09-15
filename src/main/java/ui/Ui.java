package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import misc.TaskBotException;
import task.Deadline;
import task.Event;
import task.Task;

/**
 * Deals with interactions with user
 */

public class Ui {

    /**
     * Parses the input command to obtain the required task index i
     *
     * @param command input command provided by the user
     * @param size current size of the task list
     * @return task index
     * @throws TaskBotException
     */

    public static int read(String command, int size) throws TaskBotException {
        try {
            int i = Integer.parseInt(command.trim()) - 1;
            if (i < 0 || i >= size) {
                throw new TaskBotException("OOPS!! Task at index i not found.");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new TaskBotException("Task index i needs to be an integer.");
        }
    }

    /**
     * Notifies user of task added to list of tasks
     * @param t task added
     * @param size size of the task list after adding new task
     */

    public String printAddedTask(Task t, int size) {
        return "Okay! Task added:\n" + "  " + t + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints an enumerated list of all current tasks
     * @param tasks accumulated list of tasks
     */

    public static String printList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks yet!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("  ")
                    .append(i + 1)
                    .append(": ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Notifies user of marking/unmarking task
     * @param t task whose status is being modified
     * @param isMarked boolean representing task status
     */

    public String printMark(Task t, boolean isMarked) {
        String msg;
        if (isMarked) {
            msg = "Nice! I've marked this task as done: ";
        } else {
            msg = "OK, I've marked this task as not done yet: ";
        }
        return msg + t;
    }

    /**
     * Notifies user of task removed from task list
     * @param tasks task list
     * @param t task being deleted
     */
    public static String printDeletedTask(List<Task> tasks, Task t) {
        return ("Noted. I've removed this task: ") + t.toString()
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints all Deadline and Event objects relevant to specified date
     * @param tasks accumulated list of tasks
     * @param date date of interest
     */

    public String printDateTasks(List<Task> tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks on ")
                .append(date)
                .append(":\n");
        boolean tasksFound = false;

        for (Task t : tasks) {
            if (t instanceof Deadline d && d.getBy().equals(date)) {
                sb.append("  ")
                        .append(t)
                        .append("\n");
                tasksFound = true;
            } else if (t instanceof Event e) {
                LocalDate startDate = e.getStart();
                LocalDate endDate = e.getEnd();

                if ((date.isEqual(startDate) || date.isAfter(startDate))
                        && (date.isEqual(endDate) || date.isBefore(endDate))) {
                    sb.append("  ")
                            .append(t)
                            .append("\n");
                    tasksFound = true;
                }
            }
        }
        if (!tasksFound) {
            return "No tasks found on " + date;
        }
        return sb.toString();
    }

    /**
     * Prints all tasks with names containing the specified keyword
     * @param tasks accumulated list of tasks
     * @param taskName keyword used to search in tasks
     * @return String representing the list of matching tasks
     */
    public String printFind(ArrayList<Task> tasks, String taskName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Looking for task including ")
                .append(taskName)
                .append("...")
                .append("\n");
        boolean taskFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDesc().toLowerCase().contains(taskName.toLowerCase())) {
                sb.append("  ")
                        .append(i + 1)
                        .append(". ")
                        .append(t)
                        .append("\n");
                taskFound = true;
            }
        }

        if (!taskFound) {
            return "Task including " + taskName + "not found.";
        }
        return sb.toString();
    }

    /**
     * TaskBot.TaskBot welcome
     */
    public static String showWelcome() {
        return "Hello! I'm TaskBot.\n"
                + "What can I do for you?";
    }

    /**
     * TaskBot.TaskBot goodbye
     */
    public String showGoodbye() {
        return "Bye! See you later!";
    }

    public static String showError(String msg) {
        return "OOPS!" + msg;
    }

    public String showUpdate(Task t) {
        return "Okay! I've updated this task: " + t;
    }
}
