package uxie.commands;

import java.time.LocalDateTime;

import uxie.exceptions.UxieIOException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;
import uxie.tasks.Deadline;

/**
 * Command for creating Deadline tasks.
 *
 * @author junyan-k
 */
public class DeadlineCommand extends Command {

    /** Task to be added. */
    private Deadline task;

    /**
     * Generates a DeadlineCommand with parameters for Deadline.
     * @see uxie.tasks.Deadline#Deadline(String, LocalDateTime)
     */
    public DeadlineCommand(String desc, LocalDateTime deadline) {
        this.task = new Deadline(desc, deadline);
    }

    /**
     * Generates a DeadlineCommand with parameters for Deadline.
     * @see uxie.tasks.Deadline#Deadline(boolean, String, LocalDateTime)
     */
    public DeadlineCommand(boolean isCompleted, String desc, LocalDateTime deadline) {
        this.task = new Deadline(isCompleted, desc, deadline);
    }

    /**
     * {@inheritDoc}
     * Adds the Deadline to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.storeTask(task);
        } catch (UxieIOException e) {
            ui.appendException(e);
        }
        ui.uxieAppendln(String.format("Alright. Task added:\n  %s\nYou have %s total tasks. "
                        + "But we all know you'll just rush them at the last minute like you always do.",
                task, tasks.getSize()));
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
