package command;

import tasklist.TaskList;
import ui.UI;

/**
 * Represents an invalid or unrecognized command.
 * Displays an error message to the user when executed.
 */
public class InvalidCommand extends Command {
    private String message;

    /**
     * Constructs an InvalidCommand with the specified error message.
     *
     * @param message the error message to display to the user
     */
    public InvalidCommand(String message) {
        super(CommandType.INVALID);
        this.message = message;
    }

    /**
     * {@inheritDoc}
     * Displays the invalid command error message to the user.
     *
     * @param taskList the task list (not used in this command)
     */
    @Override
    public String execute(TaskList taskList) {
        return UI.showMessage(message);
    }

    /**
     * Returns the error message associated with this invalid command.
     *
     * @return the error message string
     */
    public String getMessage() {
        return this.message;
    }
}
