package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.DukeyException;

/** Represents Event task */
public class Event extends Task {

    /** Start date time of task */
    private LocalDateTime from;
    /** End date time of task */
    private LocalDateTime to;

    /**
     * Initialises an Event task.
     * Parses the user input to find the start and end date times.
     *
     * @param text the description of task.
     * @param isMarked whether the task is marked as completed.
     * @throws DukeyException if no task description provided.
     */
    public Event(String text, boolean isMarked) throws DukeyException {
        super();
        this.isMarked = isMarked;
        this.type = "E";

        //split user input by whitespace and put substrings into an array
        String [] temp = text.split(" ");
        //temp variable to store task description
        String eventText = "";
        //use StringBuilder to append strings efficiently
        StringBuilder eventDesc = new StringBuilder();

        //loop through substring array
        for (int i = 0; i < temp.length; i++) {

            if (temp[i].equals("/from")) {
                eventText = eventDesc.toString().trim(); // Save description before /from
                eventDesc = new StringBuilder(); //Reset eventDesc

            } else if (temp[i].equals("/to")) {
                this.from = convertToDateTime(eventDesc.toString().trim()); // Save starting date/time
                eventDesc = new StringBuilder(); //Reset eventDesc

            } else {
                eventDesc.append(temp[i]).append(" "); //Append substring and a whitespace
            }
        }
        //throw DukeyException if task description missing
        if (eventText.isEmpty()) {
            throw new DukeyException("Description Missing!"); // Handle missing description
        }
        //set the task description and ending date/time
        this.text = eventText.trim();
        this.to = convertToDateTime(eventDesc.toString().trim());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String temp1 = from.format(formatter);
        String temp2 = to.format(formatter);
        return super.toString() + " (from " + temp1 + " to: " + temp2 + ")";
    }
    @Override
    public String toTxt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String temp1 = from.format(formatter);
        String temp2 = to.format(formatter);
        return super.toString() + " /from " + temp1 + " /to " + temp2;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        return this.from.equals(other.from) && this.to.equals(other.to);
    }

}
