package winnie.ui;

import java.time.LocalDateTime;

import winnie.command.Command;
import winnie.task.Task;
import winnie.tasklist.TaskList;

/**
 * User interface for handling input and output.
 */
public interface Ui {

    /**
     * Shows a welcome message to the user.
     */
    public void showWelcome();

    /**
     * Shows a goodbye message to the user.
     */
    public void showGoodbye();

    /**
     * Shows the current task list to the user.
     *
     * @param tasks The array of tasks to display.
     */
    public void showTaskList(TaskList tasks);

    /**
     * Shows a message when a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of tasks.
     */
    public void showTaskAdded(Task task, int taskCount);

    /**
     * Shows a message when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task);

    /**
     * Shows a message when a task is unmarked as done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task);

    /**
     * Shows a message when a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The current number of tasks.
     */
    public void showTaskDeleted(Task task, int taskCount);

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage);

    /**
     * Shows a message when there is an error loading tasks.
     */
    public void showLoadingError();

    /**
     * Shows the found tasks matching a search keyword.
     *
     * @param foundTasks The TaskList containing matching tasks.
     */
    public void showFoundTasks(TaskList foundTasks);

    /**
     * Shows a message when a task is snoozed.
     *
     * @param task        The task that was snoozed.
     * @param snoozeUntil The date and time until when the task is snoozed.
     */
    public void showTaskSnoozed(Task task, LocalDateTime snoozeUntil);

    /**
     * Reads a command from the user.
     *
     * @return The user's command.
     */
    public Command readCommand();
}
