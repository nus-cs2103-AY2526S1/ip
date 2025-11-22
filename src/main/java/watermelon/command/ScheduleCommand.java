package watermelon.command;

import java.time.LocalDate;
import java.util.ArrayList;

import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.InvalidTaskTypeException;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents a schedule command that contains a date, ui, storage, tasklist and message.
 * Upon execution, tasks scheduled on specified date are saved into the message.
 */
public class ScheduleCommand extends Command {
    private LocalDate date;
    private boolean isDateProvided;

    /** Constructs a ScheduleCommand object with specified date. **/
    public ScheduleCommand(TaskList taskList, LocalDate date, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.date = date;
        this.isDateProvided = true;
    }

    /** Constructs a ScheduleCommand object without specified date. **/
    public ScheduleCommand(TaskList taskList, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.date = LocalDate.now();
        this.isDateProvided = false;
    }

    @Override
    public void execute() throws StorageOperationException, InvalidTaskTypeException {
        ArrayList<Task> scheduledTasks = taskList.findScheduledTask(date);
        assert scheduledTasks != null : "scheduledTasks should not be null";

        if (scheduledTasks.isEmpty()) {
            message = this.isDateProvided
                    ? ui.showNoTasksScheduledMessage(date)
                    : ui.showNoTasksScheduledMessage();
            return;
        }

        message = this.isDateProvided
                ? ui.showTasksScheduledMessage(scheduledTasks, date)
                : ui.showTasksScheduledMessage(scheduledTasks);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
