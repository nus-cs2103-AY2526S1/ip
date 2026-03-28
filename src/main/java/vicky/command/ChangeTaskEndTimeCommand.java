package vicky.command;

import java.io.IOException;
import java.time.LocalDateTime;

import vicky.exception.InvalidInputException;
import vicky.storage.Storage;
import vicky.tasklist.Deadline;
import vicky.tasklist.Task;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to change a task's end time.
 */
public class ChangeTaskEndTimeCommand extends Command {

    private int index;
    private LocalDateTime by;

    public ChangeTaskEndTimeCommand(int index, LocalDateTime by) {
        this.index = index;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.getTask(this.index);
            if (t instanceof Deadline d) {
                d.changeBy(this.by);
                storage.save(tasks);
                return ui.showChangeTask(d);
            } else {
                throw new InvalidInputException("What r you tryna do? This task has no deadline.");
            }

        } catch (InvalidInputException e) {
            return ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
