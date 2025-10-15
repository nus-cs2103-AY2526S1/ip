package bro.commands;

import bro.tasks.Tasks;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a new ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        String output = "Here bro, these are the tasks in your list:";

        for (int i = 0; i < tasks.getSize(); i++) {
            output += String.format("\n%d. %s",
                    i + 1, tasks.getEntry(i));
        }

        return output;
    }
}
