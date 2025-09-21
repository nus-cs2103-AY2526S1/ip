package merlie.command;

import merlie.task.Todo;

/**
 * Adds a Todo task to the task list.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand for the specified Todo task.
     *
     * @param description Description of the Todo task.
     */
    public TodoCommand(String description) {
        super(new Todo(description));
    }
}
