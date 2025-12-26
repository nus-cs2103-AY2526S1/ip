package gene;

import gene.exceptions.TaskOutOfRangeException;
import gene.tasks.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private final Storage storage;
    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        ArrayList<Task> tasks1;
        this.storage = storage;
        try {
            tasks1 = this.storage.loadTasksFromFile();
        } catch (IOException e) {
            tasks1 = new ArrayList<>();
        }
        tasks = tasks1;
    }

    /**
     * Adds the task in the Task List and updates the storage
     *
     * @param task Task to add to the task list.
     * @return The confirmation message to be printed out to user
     * upon completion
     */
    public String addTask(Task task) {
        tasks.add(task);
        this.storage.saveTasksToFile(this.tasks);
        return String.format("%sGot it. I've added this task:\n%s  " +
                        " %s\n%sNow you have %d tasks in the list.",
                Ui.SPACING, Ui.SPACING, task.toString(), Ui.SPACING, tasks.size());
    }

    /**
     * Marks the task in the Task List and updates the storage
     *
     * @param i 1-based indexing of task to mark
     * @return The confirmation message to be printed out to user
     * upon completion
     */
    public String markTask(int i) {
        int idx = i - 1;
        tasks.get(idx).mark();
        this.storage.saveTasksToFile(this.tasks);
        return String.format("Nice! I've marked this task as done:\n%s%s", Ui.SPACING, tasks.get(idx).toString());
    }

    /**
     * Unmarks the task in the Task List and updates the storage
     *
     * @param i 1-based indexing of task to unmark
     * @return The confirmation message to be printed out to user
     * upon completion
     */
    public String unmarkTask(int i) {
        int idx = i - 1;
        tasks.get(idx).unmark();
        this.storage.saveTasksToFile(this.tasks);
        return String.format("OK, I've marked this task as not done yet:\n%s%s",
                Ui.SPACING, tasks.get(idx).toString());
    }

    /**
     * Deletes the task in the Task List and updates the storage
     *
     * @param i 1-based indexing of task to delete
     * @return The confirmation message to be printed out to user upon
     * completion
     * @throws TaskOutOfRangeException if the given index is less than 1
     *                                 or greater than number of tasks
     */
    public String deleteTask(int i) throws TaskOutOfRangeException {
        int idx = i - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskOutOfRangeException();
        }
        Task removed = tasks.remove(idx);
        assert removed != null;
        this.storage.saveTasksToFile(this.tasks);
        return String.format("%sNoted. I've removed this task:\n%s  " +
                        " %s\n%sNow you have %d tasks in the list.",
                Ui.SPACING, Ui.SPACING, removed.toString(), Ui.SPACING, tasks.size());
    }

    public ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.containsKeyword(keyword)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    public String getReminderTasks(LocalDateTime cutOffDate) {
        StringBuilder res = new StringBuilder();
        ArrayList<Task> reminderTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.isReminderNeeded(cutOffDate)) {
                reminderTasks.add(task);
            }
        }
        if (reminderTasks.isEmpty()) {
            return Ui.SPACING + "You have no tasks that need reminders by this date.";
        }
        res.append(Ui.SPACING).append("Here are the tasks that need reminders by this date:\n");
        return getStringFromTasks(res, reminderTasks);
    }

    /**
     * Loops through the task list array and return formatted string
     * consisting of all tasks. This allows user to list all tasks.
     *
     * @param res   StringBuilder to append the result to
     * @param tasks ArrayList of tasks to be listed
     * @return String which is a formatted representation of all tasks
     */
    public static String getStringFromTasks(StringBuilder res, ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            assert !tasks.get(i).toString().isEmpty();
            res.append(String.format("%s %d. %s",
                    Ui.SPACING, i + 1, tasks.get(i).toString()));
            if (i != tasks.size() - 1) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    /**
     * Formats the TaskList for printing when user calls List command
     *
     * @return String which is a formatted representation of all tasks
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return Ui.SPACING + "You have no tasks in your list.";
        }
        StringBuilder res = new StringBuilder();
        res.append(Ui.SPACING).append("Here are the tasks in your list:\n");
        return getStringFromTasks(res, tasks);
    }

}
