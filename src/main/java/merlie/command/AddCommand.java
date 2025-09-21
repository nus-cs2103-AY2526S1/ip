package merlie.command;

import merlie.exception.MerlieException;
import merlie.listfile.ListFile;
import merlie.task.Task;
import merlie.tasklist.TaskList;
import merlie.ui.Ui;

/**
 * Adds a task to the task list if it is not a duplicate. This command handles
 * the addition of Todo, Deadline, and Event tasks.
 */
public abstract class AddCommand extends Command {
    protected final Task task;

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task Task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc} Adds the task to the list if it is not a duplicate, updates
     * list file, and shows output to the UI.
     */
    @Override
    public void execute(TaskList list, Ui ui, ListFile listFile) throws MerlieException {
        if (!list.isTaskInList(task, ui, listFile)) {
            list.add(task);
            listFile.save(list);
            ui.addOutput(task, list.size());
        }
    }
}
