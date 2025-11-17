package pagrobot.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline. A Deadline has a description and a
 * specific due date/time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline with the given name and deadline.
     *
     * @param name the description of the deadline task.
     * @param to the deadline as a string.
     */
    public Deadline(String name, String by) {
        this(name, by, false);
    }

    /**
     * Constructs a new Deadline with the given name, deadline, and
     * status.
     *
     * @param name the description of the deadline task.
     * @param to the deadline as a string.
     * @param isDone whether the task is completed.
     */
    public Deadline(String name, String to, boolean isDone) {
        super(name, isDone);
        this.by = super.parseDate(to);
    }

    /**
     * Returns the string representation of this Deadline.
     *
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", by.format(outputFormatter));
    }

    /**
     * Creates a Deadline from its saved memory representation.
     *
     * @param input the serialized string.
     * @return the reconstructed deadline task.
     */
    public static Task fromMemory(String input) {
        String[] parts = input.split("\\|");
        return new Deadline(parts[2], parts[3], parts[1].equals("1"));
    }

    /**
     * Returns the string representation of this Deadline for saving to
     * memory.
     *
     * @return the memory string of this deadline.
     */
    @Override
    public String toMemory() {
        return String.format("D|%d|%s|%s", super.isDone() ? 1 : 0, super.getName(), this.by);
    }

    /**
     * Returns the deadline date and time of this task.
     *
     * @return the deadline as a LocalDateTime.
     */
    public LocalDateTime getDeadline() {
        return this.by;
    }

    public static String inputArgument() {
        return "deadline <task name> /by <date: dd/mm/yyyy> <time: HHmm>";
    }
}
