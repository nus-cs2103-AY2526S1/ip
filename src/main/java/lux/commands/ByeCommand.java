package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command to gracefully shut down the application.
 *
 * <p>When executed it persists tasks and aliases to storage and returns the
 * goodbye message produced by {@link lux.ui.Ui}.
 */
public class ByeCommand extends Command {
    /**
     * Persist current application state and return the farewell message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        try {
            storage.saveTasks(tasks);
            storage.saveAliases(aliases);
            return ui.showBye();
        } catch (LuxException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return true;
    }
}
