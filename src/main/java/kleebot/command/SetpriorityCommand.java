package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.Task;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command to change the priority of the associated task.
 */
public class SetpriorityCommand extends Command {
    private final int newValue;
    private final int index;


    /**
     * Constructs a new {@code SetpriorityCommand}.
     *
     * @param index The number of the task to be modified. (1-index)
     * @param value The new priority of the task.
     */
    public SetpriorityCommand(int index, int value) {
        this.newValue = value;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        Task task = tasks.getTask(index);
        task.setPriority(newValue);
        ui.updatePriority(task, newValue);
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        Task task = tasks.getTask(index);
        task.setPriority(newValue);
        storage.saveTasksToLocal(tasks.getTasks());
        return ui.formatPriority(task, newValue);
    }
}
