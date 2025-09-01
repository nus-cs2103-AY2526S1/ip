package aurora.command;

import aurora.task.TaskList;


/**
 * Command for invalid or unrecognized user input.
 */
public class InvalidCommand implements Command {

    private final String message;

    /**
     * Creates a new {@code InvalidCommand}.
     *
     * @param message error message to display
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(TaskList list) {
        return message;
    }
}
