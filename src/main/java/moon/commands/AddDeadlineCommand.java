package moon.commands;

import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.ui.UiMessages;

/**
 * Command to add a {@link Deadline} task to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.DEADLINE;

    /** The deadline task to be added. */
    public final Deadline deadline;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param deadline The deadline task to add
     */
    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds the deadline to the task list and shows a confirmation message.
     *
     * @return confirmation message to be displayed to the user
     */
    @Override
    public String execute() {
        addToStorage(deadline);
        return UiMessages.showAddTaskMessage(deadline);
    }
}
