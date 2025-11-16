package tuesday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *  Implementation of DeadlineTask Class
 */
public class DeadlineTask extends Task {
    private final String OUTPUT_FORMAT_PATTERN = "dd MMM yyyy - h:mma" ;
    private final String INPUT_FORMAT_PATTERN = "dd-MM-yyyy HHmm";
    private LocalDateTime deadlineTime;

    private final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern(OUTPUT_FORMAT_PATTERN);
    private final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(INPUT_FORMAT_PATTERN);


    /**
     * Construct Deadline task with a description, deadline
     * @param description
     * @param deadline
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadlineTime = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    /**
     * Return the String of type
     * @return 'D"
     */
    public String getType() {
        return "D";
    }

    /**
     * Return the detailed deadline time format for saving data to storage
     * @return @return format: dd-MM-yyyy HHmm
     */
    public String getTime() {
        return this.deadlineTime.format(INPUT_FORMATTER);
    }

    /**
     * Return the decorated deadline time format for display in terminal
     * @return format: dd MMM yyyy - h:mma
     */
    public String getDeadlineTime() {
        return this.deadlineTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Return TaskType
     * @return
     */
    @Override
    public TaskEnums.TaskType getTaskType() {
        return TaskEnums.TaskType.DEADLINE;
    }

    /**
     * Return the LocalDateTime
     * @return
     */
    @Override
    public LocalDateTime getLDTTime() {
        return this.deadlineTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (BY %s)",
                this.isDone() ? "X" : " ",
                this.getDescription(),
                this.getDeadlineTime());

    }
}
