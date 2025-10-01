package cat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a type of Todo task with a specific end date and time.
 * Inherits from the Todo Class.
 */
public class Deadline extends Todo{


    /** The date and time by which the task must be completed. */
    protected LocalDateTime endDate; // converted date-time object


    /**
     * Creates a new Deadline task with the given name and end date.
     * The end date string is parsed into a LocalDateTime object using the format "d/M/yyyy HHmm".
     *
     * @param name The name or description of the Deadline task.
     * @param endDate The end date and time as a string in the format "d/M/yyyy HHmm" (e.g., "2/12/2019 1800").
     */
    public Deadline(String name,String endDate){
        super(name);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime localdatetime = LocalDateTime.parse(endDate, inputFormatter);

        this.endDate = localdatetime;

    }

    /**
     * Returns the status of the Deadline task as a formatted string.
     * Format: [D][status] name endDate
     * Example: [D][X] Submit report Dec 02 2019 18:00
     *
     * @return A string representing the task's completion status and end date.
     */

    @Override
    public String getStatus(){
        String stat=  isDone ? "[X]" : "[ ]";
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + stat + " " + name + " " + endDate.format(outputFormatter);
    }


    /**
     * Returns a formatted string for storing or saving the Deadline task.
     * Format: D | status | name | endDate
     * Status: "1" if done, "0" if not done
     * End date is formatted as "d/M/yyyy HHmm"
     *
     * @return A string representing the formatted Deadline task for storage.
     */
    @Override
    public String getFormat(){
        String d=  isDone ? "1" : "0";
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D" + " | " + d + " | " + name + " | " + endDate.format(outputFormatter);
    }

}
