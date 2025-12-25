package khat.command;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Saves all tasks added and shows exit message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KhatException {
        storage.saveTasks(tasks);
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
