package winnie.command;

import winnie.exception.InvalidTaskNumberException;
import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {

    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskNumber < 1 || taskNumber > tasks.getTaskCount()) {
                throw new InvalidTaskNumberException(taskNumber, tasks.getTaskCount());
            }
            Task unmarkedTask = tasks.unmarkTask(taskNumber - 1);
            ui.showTaskUnmarked(unmarkedTask);
            storage.saveTasksToFile(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
