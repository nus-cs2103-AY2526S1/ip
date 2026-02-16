package mambo.command;

import mambo.MamboException;
import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.TaskList;

/**
 * Represents a single command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public abstract class Command {
    private final String argument;

    public Command(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return this.argument;
    }

    public abstract String execute(Ui ui, TaskList tasks, TaskListFileManager file) throws MamboException;

    /**
     * Returns true except on Bye command when program will end
     * @return true
     */
    public boolean isRunning() {
        return true;
    }

    /**
     * Takes the argument of a command and splits it with the regex to return a string array where
     * each index is one detail of the command argument.
     * If command does not have argument, does nothing.
     *
     * @return Array of String containing details of command if command has argument.
     *         Empty array of strings otherwise.
     */
    public String[] splitArgumentIntoDetails() {
        return new String[] { };
    }

    /**
     * Compares if one command and another are equal by first checking if their types are the exact
     * same, then comparing the arguments of each command.
     * Commands are considered to be equal when they are of the same type and have the same argument.
     *
     * @param c Other object to compare to
     * @return True if Commands are equal, false otherwise
     */
    @Override
    public boolean equals(Object c) {
        if (c == null) {
            return false;
        }
        if (this.getClass() != c.getClass()) {
            return false;
        }

        Command obj = (Command) c;
        return this.argument.equals(obj.argument);
    }
}
