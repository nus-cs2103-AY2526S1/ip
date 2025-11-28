package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Command to mark a task as completed.
 */
public class MarkCommand implements Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getIndex() {
        return taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        String message;

        tasks.markTask(taskIndex);
        storage.saveTasks(tasks.getTasks());
        message = "Good job for completing!!!\n" + tasks.getTask(taskIndex).toString() + "\n";

        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
