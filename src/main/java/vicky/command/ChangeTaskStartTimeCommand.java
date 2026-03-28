package vicky.command;

import java.io.IOException;
import java.time.LocalDateTime;

import vicky.exception.InvalidInputException;
import vicky.storage.Storage;
import vicky.tasklist.Event;
import vicky.tasklist.Task;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to change a task's start time.
 */
public class ChangeTaskStartTimeCommand extends Command {

    private int index;
    private LocalDateTime from;

    public ChangeTaskStartTimeCommand(int index, LocalDateTime from) {
        this.index = index;
        this.from = from;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.getTask(this.index);
            if (t instanceof Event e) {
                e.changeFrom(this.from);
                storage.save(tasks);
                return ui.showChangeTask(e);
            } else {
                throw new InvalidInputException("What r you tryna do? This task has no start time.");
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
