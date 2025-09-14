package tsundere.command;

import java.util.Objects;

import tsundere.TsundereException;
import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Adds a task to the task list, storage, and prints a message to the user.
 */
public class AddTaskCommand extends AbstractCommand {
    private final Task task;

    /**
     * Adds a new task to the task list and storage.
     * @param task Task to be added
     */
    public AddTaskCommand(Task task) {
        super(false, true);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, AbstractStorage storage, Log log) {
        try {
            tasks.add(task);
            storage.storeAll(tasks);
            ui.displayMessage(UiMessages.ADD_TASK, task);
            log.add(this);
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, AbstractStorage storage) {
        try {
            tasks.undoAdd();
            storage.storeAll(tasks);
            ui.displayMessage("Successfully undid the last action of adding the following task: \n" + task + "\n");
        } catch (TsundereException e) {
            ui.displayMessage(UiMessages.UNDO, this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddTaskCommand addTaskCommand) {
            return this.task.equals(addTaskCommand.task);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.task);
    }

    @Override
    public String toString() {
        return "Add Task Command: " + task.toString();
    }
}
