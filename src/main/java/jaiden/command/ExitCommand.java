package jaiden.command;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for exit command.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for exit command.
     *
     * @param inputs User input.
     */
    public ExitCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.EXITCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) throws JaidenException {
        this.string = "Bye. Hope to see you again soon!";

        storage.save(taskList);
    }
}
