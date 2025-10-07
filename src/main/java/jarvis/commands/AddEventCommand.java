package jarvis.commands;

import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasks.EventsTask;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command to add an event task to the task list.
 * An event task has a description, a start date/time, and an end date/time.
 */
public class AddEventCommand extends Command {
    private final String desc;
    private final LocalDateTime from;
    private final LocalDateTime to;

    // Message to return to the GUI after execute()
    private String message;

    /**
     * Constructs an {@code AddEventCommand}.
     *
     * @param desc Description of the event task.
     * @param from String representation of the start date and time (parsed via {@link Parser#parseDateTime(String)}).
     * @param to   String representation of the end date and time (parsed via {@link Parser#parseDateTime(String)}).
     */
    public AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
        assert this.from != null : "Parser should not return null for valid starting date/time";
        assert this.to != null : "Parser should not return null for valid ending date/time";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {

        if (desc == null || desc.isBlank()) {
            throw new DarrenAssistantException("Event needs a description!");
        }
        if (from == null || to == null) {
            throw new DarrenAssistantException("Please provide a valid start and end date/time.");
        }
        if (from.isAfter(to)) {
            throw new DarrenAssistantException("Start date/time must be before or equal to end date/time.");
        }

        Task t = new EventsTask(desc, from, to);
        tasks.add(t);

        // Build the message (Ui should return strings, not print)
        // e.g., in Ui: public String formatAdded(Task t, int total) { ... }
        message = ui.formatAdded(t, tasks.size());

        // Persist
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        return message != null ? message
                : "Added event: " + desc + " (" + from + " to " + to + ")";
    }
}
