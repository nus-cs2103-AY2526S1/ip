
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
    private final String description;

    /**
     * Constructs a event task.
     *
     * @param description of the event task.
     */
    public EventCommand(String description) {
        super(Parser.CommandType.EVENT);

        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (description.isEmpty()) {
            throw new RafayelException("Please add in the description of the Event task.");
        }
        if (!description.contains("/from")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }
        if (!description.contains("/to")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }
        String[] taskInfo = description.substring(6).split("/");
        LocalDateTime from = handleReadDate(taskInfo[1].substring(5).trim());
        LocalDateTime to = handleReadDate(taskInfo[2].substring(3).trim());


        Event newTask = new Event(description, from, to);
        tasks.add(newTask);
        storage.save(tasks.getAll());

        return getNewTaskString(newTask, tasks.getSize());
    }
}
