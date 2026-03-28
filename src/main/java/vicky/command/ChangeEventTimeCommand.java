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
public class ChangeEventTimeCommand extends Command {

    private int index;
    private LocalDateTime from;
    private LocalDateTime by;

    public ChangeEventTimeCommand(int index, LocalDateTime from, LocalDateTime by) {
        this.index = index;
        this.from = from;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.getTask(this.index);
            if (t instanceof Event e) {
                e.changeEventTime(this.from, this.by);
                storage.save(tasks);
                return ui.showChangeTask(e);
            } else {
                throw new InvalidInputException("DING DONG wake up brother!! This task is NOT an event.");
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
