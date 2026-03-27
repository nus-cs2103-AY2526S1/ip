package jaiden.command;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for unknown command.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for unknown command.
     *
     * @param inputs User input.
     */
    public UnknownCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.ERRORCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) throws JaidenException {
        this.string = "Oopsie! 😅 I’m not too sure what that means… could you help me out?";

        storage.save(taskList);
    }
}
