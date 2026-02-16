package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to display the goodbye message and exit the TalkGPT application.
 */
public class GoodbyeCommand extends Command {

    /**
     * Constructs a GoodbyeCommand to execute the goodbye statement.
     */
    public GoodbyeCommand() {}

    /**
     * Executes the goodbye command using the UI.
     *
     * @param list TaskList (not used in this command).
     * @param ui UI to print the goodbye statement.
     * @param storage Storage (not used in this command).
     * @return The goodbye message as a string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.goodbye();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }
}
