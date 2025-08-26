package beebong.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;

import beebong.exception.InvalidSerializedTaskDataException;

public abstract class Task {
    private String name;
    private boolean completed;
    protected static String SAVE_DELIMITER = " ";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    // SerialiseTask declared abstract for ensure child classes implement it,
    // but deserializeTask cannot be declared as an abstract method here
    // due to its nature of being static
    public abstract String serializeTask();

    public static Task deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // Each instance of deserializeTask in the child classes
        // also throw InvalidSeralizedTaskDataException
        // If any of them throw it here, just let the callee
        // of this method handle it
        if (taskStr.startsWith("T")) {
            return ToDoTask.deserializeTask(taskStr);
        } else if (taskStr.startsWith("D")) {
            return DeadlineTask.deserializeTask(taskStr);
        } else if (taskStr.startsWith("E")) {
            return EventTask.deserializeTask(taskStr);
        }
        throw new InvalidSerializedTaskDataException();
    }

    // Idea taken from chatGPT on how to safely design my serialization of strings
    protected String encodeString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    protected static String decodeString(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    // Updated methods to be more flexible with Date and Time
    public static LocalDateTime parseDateTime(String dateStr) {
        // In order to make the method flexible we need to
        // try parsing it as a LocalDateTime first, if not try LocalDate
        try {
            return LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(dateStr, DATE_FORMATTER).atStartOfDay(); // Default 00:00
        }
    }

    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Check if time is 00:00
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            // Only Date
            return dateTime.toLocalDate().format(formatterD);
        } else {
            // Date and Time
            return dateTime.format(formatterDT);
        }
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " " ) + "] " + this.name;
    }
}
