package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 * Handles the 'find' command for searching task descriptions.
 */
public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String fullCommand) throws CharliException {
        // Trim leading/trailing whitespace
        String trimmed = fullCommand.trim();

        // "find" is 4 chars. If trimmed length == 4, then there's no keyword.
        if (trimmed.length() <= 4) {
            throw new CharliException("You gotta specify a keyword...");
        }

        // Otherwise, cut off the command word and trim again
        this.keyword = trimmed.substring(4).trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {

        return tasks.getMatchingTasks(keyword);

    }

    @Override
    public boolean isExit() {
        return false;
    }




}
