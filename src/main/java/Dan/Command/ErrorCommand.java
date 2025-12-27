package Dan.Command;

import Dan.Task.TaskList;

public class ErrorCommand extends Command {
    String errorMessage;

    /**
     * Constructs an ErrorCommand with the specified error message.
     *
     * @param errorMessage the error message to be displayed when the command is executed
     */
    public ErrorCommand(String errorMessage) {
       this.errorMessage = errorMessage;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.ERROR indicating this is an error command
     */
    public CommandType getType() {
        return CommandType.ERROR;
    }

    /**
     * Executes the error command by returning the stored error message.
     * This command does not modify the task list.
     *
     * @param tasks the task list (not used in this command)
     * @return the error message that was set during construction
     */
    public String execute(TaskList tasks) {
       return errorMessage;
    }
}
