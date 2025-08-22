package command;

import task.Task;
import java.util.List;

/**
 * Command for invalid or unrecognized user input.
 */
public class InvalidCommand implements Command{

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
    public String execute(List<Task> list) {
        return message;
    }
}
