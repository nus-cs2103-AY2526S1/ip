package cattis.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import cattis.CattisInterface;
import cattis.exception.CattisException;
import cattis.exception.CattisInvalidTimeException;
import cattis.task.Task;

/**
 * Represents action of finding all tasks for this date.
 */
public class ViewScheduleCommand extends Command {
    public static final String DATE_TIME_INPUT_FORMATTER = "yyyy-MM-dd";
    private final String date;

    public ViewScheduleCommand(String date) {
        this.date = date.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        try {
            var formatter = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMATTER);
            LocalDate targetDate = LocalDate.parse(this.date, formatter);
            List<Task> taskList = cattis.getTaskList().getTasksByDate(targetDate, true);
            taskList.stream().forEach(
                    task -> cattis.getUi().showMessage(task.toString() + "\n")
            );
        } catch (DateTimeParseException err) {
            throw new CattisInvalidTimeException(DATE_TIME_INPUT_FORMATTER);
        }
    }
}
