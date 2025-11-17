package beebong.command;

import beebong.task.Task;
import beebong.task.ToDoTask;

/**
 * Represents a Command for adding a new {@link ToDoTask} to the task list.
 */
public class AddToDoTaskCommand extends AddTaskCommand {
    /**
     * Constructs an {@code AddToDoTaskCommand} with the given task name.
     *
     * @param name the name of the task to be created.
     */
    public AddToDoTaskCommand(String name) {
        super(name);
    }

    /**
     * Creates a new {@link ToDoTask}.
     *
     * @return the newly created task.
     */
    @Override
    protected Task createTask() {
        return new ToDoTask(this.name);
    }
}
