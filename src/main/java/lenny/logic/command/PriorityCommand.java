package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents a command that searches for tasks in the task list
 * whose priority is of a certain level.
 * Returns a list of matching tasks formatted for display.
 */
public class PriorityCommand extends Command {
    private final int priority;

    /**
     * Creates a PriorityCommand with the specified integer priority to search for.
     *
     * @param p The integer whose task priority level matches.
     */
    public PriorityCommand(int p) {
        this.priority = p;
    }

    /**
     * Executes the priority command by searching through the task list
     * for tasks that priority level matches the priority index we are searching for. The
     * resulting matches are formatted as a numbered list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param storage The Storage object responsible for persisting tasks
     *                (not directly used here).
     * @param ui      The Ui component used for interactions
     *                (not directly used here).
     * @return A string containing all tasks that match the keyword,
     *         formatted as a numbered list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.showPriority(priority);
    }
}
