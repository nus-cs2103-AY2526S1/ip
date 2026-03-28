package ozil.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for tasks that are added by users in chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String convertToStorageFormat() {
        return String.format("X | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    public Date parseDate(String input) throws ParseException {
        String normalizedInput = normalizeInput(input);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format.parse(normalizedInput);
    }

    /**
     * Converts user input date and time to a Date object
     * @param input User input
     * @return Date
     * @throws ParseException
     */
    public Date parseDateTime(String input) throws ParseException {
        String normalizedInput = normalizeInput(input);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmm");
        format.setLenient(false);
        return format.parse(normalizedInput);
    }

    /**
     * Parses time to a Date
     * @param input Time in HHmm
     * @return Date
     * @throws ParseException
     */
    public Date parseTime(String input) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        format.setLenient(false);
        return format.parse(input);
    }

    public Date setTimeOnDate(Date date, String timeInput) throws ParseException {
        Date timeOnly = parseTime(timeInput);
        Calendar time = Calendar.getInstance();
        time.setTime(timeOnly);
        Calendar dateInput = Calendar.getInstance();
        dateInput.setTime(date);
        dateInput.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        dateInput.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        return dateInput.getTime();
    }

    private String normalizeInput(String input) {
        input = input.trim();
        if (input.length() == 10) {
            return input + " 0000";
        }
        return input;
    }

    /**
     * Returns whether the task has a proper Date object to store its time or not
     * @return Boolean
     */
    public boolean hasDate() {
        return false;
    }

    /**
     * Return task date or time if any
     * @return Date object
     */
    public Date getTaskDate() {
        return null;
    }

}
