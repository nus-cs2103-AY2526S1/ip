package winnie.command;

import java.time.LocalDateTime;

import winnie.exception.WinnieException;
import winnie.exception.InvalidDateTimeException;
import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;
import winnie.util.DateTimeUtil;

public class SnoozeCommand extends Command {
    private int taskNumber;
    private String snoozeUntilString;

    public SnoozeCommand(int taskNumber, String snoozeUntilString) {
        this.taskNumber = taskNumber;
        this.snoozeUntilString = snoozeUntilString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskNumber < 1 || taskNumber > tasks.getTaskCount()) {
                throw new WinnieException("Invalid task number: " + taskNumber);
            }
            
            LocalDateTime snoozeUntil = DateTimeUtil.parseDateTime(snoozeUntilString);
            Task task = tasks.getTask(taskNumber - 1);
            task.snooze(snoozeUntil);
            
            ui.showTaskSnoozed(task, snoozeUntil);
            storage.saveTasksToFile(tasks);
        } catch (InvalidDateTimeException e) {
            ui.showError("Invalid date/time format: " + e.getMessage());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}