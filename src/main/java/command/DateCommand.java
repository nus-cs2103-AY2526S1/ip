package command;

import java.time.LocalDate;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Handles the command to print tasks relevant to the specified date
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * Initialises the specified date
     * @param date
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Displays all tasks relevant to the specified date
     * @param tasks accumulated list of tasks
     * @param ui UI where notifications are displayed
     * @param storage storage system where tasks are saved
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printDateTasks(tasks.getTasks(), date);
    }
}
