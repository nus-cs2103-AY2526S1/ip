package hachiware;
//AI helped in generating javadoc comments

/**
 * Represents a command that creates and adds an {@link Event} task to the task list.
 * <p>
 * The {@code EventCommand} parses user input to extract a task description,
 * start time, and end time. The created event is added to the task list,
 * saved to persistent storage, and a confirmation message is returned.
 * </p>
 */
public class EventCommand extends Command {
    /** The description of the event. */
    private final String description;

    /** The start time of the event. */
    private final String from;

    /** The end time of the event. */
    private final String to;

    /**
     * Constructs an {@code EventCommand} by parsing the provided input string.
     * <p>
     * The input must contain a description, a start time, and an end time,
     * separated by {@code /from} and {@code /to}.
     * <br>
     * Example: {@code event project meeting /from 2025-09-21 14:00 /to 2025-09-21 16:00}
     * </p>
     *
     * @param args the raw user input containing the description, start time, and end time
     * @throws HachiwareException if the input is missing {@code /from}, {@code /to},
     *                            or a valid description
     */
    public EventCommand(String args) throws HachiwareException {
        validateArgs(args);
        this.description = parseDescription(args);
        String[] timings = parseTimings(args);
        this.from = timings[0];
        this.to = timings[1];


    }

    /**
     * Executes the event command by creating an {@link Event} with the given
     * description, start time, and end time. The event is added to the task list,
     * the updated list is saved to storage, and a confirmation message is returned.
     *
     * @param tasks   the task list to add the event to
     * @param ui      the user interface that formats the confirmation message
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message indicating the event has been added
     * @throws HachiwareException if event creation fails or saving fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "StoreFile cannot be null";

        Task task;
        try {
            task = new Event(description, from, to);
        } catch (Exception e) {
            throw new HachiwareException(e.getMessage());
        }
        tasks.add(task);
        storage.save(tasks.getAll());
        return ui.showAddTaskMessage(task, tasks.size());
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since adding an event does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Validates that the input string contains both {@code /from} and {@code /to}.
     *
     * @param args the raw user input
     * @throws HachiwareException if the input does not contain both {@code /from} and {@code /to}
     */
    private void validateArgs(String args) throws HachiwareException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new HachiwareException("MEOW! An event must have both /from and /to times.");
        }
    }

    /**
     * Extracts and validates the description from the input string.
     *
     * @param args the raw user input
     * @return the trimmed description
     * @throws HachiwareException if the description is empty
     */
    private String parseDescription(String args) throws HachiwareException {
        String[] parts = args.split("/from", 2);
        String desc = parts[0].trim();
        if (desc.isEmpty()) {
            throw new HachiwareException("MEOW! The description of an event cannot be empty.");
        }
        return desc;
    }

    /**
     * Extracts the start and end times from the input string.
     *
     * @param args the raw user input
     * @return a two-element array where index 0 is the start time and index 1 is the end time
     */
    private String[] parseTimings(String args) {
        String[] parts = args.split("/from", 2);
        return parts[1].split("/to", 2);
    }
}
