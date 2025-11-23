package jinwoo.command;

import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.Task;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * Delete command
 */
public class DeleteCommand extends Command {

    /**
     * @param fullCommand of single number element
     */
    public DeleteCommand(String[] fullCommand) {
        super(fullCommand);
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert getCommand().length == 1 : "The length of delete command should be 1";
        int taskNum = Integer.parseInt(getCommand()[0]) - 1;
        if (taskNum < 0 || taskNum >= tasks.getTasks().size()) {
            throw new DukeException("Warning ???: delete number is invalid.");
        }
        Task deletedTask = tasks.deleteTask(taskNum);
        ui.printDelete(deletedTask, tasks.getSize());
        storage.saveTasks(tasks.getTasks());
    }
}
