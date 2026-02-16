package bambam.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadlines which is a type of Task.
 */
public class Deadlines extends Task {
    private LocalDateTime deadline;

    private static final DateTimeFormatter DATE_TIME_FILE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadlines(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = getLocalDateTime(deadline);
    }


    @Override
    public String printTaskString() {
        String deadlineString = printLocalDateTime(deadline);
        return "[D]" + super.printTaskString() + " (by: " + deadlineString + ")";
    }

    @Override
    public String taskStorageString() {
        String deadlineString = deadline.
                format(DATE_TIME_FILE_FORMAT);
        return "D | " + super.taskStorageString() + " | " + deadlineString;
    }
}
