package angus.command;

import java.time.LocalDate;

import angus.exception.AngusException;
import angus.task.TaskList;


/**
 * Represents the command to create a new Event task given the event details.
 * <p>
 * This class is responsible for calling the add event method implemented by the TaskList class,
 * given the event's name, start and end date.
 */
public class EventCommand extends Commands {
    private final TaskList tasks;
    private final String eventName;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new instance of the EventCommand class with the given TaskList and event details.
     * @param tasks The current list of tasks.
     * @param eventName The name of the new event.
     * @param startDate The start date of the new event.
     * @param endDate The end date of the new event.
     */
    public EventCommand(TaskList tasks, String eventName, LocalDate startDate, LocalDate endDate) {
        this.tasks = tasks;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String execute() throws AngusException {
        if (startDate.isAfter(endDate)) {
            throw new AngusException("End date cannot be before start date!");
        }
        if (endDate.isBefore(LocalDate.now())) {
            throw new AngusException("Event cannot end before today's date!");
        }
        return tasks.addEvent(eventName, startDate, endDate);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
