package charli.command;

import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to exit the Charli chatbot application.
 * Handles the 'bye' command for application termination.
 */
public class ExitCommand implements Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        return "XOXO, Charli";
    }

    public boolean isExit() {
        return true;
    }
}
