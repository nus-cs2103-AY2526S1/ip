package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.DeadLine;

/**
 * A specific implementation of the {@link Command} class that creates a new {@link DeadLine} task
 * and adds it to the storage.
 * <p />
 * The {@code CreateDeadLine} class encapsulates the creation and saving of a deadline task.
 * It includes methods to execute the task addition and to parse input for creating the deadline task.
 */
public class CreateDeadLine extends Command {
    private final DeadLine deadline;

    /**
     * Constructs a new {@code CreateDeadLine} command with the specified {@link DeadLine} task.
     * The {@link DeadLine} task encapsulates the description and deadline details of a task.
     *
     * @param deadline the {@link DeadLine} task to be created and managed by this command
     */
    private CreateDeadLine(DeadLine deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the addition of a new {@link DeadLine} task to the storage and returns a confirmation message.
     *
     * @return a string message confirming the addition of the {@link DeadLine} task, including its details
     */
    @Override
    public String execute() {
        Storage.getInstance().addTask(this.deadline);
        return "Added:\n" + deadline.toString();
    }

    /**
     * Creates a new {@code CreateDeadLine} command instance from the given parts array.
     * Validates that the array contains exactly two elements, representing
     * the task details and deadline. If validation passes, a new {@code CreateDeadLine}
     * object is instantiated with a {@link DeadLine} task created from the parsed deadline details.
     *
     * @param parts an array of strings where:
     *              parts[0] is expected to be the command keyword "deadline"
     *              parts[1] contains the task description and deadline separated by "/by"
     * @return a new {@code CreateDeadLine} instance representing the command
     * @throws IllegalArgumentException if the array does not contain exactly two elements
     */
    public static CreateDeadLine of(String[] parts) {
        assert(parts[0].equals("deadline"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(DeadLine.ERROR_MESSAGE);
        }

        return new CreateDeadLine(DeadLine.of(parts[1]));

    }
}
