package chathyt.command;

/**
 *  Represents a command to view tasks on a single date.
 */

import java.time.LocalDate;
import java.util.ArrayList;

import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;

public class ViewDateCommand implements Command {
    private final LocalDate date;

    public ViewDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     *
     * @param tasks The list of existing tasks.
     * @param storage The storage handler for saving tasks.
     * @param ui The UI handler for displaying messages.
     * @return A string message confirming all tasks in the day.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        String schedule = Ui.displaySchedule(date, tasks);
        if (schedule.isEmpty()) {
            return "No tasks scheduled for " + date.toString();
        } else {
            return "Tasks for " + date.toString() + ":\n" + schedule;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
