package bro.commands;

import bro.tasks.Tasks;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        return "Oh, going already? Bye bro, see you next time!";
    }
}
