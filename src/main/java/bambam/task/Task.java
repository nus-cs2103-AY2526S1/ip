package bambam.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task object with its description and isTaskDone status.
 */
public class Task {
    private final String taskDescription;
    private boolean isTaskDone;

    private static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    private static final DateTimeFormatter DATE_OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    /**
     * Returns dates and times of Task objects in LocalDateTime.
     * If only a date is provided, the time is set to the start of the day.
     *
     * @param dateTime The LocalDateTime of date and time provided by users.
     * @return The date and time provided by users in string in LocalDateTime.
     */
    public LocalDateTime getLocalDateTime(String dateTime) {
        assert (dateTime != null && !dateTime.isEmpty()) :
                "dateTime cannot be null or empty";

        try {
            String[] dateTimeDetails = dateTime.split(" ", 2);
            if (dateTimeDetails.length == 2) {
                return LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMATTER);
            } else {
                LocalDate deadlineDate = LocalDate.parse(dateTime, DATE_INPUT_FORMATTER);
                return deadlineDate.atStartOfDay();
            }
        } catch (DateTimeParseException e) { // ChatGPT enhanced to include catch statements
            throw new IllegalArgumentException(
                    "Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns dates and times of Task objects in LocalDateTime as Strings.
     * If the time is exactly midnight, only the date is returned.
     *
     * @param dateTime The string of date and time provided by users.
     * @return The string of date and time in LocalDateTime.
     */
    public String printLocalDateTime(LocalDateTime dateTime) {
        if (dateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return dateTime.format(DATE_OUTPUT_FORMAT);
        } else {
            return dateTime.format(DATE_TIME_OUTPUT_FORMAT);
        }
    }

    /**
     * Returns String to print depending on whether the Task object is done.
     * @return The string of done or undone.
     */
    public String getStatusIcon() {
        return (isTaskDone ? "X" : " ");
    }

    /**
     * Returns isTaskDone boolean.
     * @return The isTaskDone boolean.
     */
    public boolean getIsDone() {
        return isTaskDone;
    }

    /**
     * Returns taskDescription string.
     * @return The string of the task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the Task String to be printed.
     * @return The string of a task to be printed in the list.
     */
    public String printTaskString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }

    /**
     * Handles the marking of Task object as done.
     */
    public void markAsDone() {
        isTaskDone = true;
    }

    /**
     * Handles the marking of Task object as undone.
     */
    public void markAsUndone() {
        isTaskDone = false;
    }

    /**
     * Returns the string of the Task object ot be saved in the hard disk.
     * @return The string of a task to be stored in the task storage.
     */
    public String taskStorageString() {
        return (isTaskDone ? "Done" : "Not Done") + " | " + taskDescription;
    }

}
