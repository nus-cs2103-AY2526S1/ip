import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    private DeadlineTask(String name, boolean isCompleted, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
        if (isCompleted) {
            markCompleted();
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String serializeTask() {
        return "D" + SAVE_DELIMITER + (isCompleted() ? "1" : "0")
                + SAVE_DELIMITER + this.encodeString(this.getName())
                + SAVE_DELIMITER + this.encodeString(Task.dateTimeToString(this.deadline));
    }

    public static DeadlineTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVE_DELIMITER, -1);
        if (taskData.length != 4) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["D", "0", "NAME", "DEADLINE"]
        String name = decodeString(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");
        LocalDateTime deadline;
        try {
            deadline = Task.parseDateTime(decodeString(taskData[3]));
        } catch (DateTimeParseException e) {
            throw new InvalidSerializedTaskDataException();
        }

        return new DeadlineTask(name, isCompleted, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.dateTimeToString(this.deadline) + ")";
    }
}
