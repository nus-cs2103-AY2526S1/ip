package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.exception.InvalidCommandException;

/**
 * Represents command that does not exist.
 */
public class InvalidCommand extends Command {
    private final String invalidCommand;

    /**
     * Constructor for invalid command.
     *
     * @param invalidCommand Attempted command that does not exist.
     */
    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws InvalidCommandException {
        throw new InvalidCommandException(invalidCommand);
    }
}
