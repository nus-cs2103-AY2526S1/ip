package Coffee;

/**
 * Represents a command to add an event task to the task list.
 * The command parses the user input for the description, start time, and end time,
 * creates an {@code Event}, saves it, and displays confirmation.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an {@code EventCommand} by parsing the given arguments.
     * The arguments are split into description, start, and end times using
     * the delimiters {@code /from} and {@code /to}.
     *
     * @param args User input string containing the description, start, and end times.
     * @throws IllegalArgumentException If the command format is invalid.
     */
    public EventCommand(String args) {
        String[] fromSplit = args.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new IllegalArgumentException("The event command format is wrong. " +
                    "Use: event <desc> /from <time> /to <time>");
        }

        this.description = fromSplit[0].trim();

        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new IllegalArgumentException("The event command format is wrong. " +
                    "Use: event <desc> /from <time> /to <time>");
        }

        this.from = toSplit[0].trim();
        this.to = toSplit[1].trim();
    }

    /**
     * Executes the command by creating an {@code Event} and adding it to the task list.
     * Saves the updated list to storage and displays confirmation messages to the user.
     *
     * @param tasks Task list where the new event will be added.
     * @param ui User interface for displaying messages.
     * @param storage Storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            CommandUtil.addEventSaveAndAck(description, from, to, tasks, ui, storage);
        } catch (Exception e) {
            ui.showMessage("Error: Please enter both start and end date/time in the format yyyy-MM-dd HHmm " +
                    "(e.g., 2024-06-10 1800).");
        }
    }
}
