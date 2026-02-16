package luna.command;

import luna.task.Task;
import luna.task.ToDo;

/**
 * Represents the {@code todo} command.
 */
public class TodoCommand extends AddTaskCommand {
    private final String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    protected Task getTaskToAdd() {
        return new ToDo(name);
    }
}
