package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.Task;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents a command that adds a new task to the task list.
 * When executed, this command appends the given task, updates
 * the persistent storage, and returns a confirmation message.
 */
public class AddCommand extends Command implements SupportsPriority {
    private final Task task;
    private int priority;

    /**
     * Creates an {@code AddCommand} with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }



    /**
     * Executes the add command by appending the task to the task list,
     * saving the updated task list to storage, and returning a confirmation
     * message that includes the added task and the updated task count.
     *
     * @param tasks   The TaskList where the task will be added.
     * @param storage The Storage responsible for persisting changes.
     * @param ui      The  Ui component for user interactions
     * @return A confirmation message showing the added task and the updated
     *         number of tasks in the list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        task.setPriority(priority);
        tasks.add(task);
        storage.save(tasks);
        // Usage of AI to add personality
        return "Boom! Just added to your list:\n" + task + "\n" + "This task has priority level: " + task.getPriority() + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
