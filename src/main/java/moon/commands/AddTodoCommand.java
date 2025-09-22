package moon.commands;

import moon.commands.enums.Command;
import moon.models.Todo;
import moon.ui.UiMessages;

/**
 * Command to add a {@link Todo} task to the task list.
 */
public class AddTodoCommand extends AddCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.TODO;

    /** The todo task to be added. */
    public final Todo todo;

    /**
     * Creates a new AddTodoCommand.
     *
     * @param todo The todo task to add
     */
    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Adds the todo to the task list and shows a confirmation message.
     *
     * @return confirmation message to be displayed to the user
     */
    @Override
    public String execute() {
        addToStorage(todo);
        return UiMessages.showAddTaskMessage(todo);
    }
}
