package winnie.command;

import winnie.exception.WinnieException;
import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskNumber < 1 || taskNumber > tasks.getTaskCount()) {
                throw new WinnieException("Invalid task number: " + taskNumber);
            }
            Task deletedTask = tasks.deleteTask(taskNumber - 1);
            ui.showTaskDeleted(deletedTask, tasks.getTaskCount());
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
