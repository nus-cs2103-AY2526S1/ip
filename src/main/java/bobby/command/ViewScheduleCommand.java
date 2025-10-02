package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Command to view the schedule for a specific date.
 */
public class ViewScheduleCommand extends Command {
    private final LocalDate date;

    public ViewScheduleCommand(String dateStr) throws BobbyException {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy");
            this.date = LocalDate.parse(dateStr.trim(), fmt);
        } catch (DateTimeParseException e) {
            throw new BobbyException("Invalid date format. Use d/M/yyyy, e.g., 19/09/2025");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> scheduled = tasks.getTasksForDate(date);
        return ui.showScheduleForDate(date, scheduled);
    }
}

