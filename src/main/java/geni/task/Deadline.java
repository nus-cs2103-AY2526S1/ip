package geni.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import geni.exception.GeniException;


/**
 * Represents a task with a specific deadline.
 * Stores the description and the date-time by which it must be done.
 */


public class Deadline extends Task {
    protected LocalDateTime by;
    /**
     * Creates a {@code Deadline} task.
     *
     * @param description task description
     * @param time        deadline in format "yyyy-MM-dd HHmm"
     * @throws GeniException if the date-time format is invalid
     */
    public Deadline(String description, String time) throws GeniException {
        super(description);
        assert time != null && !time.trim().isEmpty() : "Deadline.time must be non-null and not empty";
        try {
            DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.by = LocalDateTime.parse(time.trim(), inputFmt);



        } catch (DateTimeParseException e) {
            throw new GeniException("Invalid date-time format! Please use format: yyyy-MM-dd HHmm");
        }
    }
    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(outputFmt) + ")";
    }
    /**
     * Returns a string representation suitable for saving to a file.
     *
     * @return formatted save string
     */
    @Override
    public String toSaveFormat() {

        DateTimeFormatter saveFmt = DateTimeFormatter.ofPattern("yyy-MM-dd HHmm");
        return "D | " + (super.isDone)
                + " | " + super.getDescription() + " | " + by.format(saveFmt);
    }
    public LocalDateTime getDeadline() {
        return by; }

    @Override
    public List<LocalDateTime[]> getBusyIntervals() {
        List<LocalDateTime[]> list = new ArrayList<>();
        list.add(new LocalDateTime[] { getDeadline().minusHours(1), getDeadline() });
        return list;
    }
}
