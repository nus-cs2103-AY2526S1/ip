package command;

import tasklist.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * Removes the task at the specified index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param index the 1-based index of the task to delete
     */
    public DeleteCommand(int index) {
        super(CommandType.DELETE);
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Deletes the task at the specified index from the task list.
     *
     * @param taskList the task list from which the task will be deleted
     */
    @Override
    public String execute(TaskList taskList) {
        assert index < taskList.size() && index > 0 : "index out of range in execute";
        return taskList.deleteTask(index);
    }
}
