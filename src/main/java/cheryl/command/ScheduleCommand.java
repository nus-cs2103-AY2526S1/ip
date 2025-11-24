package cheryl.command;

import cheryl.util.DukeException;
import cheryl.util.Storage;
import cheryl.util.TaskList;
import cheryl.util.Ui;
import cheryl.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a command to list tasks scheduled for a specific date.
 */
public class ScheduleCommand implements Command {
    private final LocalDate date;

    public ScheduleCommand(String dateStr) throws DukeException {
        try {
            this.date = LocalDate.parse(dateStr.trim()); // format: yyyy-MM-dd
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use yyyy-MM-dd format for dates.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> scheduled = tasks.getTasksForDate(date);
        if (scheduled.isEmpty()) {
            ui.showMessage("No tasks scheduled for " + date + ".");
        } else {
            ui.showMessage("Tasks scheduled for " + date + ":");
            for (int i = 0; i < scheduled.size(); i++) {
                ui.showMessage((i + 1) + ". " + scheduled.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
