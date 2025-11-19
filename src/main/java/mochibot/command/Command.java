package mochibot.command;

import mochibot.MochiBotException;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

/**
 * This abstract class provides the abstract method {@code execute} for
 * child classes to implement, and {@code hasExit} method to indicate the user
 * exiting the program.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Gui gui) throws MochiBotException;

    public boolean hasExit() {
        return false;
    }
}
