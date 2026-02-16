package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword in the task list in the TalkGPT application.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find task function for the TaskList and UI.
     *
     * @param list TaskList to search for matching tasks.
     * @param ui UI for displaying the matching tasks.
     * @param storage Storage (not used in this command).
     * @return The formatted list of matching tasks as a string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        TaskList tasks = list.findTask(keyword);
        return ui.findTask(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FindCommand that = (FindCommand) obj;
        return keyword.equals(that.keyword);
    }
}
