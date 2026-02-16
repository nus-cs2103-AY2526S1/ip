package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.Event;

/**
 * Represents a command to create an event task in the system.
 * The {@code CreateEvent} command encapsulates the creation of an event
 * task and its addition to the storage. It inherits functionality from
 * the {@link Command} class and implements specific behavior for execution.
 */
public class CreateEvent extends Command {
    private final Event event;

    /**
     * Constructs a new {@code CreateEvent} command with the specified event.
     *
     * @param event the {@link Event} task to be created and added to the system
     */
    private CreateEvent(Event event) {
        this.event = event;
    }

    /**
     * Executes the command, adding the associated event to the storage and returning
     * a confirmation message with the event details.
     *
     * @return a {@code String} message confirming the event addition, including the details of the event
     */
    @Override
    public String execute() {
        Storage.getInstance().addTask(this.event);
        return "Added:\n" + event.toString();
    }

    /**
     * Creates a new instance of {@link CreateEvent} using the provided array of input parts.
     * The input array is expected to contain a command keyword and the event description with
     * start and end times. Validates the input to ensure it has exactly two elements.
     *
     * @param parts an array where:
     *              - {@code parts[0]} is the command keyword (e.g., "event")
     *              - {@code parts[1]} contains the event description and time details in the
     *                expected format "taskName /from &ltfrom&gt /to &ltto&gt"
     * @return a new {@code CreateEvent} instance that encapsulates the {@link Event} task created
     *         from the description and time details
     * @throws IllegalArgumentException if the input array does not contain exactly two elements
     */
    public static CreateEvent of(String[] parts) {
        assert(parts[0].equals("event"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(Event.ERROR_MESSAGE);
        }
        return new CreateEvent(Event.of(parts[1]));
    }
}
