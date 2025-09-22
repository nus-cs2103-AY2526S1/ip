package jerome.command;

import jerome.TaskList;
import jerome.storage.Storage;
import jerome.task.Task;
import jerome.ui.Ui;

/**
 * Command to mark or unmark a task in the task list.
 * This command marks a task as isComplete or unmarks it based on the specified index.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isUnmark;

    /**
     * Constructs a MarkCommand with the specified task index and mark/unmark flag.
     *
     * @param index The index of the task to be marked or unmarked.
     * @param isUnmark If true, the task will be unmarked. If false, the task will be marked.
     */
    public MarkCommand(int index, boolean isUnmark) {
        this.index = index;
        this.isUnmark = isUnmark;
    }

    /**
     * Executes the mark/unmark task command.
     *
     * @param tasks The task list to be modified.
     * @param ui The UI component for displaying messages.
     * @param storage The storage component to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        if (isUnmark) {
            task.unmark();
            storage.save(tasks);
            return ui.successfulUnmarkText(task);
        } else {
            task.mark();
            storage.save(tasks);
            return ui.successfulMarkText(task);
        }
    }
}
