package baymax.command;

import baymax.task.TaskList;

/**
 * Represents a command that terminates the Baymax chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, which signals that the application should terminate,
     * and returns Baymax's farewell message.
     *
     * @param tasks The {@link TaskList}, unused by this command.
     * @return A farewell message.
     */
    @Override
    public String execute(TaskList tasks) {
        return "I will deactivate now. I hope you are satisfied with my care.";
    }


    /**
     * Indicates that this command will terminate the program.
     *
     * @return {@code true}, since this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
