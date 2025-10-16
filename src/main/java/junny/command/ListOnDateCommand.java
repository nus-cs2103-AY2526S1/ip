package junny.command;

import java.time.LocalDate;
import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

/**
 * Represents a command that lists tasks occurring on a specific date.
 */
public class ListOnDateCommand extends Command {
    private LocalDate date;
    /**
     * Constructs a ListOnDateCommand for a given date.
     *
     * @param date The date to filter tasks by.
     */
    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the list-on-date command by showing tasks scheduled
     * on the specified date.
     *
     * @param tasks   List of current tasks.
     * @param ui      The UI used to interact with the user.
     * @param storage The storage handler (not used here).
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            ui.printTaskOnDate(this.date, tasks);
        } catch (Exception e) {
            ui.printError("Please enter the date in yyyy-MM-dd format.");
        }
    }
}
