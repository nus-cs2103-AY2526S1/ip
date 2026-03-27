package marvin.command;

import marvin.Personality;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for the find command in Marvin.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Instantiate a find command.
     *
     * @param query The text to look for in the tasklist.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        String found = taskList.searchTasks(this.query);
        String reply = Personality.getFoundItemText() + "\n" + found;
        return new CommandResult(() -> Ui.printGeneric(reply), reply);
    }
}
