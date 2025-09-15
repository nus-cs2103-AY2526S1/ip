
package rafayel.command;

import java.time.LocalDateTime;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Event;
import rafayel.task.TaskList;

/**
 * Handles the creation and addition of a new Event task.
 */
public class EventCommand extends Command {
    private final String descriptionDate;
    private static final String EVENT_FORMAT_ERROR = "Event format is wrong. Example: event [desc] /from [time] /to [time]";

    /**
     * Constructs a event task.
     *
     * @param descriptionDate of the event task.
     */
    public EventCommand(String descriptionDate) {
        super(CommandHandle.CommandType.EVENT);

        this.descriptionDate = descriptionDate;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (descriptionDate.isEmpty()) {
            throw new RafayelException(EVENT_FORMAT_ERROR + "Please add in the description of the Event task.");
        }
        if (!descriptionDate.contains("/from")) {
            throw new RafayelException(EVENT_FORMAT_ERROR);
        }
        if (!descriptionDate.contains("/to")) {
            throw new RafayelException(EVENT_FORMAT_ERROR);
        }
        String[] taskInfo = descriptionDate.substring(6).split("/");
        String description = taskInfo[0].trim();
        LocalDateTime from = handleReadDate(taskInfo[1].substring(5).trim());
        LocalDateTime to = handleReadDate(taskInfo[2].substring(3).trim());

        Event newTask = new Event(description, from, to);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
