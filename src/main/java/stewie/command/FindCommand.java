package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;

/**
 * Command to find tasks by description keyword.
 */
public class FindCommand implements Command {
    private final String args;

    /**
     * Creates a new FindCommand with the specified search arguments.
     *
     * @param args The search keyword or phrase.
     */
    public FindCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the find command to search for tasks containing the specified keyword.
     *
     * @param taskList The task list to search in.
     * @param storage The storage instance (not used in this command).
     * @return A formatted string containing matching tasks.
     * @throws CommandException If the search keyword is blank or null.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        if (args.isBlank()) {
            throw new InvalidCommandException("find <description>");
        }

        return taskList.findTaskByDescription(args);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FIND;
    }
}
