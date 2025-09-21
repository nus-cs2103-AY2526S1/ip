package merlie.command;

import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {
    private static final String INVALID_INDEX_ERROR = "enter a valid task number";
    private final int taskIndex;

    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param index Index of task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     * Marks the task at the given index as done, updates list file,
     * and shows output to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (!list.isValidIndex(this.taskIndex)) {
            throw new MerlieException(INVALID_INDEX_ERROR);
        }

        Task task = list.getTask(this.taskIndex);
        list.markDone(this.taskIndex);
        listFile.save(list);
        ui.markDoneOutput(task);
    }
}
