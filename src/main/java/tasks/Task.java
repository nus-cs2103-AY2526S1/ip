package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Task to abstract the common task behaviours */
public class Task {

    /** Whether a tasked is marked as completed or not, not completed by default*/
    protected boolean isMarked = false;
    /** Type of Task, "T", "D", "E"/ ToDo, DeadLine, Event */
    protected String type;
    /** Description of task */
    protected String text;

    public String getDesc() {
        return this.text;
    }

    public String mark() {
        isMarked = true;
        return "Nice! I've marked this task as done:\n" + this.toString();
    }

    public String unmark() {
        isMarked = false;
        return "OK, I've marked this task as not done yet:\n" + this.toString();
    }

    /**
     * Converts a string in the format "dd/MM/yyyy HHmm" into a {@code LocalDateTime}.
     *
     * @param date the date-time string to parse, in the format "dd/MM/yyyy HHmm".
     * @return a {@code LocalDateTime} representing the parsed date and time.
     */
    LocalDateTime convertToDateTime(String date) {
        //date format : dd/mm//yyyy 0000
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dt = LocalDateTime.parse(date, formatter);
        return dt;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[" + type + "]" + "[X] " + text;
        } else {
            return "[" + type + "]" + "[ ] " + text;
        }
    }

    /**
     * Converts task description into the appropriate format to write into .txt file.
     *
     * @return The appropriate format to write into .txt file.
     */
    public String toTxt() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {
        // same reference
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        // null or different type
        Task task = (Task) obj; // safe cast
        return this.getDesc().equals(task.getDesc()); // compare contents
    }
}
