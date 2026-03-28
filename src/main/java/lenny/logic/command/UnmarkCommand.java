package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents a command that unmarks a task in the task list as not done.
 * This command updates both the in-memory task list and the persistent storage.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an UnmarkCommand with the specified task index.
     *
     * @param i The index of the task to be unmarked, based on the task list.
     */
    public UnmarkCommand(int i) {
        this.index = i;
    }

    /**
     * Executes the unmark command by setting the specified task as not done,
     * saving the updated task list to storage, and returning a confirmation message.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param storage The Storage object responsible for saving the task list.
     * @param ui      The Ui component used for interactions (not directly used here).
     * @return A confirmation message indicating the task has been unmarked.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.unmark(index);
        storage.save(tasks);
        //Used AI to generate personality
        return "Back to the to-do pile it goes:\n" + " " + tasks.get(index);
    }
}
