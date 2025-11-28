package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Command to unmark a completed task.
 */
public class UnmarkCommand implements Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getIndex() {
        return taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.unmarkTask(taskIndex);
        storage.saveTasks(tasks.getTasks());
        String message = "You are undoing this task\n" + tasks.getTask(taskIndex).toString() + "\n";

        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
