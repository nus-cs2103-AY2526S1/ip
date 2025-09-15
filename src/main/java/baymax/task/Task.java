package baymax.task;

import java.time.LocalDate;

/**
 * Base type for all tasks tracked by Baymax.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns the completion marker used in string renderings.
     *
     * @return {@code "X"} if done, otherwise a single space {@code " "}.
     */
    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.description);
    }

    /**
     * Marks this task as completed and returns a confirmation message.
     *
     * @return A confirmation string including the task.
     */
    public String mark() {
        if (this.isDone) {
            return "This task is already completed. There is no further action required.";
        }
        this.isDone = true;

        return """
                Task complete. You are doing a wonderful job.
                \t""" + this;
    }

    /**
     * Unmarks this task (sets it to not completed) and returns a confirmation message.
     *
     * @return A confirmation string including the task.
     */
    public String unmark() {
        if (!this.isDone) {
            return "This task is already unmarked. No further changes are necessary.";
        }
        this.isDone = false;

        return """
                I have unmarked this task.
                \t""" + this;
    }


    /**
     * Parses a line into a concrete {@link Task} instance.
     * <p>
     * This method expects the exact wire format produced by {@link #toSaveFormat()} in
     * the subclasses ({@code ToDo}, {@code Deadline}, {@code Event}).
     * </p>
     *
     * @param line The saved task line (as produced by {@code toSaveFormat()}).
     * @return A concrete {@link Task} reconstructed from the line.
     */
    public static Task toTaskFormat(String line) {
        String[] parts = line.split(" \\| ");

        String taskType = parts[0];
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new ToDo(isDone, description);
        case "D":
            LocalDate deadline = LocalDate.parse(parts[3]);
            return new Deadline(isDone, description, deadline);
        case "E":
            String startTime = parts[3];
            String endTime = parts[4];
            return new Event(isDone, description, startTime, endTime);
        default:
            return new ToDo(false, "test");
        }
    }

    /**
     * Converts this task into a compact, line-oriented string format suitable for storage.
     * <p>
     * The format is pipe-separated with single spaces around the delimiter:
     * {@code "<TYPE> | <isDone> | <description> | <extra...>"}, where:
     * </p>
     * <ul>
     *   <li>{@code <TYPE>} is one of:
     *     <ul>
     *       <li>{@code T} — To-do</li>
     *       <li>{@code D} — Deadline</li>
     *       <li>{@code E} — Event</li>
     *     </ul>
     *   </li>
     *   <li>{@code <isDone>} is a boolean literal ({@code true} or {@code false}).</li>
     *   <li>{@code <description>} is the raw task description.</li>
     *   <li>{@code <extra...>} depends on {@code <TYPE>}:
     *     <ul>
     *       <li>{@code <deadline>} in date format ({@code yyyy-MM-dd}).</li>
     *       <li>{@code <start>} and {@code <end>} as provided by the user.</li>
     *     </ul>
     *   </li>
     * </ul>
     * <p>
     * Examples:
     * </p>
     * <pre>
     * T | false | buy milk
     * D | true  | submit report | 2025-09-01
     * E | false | team sync     | 10am | 11am
     * </pre>
     * <p>
     * The output of this method must be consumable by {@link #toTaskFormat(String)}.
     * Subclasses override this method to append their specific fields.
     * </p>
     *
     * @return A storage-friendly string representation of this task.
     */

    public abstract String toSaveFormat();
}
