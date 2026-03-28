package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.Task;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 * When executed, this command removes the specified task, updates
 * the persistent storage, and returns a confirmation message.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand with the specified task index.
     *
     * @param i The index of the task to be deleted, based on the task list.
     */
    public DeleteCommand(int i) {
        this.index = i;
    }

    /**
     * Executes the delete command by removing the task at the given index,
     * saving the updated task list to storage, and returning a confirmation
     * message including the removed task and the updated task count.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param storage The Storage object responsible for persisting changes.
     * @param ui      The Ui component used for interactions
     *                (not directly used here).
     * @return A confirmation message showing the removed task and the
     *         updated number of tasks remaining.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Task removed = tasks.delete(index);
        storage.save(tasks);
        //Used AI to generate personality
        return "Task erased from existence:\n" + " " + removed + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
