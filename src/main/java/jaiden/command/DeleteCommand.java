package jaiden.command;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for delete command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for delete command.
     *
     * @param inputs User input.
     */
    public DeleteCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.DELETECOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) throws JaidenException {
        int index = Integer.parseInt(inputs[1]) - 1;
        assert index >= 0;

        this.string = taskList.remove(index);
        assert this.string != null;

        storage.save(taskList);
    }
}
