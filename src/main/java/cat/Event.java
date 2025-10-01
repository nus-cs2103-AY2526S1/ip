package cat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is a type of Deadline with a specific start and end date.
 * Inherits from the Deadline class.
 */
public class Event extends Deadline{

    /** The start date of the event as a string. */
    //protected String startDate;

    protected LocalDateTime startDate; // converted date-time object


    /**
     * Creates a new Event task with the given name, start date, and end date.
     *
     * @param name The name or description of the Event task.
     * @param startDate The start date of the event as a string (format as needed for display).
     * @param endDate The end date of the event as a string in the format "d/M/yyyy HHmm".
     */
    public Event(String name, String startDate, String endDate){
        super(name,endDate);
//        this.startDate = startDate;

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");//
        LocalDateTime startdatetime = LocalDateTime.parse(startDate, inputFormatter);//
        this.startDate = startdatetime;
    }


    /**
     * Returns the status of the Event task as a formatted string.
     * Format: [E][status] name (Start: startDate) (End: endDate)
     * Example: [E][X] Meeting (Start: 01/09/2025 1400) (End: 01/09/2025 1600)
     *
     * @return A string representing the task's completion status along with start and end dates.
     */
    @Override
    public String getStatus(){
        String stat=  isDone ? "[X]" : "[ ]";
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + stat + " " + name + " " + "(Start: " + startDate.format(outputFormatter) + ") " + "(End: " + endDate.format(outputFormatter) + ")" ;
    }

//    public String getStatus(){
//        String stat=  isDone ? "[X]" : "[ ]";
//        return "[E]" + stat + " " + name + " " + "(Start: " + startDate + ") " + "(End:" + endDate + ")" ;
//    }
//
//
//    public String getStatus(){
//        String stat=  isDone ? "[X]" : "[ ]";
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
//        return "[D]" + stat + " " + name + " " + endDate.format(outputFormatter);
//    }

    /**
     * Returns a formatted string for storing or saving the Event task.
     * Format: D | status | name | startDate | endDate
     * Status: "1" if done, "0" if not done
     *
     * @return A string representing the formatted Event task for storage.
     */
    @Override
    public String getFormat(){
        String d=  isDone ? "1" : "0";
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        return "E" + " | " + d + " | " + name + " | " + startDate.format(outputFormatter) + " | " + endDate.format(outputFormatter);
        //return "D" + " | " + d + " | " + name + " | " + startDate + " | " + endDate;

    }
}
