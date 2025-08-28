package beebong.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import beebong.exception.InvalidSerializedTaskDataException;
import beebong.util.DateTimeUtil;
import beebong.util.StringUtil;

/**
 * Represents a task that occurs within a specific time range (start and end).
 */
public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Creates a new incomplete event task.
     *
     * @param name      the name of the task.
     * @param startDate the start time of the event.
     * @param endDate   the end time of the event.
     */
    public EventTask(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private EventTask(String name, boolean isCompleted, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        if (isCompleted) {
            markCompleted();
        }
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serializeTask() {
        return "E" + SAVE_DELIMITER + super.serializeTask()
                + SAVE_DELIMITER + StringUtil.encode(DateTimeUtil.toSerializedString(this.startDate))
                + SAVE_DELIMITER + StringUtil.encode(DateTimeUtil.toSerializedString(this.endDate));
    }

    /**
     * Deserializes a string back into an {@link EventTask}.
     *
     * @param taskStr the serialized task string.
     * @return the corresponding {@link EventTask} object.
     * @throws InvalidSerializedTaskDataException If taskStr is invalid.
     */
    public static EventTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVE_DELIMITER, -1);
        if (taskData.length != 5) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["E", "0", "NAME", "START", "END]
        String name = StringUtil.decode(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");
        LocalDateTime startDate, endDate;
        try {
            startDate = DateTimeUtil.parseDateTime(StringUtil.decode(taskData[3]));
            endDate = DateTimeUtil.parseDateTime(StringUtil.decode(taskData[4]));
        } catch (DateTimeParseException e) {
            throw new InvalidSerializedTaskDataException();
        }

        return new EventTask(name, isCompleted, startDate, endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + DateTimeUtil.toString(this.startDate) + " to "
                + DateTimeUtil.toString(this.endDate) + ")";
    }
}
