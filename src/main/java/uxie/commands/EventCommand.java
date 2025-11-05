package uxie.commands;

import java.time.LocalDateTime;

import uxie.exceptions.UxieIOException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;
import uxie.tasks.Event;

/**
 * Command for creating Event tasks.
 *
 * @author junyan-k
 */
public class EventCommand extends Command {

    /** Task to be added. */
    private Event task;

    /**
     * Generates an EventCommand with parameters for Event.
     * @see uxie.tasks.Event#Event(String, LocalDateTime, LocalDateTime)
     */
    public EventCommand(String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        task = new Event(desc, startDateTime, endDateTime);
    }

    /**
     * Generates an EventCommand with parameters for Event.
     * @see uxie.tasks.Event#Event(boolean, String, LocalDateTime, LocalDateTime)
     */
    public EventCommand(boolean isCompleted, String desc,
            LocalDateTime startDateTime, LocalDateTime endDateTime) {
        task = new Event(isCompleted, desc, startDateTime, endDateTime);
    }

    /**
     * {@inheritDoc}
     * Adds the Event to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        try {
            storage.storeTask(task);
        } catch (UxieIOException e) {
            ui.appendException(e);
        }
        ui.uxieAppendln(String.format("Alright. Task added:\n  %s\nYou have %s total tasks. Have fun.",
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
