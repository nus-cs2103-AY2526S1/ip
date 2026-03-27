package jaiden.command;

import java.time.LocalDate;

import jaiden.exception.JaidenException;
import jaiden.storage.Storage;
import jaiden.task.TaskList;

/**
 * Class for list, show and find commands.
 */
public class ListCommand extends Command {
    /**
     * Constructor for list, show and find commands.
     *
     * @param inputs User input.
     */
    public ListCommand(String[] inputs) {
        super(inputs);
        this.commandType = CommandType.LISTCOMMAND;
    }

    /**
     * @inheritDoc
     */
    public void execute(TaskList taskList, Storage storage) throws JaidenException {
        switch (inputs[0]) {
        case "list":
            this.string = taskList.list();
            break;
        case "view":
            LocalDate viewDate = LocalDate.parse(inputs[1]);
            this.string = taskList.view(viewDate);
            break;
        case "find":
            this.string = taskList.find(inputs[1]);
            break;
        default:
            this.string = "";
            break;
        }
        assert this.string != null;

        storage.save(taskList);
    }
}
