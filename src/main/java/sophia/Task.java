package sophia;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Represents an abstract task with a name and completion status.
 * <p>
 * Subclasses of {@code Task} must implement {@link #write(BufferedWriter)}
 * to define how the task is serialized or saved externally.
 * </p>
 */
public abstract class Task {
    private final String name;
    private boolean isCompleted = false;
    /**
     * Constructs a {@code Task} with the given name.
     *
     * @param name the name or description of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Validates whether the given string matches the ISO date format {@code yyyy-MM-dd}.
     *
     * @param str the date string to validate
     * @return {@code true} if the string matches the expected format, {@code false} otherwise
     */
    private static boolean validateDateFormat(String str) {
        return (Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$")).matcher(str).matches();
    }
    /**
     * Parses a date string from {@code yyyy-MM-dd} format into a more user-friendly format
     * {@code MMM d yyyy} (e.g., {@code 2025-08-25 → Aug 25 2025}).
     * <p>
     * If the input string does not match the expected date format, it is returned unchanged.
     * </p>
     *
     * @param date the date string to parse
     * @return a formatted date string, or the original string if it is not in {@code yyyy-MM-dd} format
     */
    public String parseDate(String date) {
        if (!validateDateFormat(date)) {
            return date;
        }
        LocalDate localDate = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the name of the task.
     *
     * @return the task's name
     */
    public String getName() {
        return this.name;
    }


    /**
     * Writes the task's data to the specified {@link BufferedWriter}.
     * <p>
     * Subclasses should define the format and details of the output.
     * </p>
     *
     * @param bw the {@code BufferedWriter} to write the task data to
     * @throws IOException if an I/O error occurs while writing
     */
    public abstract void write(BufferedWriter bw) throws IOException;

    /**
     * Returns a string representation of the task.
     * <p>
     * Completed tasks are prefixed with {@code [X]}, while incomplete tasks
     * are prefixed with {@code [ ]}.
     * </p>
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Sets the completion status of this task.
     *
     * @param done {@code true} if the task is completed, {@code false} otherwise
     */
    public void setDone(boolean done) {
        this.isCompleted = done;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise
     */
    public boolean isDone() {
        return this.isCompleted;
    }
}
