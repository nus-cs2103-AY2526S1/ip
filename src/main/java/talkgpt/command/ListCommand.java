package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to list all tasks in the task list in the TalkGPT application.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand to display all tasks.
     */
    public ListCommand() {}

    /**
     * Executes the list command to display all tasks using the UI.
     *
     * @param list TaskList containing all tasks.
     * @param ui UI to print the list statement.
     * @param storage Storage (not used in this command).
     * @return The formatted list of tasks as a string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.listView(list);
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
