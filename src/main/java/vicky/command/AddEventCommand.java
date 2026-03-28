package vicky.command;

import java.io.IOException;
import java.time.LocalDateTime;

import vicky.storage.Storage;
import vicky.tasklist.Event;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to add a new event task.
 */
public class AddEventCommand extends Command {

    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for AddEventCommand class, initialises the command with a description, start time and end time.
     *
     * @param description The name of the event task to be created.
     * @param start The start time of the event task to be created.
     * @param end The end time of the event task to be created.
     */
    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Event event = new Event(this.description, this.start, this.end);
        tasks.addTask(event);
        storage.save(tasks);
        return ui.showAddedTask(event, tasks.len());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
