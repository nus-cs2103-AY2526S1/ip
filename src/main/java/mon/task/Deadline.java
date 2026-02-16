package mon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a new deadline task with the specified name, status, and deadline.
     * 
     * @param taskName the name of the task
     * @param status the completion status of the task
     * @param deadline the deadline date in yyyy-MM-dd format
     * @throws IllegalArgumentException if the date format is invalid
     */
    public Deadline(String taskName, boolean status, String deadline) {
        super(taskName, status);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    /**
     * Creates a new incomplete deadline task with the specified name and deadline.
     * 
     * @param taskName the name of the task
     * @param deadline the deadline date in yyyy-MM-dd format
     */
    public Deadline(String taskName, String deadline) {
        this(taskName, false, deadline);
    }

    /**
     * Returns the deadline date of this task.
     * 
     * @return the deadline date
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Creates a deadline task from a string representation.
     * 
     * @param taskString the string representation of the deadline task
     * @return the deadline task object
     * @throws IllegalArgumentException if the task format is invalid
     */
    public static Deadline toDeadlineTask(String taskString) {
        try {
            String[] parts = taskString.split(" \\| ", 4);
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format in file");
            }
            // Convert the file format date to standard format using the helper function
            String standardFormatDate = convertFileFormatToStandardDate(parts[3]);
            return new Deadline(parts[2], parts[1].equals("1"), standardFormatDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing deadline from file: " + e.getMessage());
        }
    }

    @Override
    public String toFileString() {
        String standardDate = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fileFormatDate = convertStandardToFileFormatDate(standardDate);
        return "D | " + super.toFileString() + " | " + fileFormatDate;
    }

    @Override
    public String toString() {
        String standardDate = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String displayDate = convertStandardToFileFormatDate(standardDate);
        return "[D]" + super.toString() + " (by: " + displayDate + ")";
    }
}
