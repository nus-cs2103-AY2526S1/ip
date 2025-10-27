package model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a Event Task
 * Each task contains both starting time and ending time
 */
public class Event extends Task{
    private String start;
    private String end;

    /**
     * Constructs a Event task
     *
     * @param description   A short description of the task
     * @param start         The time task start
     * @param end           The time task end
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type of event task
     *
     * @return type represent as string "E"
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns starting time of task
     *
     * @return the starting time
     */
    public String getStart() {
        return start;
    }


    /**
     * Returns ending time of task
     *
     * @return the ending time
     */
    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + getStatus() + " " + description + " (from: " + start + " to:" + end + ")";
    }
}
