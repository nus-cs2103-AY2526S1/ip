package mochibot.command;

import mochibot.storage.Storage;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

/**
 * This class represents an Exit command, providing the method {@code execute}
 * to print the exit message, and save the current task list into the local text file.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Gui gui) {
        Storage.saveTaskList(tasks);
        return gui.displayExit();
    }

    @Override
    public boolean hasExit() {
        return true;
    }
}
