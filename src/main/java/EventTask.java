import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public EventTask(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private EventTask(String name, boolean isCompleted, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        if (isCompleted) {
            markCompleted();
        }
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String serializeTask() {
        return "E" + SAVE_DELIMITER + (isCompleted() ? "1" : "0")
                + SAVE_DELIMITER + this.encodeString(this.getName())
                + SAVE_DELIMITER + this.encodeString(Task.dateToString(this.startDate))
                + SAVE_DELIMITER + this.encodeString(Task.dateToString(this.endDate));
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
        LocalDate startDate, endDate;
        try {
            startDate = Task.parseDate(decodeString(taskData[3]));
            endDate = Task.parseDate(decodeString(taskData[4]));
        } catch (DateTimeParseException e) {
            throw new InvalidSerializedTaskDataException();
        }

        return new EventTask(name, isCompleted, startDate, endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.dateToString(this.startDate) + " to " + Task.dateToString(this.endDate) + ")";
    }
}
