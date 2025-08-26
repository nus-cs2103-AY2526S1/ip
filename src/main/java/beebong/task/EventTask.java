package beebong.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import beebong.exception.InvalidSerializedTaskDataException;

public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

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

    @Override
    public String serializeTask() {
        return "E" + SAVE_DELIMITER + (isCompleted() ? "1" : "0")
                + SAVE_DELIMITER + this.encodeString(this.getName())
                + SAVE_DELIMITER + this.encodeString(Task.dateTimeToString(this.startDate))
                + SAVE_DELIMITER + this.encodeString(Task.dateTimeToString(this.endDate));
    }

    public static EventTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVE_DELIMITER, -1);
        if (taskData.length != 5) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["E", "0", "NAME", "START", "END]
        String name = decodeString(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");
        LocalDateTime startDate, endDate;
        try {
            startDate = Task.parseDateTime(decodeString(taskData[3]));
            endDate = Task.parseDateTime(decodeString(taskData[4]));
        } catch (DateTimeParseException e) {
            throw new InvalidSerializedTaskDataException();
        }

        return new EventTask(name, isCompleted, startDate, endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.dateTimeToString(this.startDate) + " to " + Task.dateTimeToString(this.endDate) + ")";
    }
}
