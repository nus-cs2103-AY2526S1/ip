package bobby.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobby.exception.BobbyException;

/**
 * abstract class that todo, deadline and event inherit from
 */
public abstract class Task {
    protected String description;
    protected boolean isMark;

    public Task(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    /**
     * used to categorise tasks
     * @return task type
     */
    public abstract int getTaskType();

    public String getDescription() {
        return this.description;
    }

    public boolean getIsMark() {
        return this.isMark;
    }

    /**
     * used for toString()
     * @return isMark status for toString()
     */
    public String getStatusIcon() {
        return (isMark ? "X" : " "); // mark done task with X
    }

    /**
     * marks task
     */
    public void mark() {
        isMark = true;
    }

    /**
     * unmarks task
     */
    public void unmark() {
        isMark = false;
    }

    /**
     * parses the given string into datetime
     * @param datetime string
     * @return LocalDateTime object
     */
    public LocalDateTime parseString(String datetime) throws BobbyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            throw new BobbyException("by/from/to must be in the format yyyy-MM-dd HHmm");
        } catch (NullPointerException e) {
            throw new BobbyException("by/from/to cannot be left empty");
        }
    }

    /**
     * converts LocalDateTime into toString() friendly format
     * @param datetime LocalDateTime object
     * @return String
     */
    public String datetimeToString(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return datetime.format(formatter);
    }

    /**
     * formats datetime to Storage friendly String format
     * @param datetime
     * @return
     */
    public String datetimeToStorage(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return datetime.format(formatter);
    }

    /**
     * converting the task to a String friendly format
     * @return String that is saved in storage
     */
    public String toStorage() {
        int i = isMark ? 1 : 0;
        return (getTaskType() + " | " + i + " | " + description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
