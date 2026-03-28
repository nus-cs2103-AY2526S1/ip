package bugsbunny.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A mutable collection of tasks with helpers for common operations.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task Task to be added.
     * @return String to inform the user that the task has been added successfully.
     */
    public String addTask(Task task) {
        this.list.add(task);
        return String.format(
                "OK Doc, I've added this task:\n"
                        + " %s\n"
                        + "Now you have %d tasks in the list.",
                task,
                this.list.size()
        );
    }

    /**
     * Removes the task at the given index (0-based).
     *
     * @param index Index of task to be removed.
     * @return String to inform the user that the task has been removed successfully.
     */
    public String deleteTask(int index) {
        Task t = this.list.get(index);
        this.list.remove(index);
        return String.format(
                "OK Doc, I've removed this task:\n"
                        + " %s\n"
                        + "Now you have %d tasks in the list.",
                t,
                this.list.size());
    }

    /**
     * Marks the task at the given index (0-based).
     *
     * @param index Index of task to be removed.
     * @return String to inform the user that the task has been marked successfully.
     */
    public String markTask(int index) {
        this.list.get(index).updateStatus(true);
        return "OK Doc, I've marked this task as done:\n " + this.list.get(index);
    }

    /**
     * Unmarks the task at the given index (0-based).
     * @param index Index of task to be removed.
     * @return String to inform the user that the task has been unmarked successfully.
     */
    public String unmarkTask(int index) {
        this.list.get(index).updateStatus(false);
        return "OK Doc, I've marked this task as not done:\n " + this.list.get(index);
    }

    /**
     * @return Total number of tasks
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    /**
     *  @return The task at the given index (0-based).
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * @return The underlying list (mutable).
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Filters tasks considered due by the provided moment.
     *
     * @param dateTime Cutoff (inclusive for deadlines, see {@link Deadline#isDueBy(LocalDateTime)}).
     * @return A new list containing tasks due by {@code dateTime}.
     */
    public ArrayList<Task> getTasksDueBy(LocalDateTime dateTime) {
        ArrayList<Task> dueTasks = new ArrayList<>();

        for (Task task : this.list) {
            if (task.isDueBy(dateTime)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

    /**
     * Filters tasks based on whether the task contains the keyword in its description.
     *
     * @param keyword Object of interest.
     * @return A new list containing tasks that contains the {@code keyword}.
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.list) {
            if (task.hasKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Outputs a string to inform the user that the task has been added successfully.
     *
     * @param task Task that has been added.
     * @return
     */
    public String addTaskMessage(Task task) {
        return String.format(
                "OK Doc, I've added this task:\n"
                        + " %s\n"
                        + "Now you have %d tasks in the list.",
                task,
                this.list.size()
        );
    }
}
