package hachiware;

/**
 * Represents a command that sets the priority of a task in the task list.
 * <p>
 * The {@code PriorityCommand} updates the priority level of the specified task,
 * saves the updated task list to storage, and returns a confirmation message.
 * </p>
 */
public class PriorityCommand extends Command {
    /** The zero-based index of the task whose priority is being set. */
    private final int taskIndex;

    /** The priority level to assign to the task. */
    private final Priority priority;

    /**
     * Constructs a {@code PriorityCommand} by parsing the user input.
     * <p>
     * The input should contain a task number (1-based) followed by a priority level.
     * Example: {@code priority 2 HIGH}.
     * </p>
     *
     * @param args the user input containing the task number and priority level
     * @throws HachiwareException if the input format is invalid, the task number is not an integer,
     *                            or the priority level is invalid
     */
    public PriorityCommand(String args) throws HachiwareException {
        int[] parsed = parseArgs(args);
        this.taskIndex = parsed[0];
        this.priority = Priority.values()[parsed[1]];
    }

    /**
     * Executes the priority command by setting the specified task's priority,
     * saving the updated task list, and returning a confirmation message.
     *
     * @param tasks   the task list containing the task to update
     * @param ui      the user interface (unused in this command)
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message showing the new priority and task details
     * @throws HachiwareException if the task index is out of bounds or saving fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new HachiwareException("MEOW! Task number out of bounds");
        }
        Task task = tasks.get(taskIndex);
        task.setPriority(priority);
        storage.save(tasks.getAll());
        return "Priority set to " + priority + " for task:\n" + task;
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since setting task priority does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input to extract the task index and priority ordinal.
     *
     * @param args the raw user input containing task number and priority
     * @return an array where index 0 is the zero-based task index and index 1 is the priority ordinal
     * @throws HachiwareException if the input format is invalid, the task number is not an integer,
     *                            or the priority level is invalid
     */
    private int[] parseArgs(String args) throws HachiwareException {
        String[] parts = args.trim().split(" ", 2);
        if (parts.length < 2) {
            throw new HachiwareException("Usage: priority <task number> <HIGH|MEDIUM|LOW>");
        }

        int taskIdx;
        try {
            taskIdx = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new HachiwareException("MEOW! Invalid task number.");
        }

        int priorityOrdinal;
        try {
            Priority parsedPriority = Priority.valueOf(parts[1].trim().toUpperCase());
            priorityOrdinal = parsedPriority.ordinal();
        } catch (IllegalArgumentException e) {
            throw new HachiwareException("MEOW! Invalid priority level. Use HIGH, MEDIUM, or LOW.");
        }

        return new int[]{taskIdx, priorityOrdinal};
    }
}
