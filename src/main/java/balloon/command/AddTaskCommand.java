package balloon.command;

import balloon.exception.TaskNumberException;
import balloon.logic.Balloon;
import balloon.logic.Storage;
import balloon.logic.TaskList;
import balloon.task.Task;

/**
 * Represents a command that adds a task to the task list in Balloon.
 * <p>
 * When executed, it adds the specified task to the end of the task list.
 * <p>
 * There are 3 concrete subclasses of this abstract class, which are:
 * {@link TodoCommand}, {@link DeadlineCommand}, and {@link EventCommand}
 * <p>
 * All subclasses of this command supports undo, which removes the most recently added
 * task from the task list.
 */
public abstract class AddTaskCommand extends Command {
    protected Task task;
    private int numberOfTasks;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Balloon balloon) {
        tasks.addTask(task);
        numberOfTasks = tasks.getSize();
        assert tasks.getSize() > 0 : "Task list should not be empty after adding a task";
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws TaskNumberException {
        tasks.deleteTask(tasks.getSize() - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public String getString() {
        return "Got it. I've added this task: \n\t" + task + "\n"
                + "Now you have " + numberOfTasks + " tasks in the list.";
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
