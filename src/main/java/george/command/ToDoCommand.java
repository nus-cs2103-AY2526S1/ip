package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to add a todo task to the task manager.
 * A todo task consists of a description without any date or time constraints.
 */
public class ToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a ToDoCommand with the specified description.
     *
     * @param description The description of the todo task
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return manager.addToDoTask(description);
    }

    @Override
    public String getCommandWord() {
        return "todo";
    }
}
