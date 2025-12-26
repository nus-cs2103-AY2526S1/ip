package gene.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static LocalDateTime dateTimeParser(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(s, formatter);
    }

    public String dateTimeToString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy HHmm");
        return dt.format(formatter);
    }

    public String dateTimeToDbString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dt.format(formatter);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Required for  find command
     *
     * @param keyword user inputs
     * @return if it contains the keyword
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Return a different String representation to be stored in .txt file
     * This string representation is easier to be parsed and recreate the
     * task when the program runs initially
     *
     * @return string to be stored in .txt file
     */
    abstract public String toDbString();

    /**
     * Check if a reminder is needed for this task at the given date time
     *
     * @param dt the date time to be checked against
     * @return true if a reminder is needed, false otherwise
     */
    abstract public boolean isReminderNeeded(LocalDateTime dt);

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}