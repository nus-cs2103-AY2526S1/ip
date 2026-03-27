package jaiden.command;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for mark and unmark commands.
 */
public class ChangeMarkCommand extends Command {
    /**
     * Constructor for mark and unmark commands.
     *
     * @param inputs User input.
     */
    public ChangeMarkCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.CHANGEMARKCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) throws JaidenException {
        int index = Integer.parseInt(inputs[1]) - 1;
        assert index >= 0;

        switch (inputs[0]) {
        case "mark":
            this.string = taskList.mark(index);
            break;
        case "unmark":
            this.string = taskList.unmark(index);
            break;
        default:
            this.string = "";
            break;
        }
        assert this.string != null;

        storage.save(taskList);
    }
}
