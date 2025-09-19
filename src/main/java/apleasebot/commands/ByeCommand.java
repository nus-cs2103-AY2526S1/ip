package apleasebot.commands;

import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;
import apleasebot.ui.Storage;

/**
 * Encapsulates the logic that is run when the user types 'bye'
 */
public class ByeCommand implements Command {
    private final Storage storage;

    public ByeCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String execute(TaskList taskList) {
        storage.close(taskList);
        return APleaseBot.LINE + APleaseBot.CLOSE + APleaseBot.LINE;
    }
}
