package johnny.commands;

import java.time.LocalDateTime;
import java.time.LocalTime;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.tasks.EventTask;
import johnny.tasks.Task;
import johnny.ui.Ui;

/**
 * A command that adds an event to the TaskList.
 * This event has falls on a particular date and has a start and end time.
 */
public class EventCommand extends Command {
    protected String name;
    protected LocalDateTime start;
    protected LocalTime end;

    /**
     * Creates an EventCommand with the name of the event, the start date and time,
     * and the end date and time.
     * 
     * @param name  Name of event
     * @param start Start date and time
     * @param end   End time
     */
    public EventCommand(String name, LocalDateTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalTime getEnd() {
        return this.end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new EventTask(this.name, this.start, this.end);
        tasks.addTask(newTask);
        return ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
