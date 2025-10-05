package sage.command;

import sage.task.Todo;

/**
 * Represents a command to add a Todo task.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Constructs an AddTodoCommand with the given description for the Todo task.
     * @param description The description of the Todo task.
     */
    public AddTodoCommand(String description) {
        super(new Todo(description));
    }
}