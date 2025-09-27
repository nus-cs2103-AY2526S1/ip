package steven.command;

import steven.exception.MissingFindKeywordException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Finds tasks in the task list that match the given search keyword.
 * The command encapsulates the input string representing the keyword
 * and handles execution including error handling.
 */
public class FindCommand implements Command {
    private final String input;

    /**
     * Constructs a new FindCommand with the given input.
     *
     * @param input String containing the keyword to be used for searching tasks.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.findTasks(input);
        } catch (MissingFindKeywordException e) {
            return e.getMessage();
        }
    }
}
