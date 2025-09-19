package eureka.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with a name and completion status.
 * This is the base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Creates a new Task with the specified name and completion status.
     *
     * @param taskName the name/description of the task
     * @param isDone whether the task is marked as completed
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    /**
     * Converts the task to a serialized string format for storage.
     *
     * @return a string representation of the task suitable for file storage
     */
    public String serialise() {
        if (this.isDone) {
            return "1" + " | " + this.taskName;
        } else {
            return "0" + " | " + this.taskName;
        }
    }

    /**
     * Parses a serialized task string and creates the appropriate Task subclass.
     *
     * @param line the serialized task string to parse
     * @return a Task object of the appropriate subtype (Todo, Deadline, or Event)
     * @throws IllegalArgumentException if the task type is unknown
     */
    public static Task parse(String line) {
        String[] parts = line.split("\\s*\\|\\s*"); // split according to |

        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String taskName = parts[2];

        switch (type) {
        case "T":
            return new Todo(taskName, isDone);
        case "D":
            return new Deadline(LocalDateTime.parse(parts[3]), taskName, isDone);
        case "E":
            return new Event(LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]), taskName, isDone);
        default:
            throw new IllegalArgumentException("Unknown type: " + parts[0]);
        }
    }

    /**
     * Formats a LocalDateTime object into a human-readable string.
     *
     * @param dateTime the LocalDateTime to format
     * @return a formatted date-time string in "EEEE, MMMM dd yyyy HH:mm" format
     */
    public String dateTimeFormatter(LocalDateTime dateTime) {
        DateTimeFormatter custom1 = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy HH:mm");
        return dateTime.format(custom1);
    }

    /**
     * Checks if the task name contains the specified keyword (case-insensitive).
     *
     * @param keyword the keyword to search for
     * @return true if the task name contains the keyword, false otherwise
     */
    public boolean contains(String keyword) {
        return taskName.toLowerCase().contains(keyword.toLowerCase());
    }
}
