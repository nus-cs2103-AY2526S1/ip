package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to add an event task to the task manager.
 * An event task consists of a description, start time, and end time.
 */
public class EventCommand extends Command {
    private final String description;
    private final String start;
    private final String end;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event task
     * @param start The start time of the event
     * @param end The end time of the event
     */
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return manager.addEventTask(description, start, end);
    }

    @Override
    public String getCommandWord() {
        return "event";
    }
}
