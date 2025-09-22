package moon.commands;

import moon.commands.enums.Command;
import moon.models.Event;
import moon.ui.UiMessages;

/**
 * Command to add an {@link Event} task to the task list.
 */
public class AddEventCommand extends AddCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.EVENT;

    /** The event task to be added. */
    public final Event event;

    /**
     * Creates a new AddEventCommand.
     *
     * @param event The event task to add
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds the event to the task list and shows a confirmation message.
     *
     * @return confirmation message to be displayed to the user
     */
    @Override
    public String execute() {
        addToStorage(event);
        return UiMessages.showAddTaskMessage(event);
    }
}
