package commands;

import java.util.Map;

import app.Messages;
import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;

/**
 * This command searches the task list for tasks that match a given keyword.
 */
public class CommandTaskFind extends Command {
    private String taskDisplay;
    private String filterRegex;

    /**
     * Creates a Find command using the given user input.
     *
     * @param input Raw user input string containing the search keyword
     * @throws BoopError If no keyword is provided
     */
    public CommandTaskFind(String input) throws BoopError {
        Flags flags = Flags.parseFlags(Map.of(), input);

        if (!flags.has("")) {
            throw new BoopError(Messages.ERROR_FILTER_NOT_GIVEN);
        }

        filterRegex = flags.get("");
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        taskDisplay = tasklist.filterDisplay(filterRegex);
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_FIND.formatted(filterRegex, taskDisplay);
    }
}
