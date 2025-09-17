package Dan.Command;

import Dan.Task.TaskList;

public class ExitCommand extends Command {

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.EXIT indicating this is an exit command
     */
    public CommandType getType() {
        return CommandType.EXIT;
    }

    /**
     * Executes the exit command by returning a farewell message.
     * This command does not modify the task list and is used to terminate the application.
     *
     * @param tasks the task list (not used in this command)
     * @return a farewell message to be displayed when the application exits
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!\n";
    }
}
