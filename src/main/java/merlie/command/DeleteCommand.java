package merlie.command;

import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String INVALID_INDEX_ERROR = "enter a valid task number";
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param index Index of task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc} Deletes the task at the specified index from the list, updates
     * list file, and shows output to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (!list.isValidIndex(this.taskIndex)) {
            throw new MerlieException(INVALID_INDEX_ERROR);
        }

        Task task = list.getTask(this.taskIndex);
        list.delete(this.taskIndex);
        listFile.save(list);
        ui.deleteOutput(task, list.size());
    }

}
