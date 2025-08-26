package beebong.command;

import beebong.exception.BBongException;
import beebong.exception.InvalidTaskDetailsException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

public class MarkTaskAsCommand extends Command {
    private final int taskNum;
    private final boolean status;

    public MarkTaskAsCommand(int taskNum, boolean status) {
        this.taskNum = taskNum;
        this.status = status;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // Check for valid task number
        if (taskNum < 0 || taskNum >= taskList.length()) {
            throw new InvalidTaskDetailsException("That task number doesn’t exist. Try a real one!");
        }
        // Mark Task as Completed/Incomplete
        taskList.markTaskAs(taskNum, status);
        ui.botMessage("Bing! Task #" + (taskNum + 1) + " marked as " + (status ? "complete" : "incomplete") + "!");
    }
}
