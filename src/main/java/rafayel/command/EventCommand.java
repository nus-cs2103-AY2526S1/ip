
package rafayel.command;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Event;
import rafayel.task.TaskList;

/**
 * Represents a command that creates and adds a new Event task.
 * The event requires a description, start time (/from) and end time (/to).
 */
public class EventCommand extends Command {

    /** Error message when event format is invalid. */
    private static final String EVENT_FORMAT_ERROR = "An event must be set with 'event [desc] /from [time] /to [time]'."
            + " \nThis isn't abstract art — precision is key!";

    /* Stores the description and date of the Event task. */
    private final String descriptionDate;


    /**
     * Constructs an event task.
     *
     * @param descriptionDate of the event task.
     */
    public EventCommand(String descriptionDate) {
        super(CommandHandle.CommandType.EVENT);

        this.descriptionDate = descriptionDate;
    }

    /**
     * Checks the input for event task.
     *
     * @param descriptionDate the input for event task.
     * @throws RafayelException if the input is formatted wrongly.
     */
    private void eventInputValidation(String descriptionDate) throws RafayelException {
        // Input Validation
        if (descriptionDate.isEmpty()) {
            throw new RafayelException(
                    "A blank canvas? How am I supposed to add an event with no description? "
                            + "Tell me what this event is for. "
                            + EVENT_FORMAT_ERROR);
        }
        if (!descriptionDate.contains("/from")) {
            throw new RafayelException(EVENT_FORMAT_ERROR);
        }
        if (!descriptionDate.contains("/to")) {
            throw new RafayelException(EVENT_FORMAT_ERROR);
        }

    }

    /**
     * Executes the event command by creating an Event task.
     *
     * @param tasks the current task list.
     * @param storage the storage handler.
     * @return confirmation message after adding the task.
     * @throws RafayelException if input format is invalid or parsing fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        eventInputValidation(descriptionDate);

        String[] taskInfo = descriptionDate.substring(6).split("/");
        String description = taskInfo[0].trim();
        LocalDateTime from = handleReadDate(taskInfo[1].substring(5).trim());
        LocalDateTime to = handleReadDate(taskInfo[2].substring(3).trim());

        // Check from to hours
        long hoursBetweenFromTo = ChronoUnit.HOURS.between(from, to);
        if (hoursBetweenFromTo < 0) {
            throw new RafayelException("Invalid time period :< 'To' should be after 'From' date.");
        }

        Event newTask = new Event(description, from, to);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
