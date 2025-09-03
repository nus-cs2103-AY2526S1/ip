package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Event;
import bytebot.task.Task;
import bytebot.ui.Ui;

/**
 * Adds an Event with a start and end time.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an event command
     *
     * @param description Description of the event
     * @param from Start time in input format (d/M/yyyy HHmm)
     * @param to End time in input format (d/M/yyyy HHmm)
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Validates description, from and to, creates the event, and shows feedback.
     */
    @Override
    public String execute(Ui ui, Storage storage) throws ByteException {
        if (description == null || description.trim().isEmpty()) {
            throw new ByteException("The description of an event cant be empty");
        }
        if (from == null || from.trim().isEmpty()) {
            throw new ByteException("The /from time of an event cant be empty");
        }
        if (to == null || to.trim().isEmpty()) {
            throw new ByteException("The /to time of an event cant be empty");
        }
        Task task = new Event(description, from, to);
        storage.addTask(task);
        return ui.showAddedTask(task, storage.getSize());
    }
}


