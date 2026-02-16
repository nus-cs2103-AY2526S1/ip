package bubbles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start timing and end timing in the Bubbles chatbot.
 * A {@code Event} task includes a description and a from and to date/time.
 */
public class Event extends Task{
    protected String task;
    protected String from;
    protected String to;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;


    /**
     * Constructs a {@code Event} task from the given description.
     * The description contains "/from" and "/to" to separate the task
     * description from the from and to date/time.
     *
     * @param description the full user input
     * @throws BubblesException if the task description is missing or improperly formatted
     */
    public Event(String description) throws BubblesException{
        super(description);

            String[] words = description.split("/from");

            if (words[0].trim().isEmpty()) {
                throw new BubblesException("Woops! You forgot to add in the description!");
            }
            this.task = words[0];
            if(!description.contains("/from")){
                throw new BubblesException("Woops! You forgot to add in the from timing!");
            }

        if(!description.contains("/to")){
            throw new BubblesException("Woops! You forgot to add in the to timing!");
        }
            words = words[1].split("/to");

            if (words[0].trim().isEmpty()) {
                throw new BubblesException("Woops! You forgot to add in the from timing!");
            }

            if (words[1].trim().isEmpty()) {
                throw new BubblesException("Woops! You forgot to add the to timing!");
            }

            this.from = words[0].trim();
            this.to = words[1].trim();

            this.fromTime = parseFlexibleDate(this.from);
            this.toTime = parseFlexibleDate(this.to);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representing the event task
     */
    public String toString() {
        try {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            return "\t[E]" + this.getStatusIcon() + " " + this.task +
                    "(from: " + fromTime.format(outputFormatter) +
                    " to: " + toTime.format(outputFormatter) + ")";

        } catch (Exception e) {
            return "Oops! : " + e.getMessage();
        }

    }

    private LocalDateTime parseFlexibleDate(String dateStr) throws BubblesException {
        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")
        };

        for (DateTimeFormatter format : formats) {
            try {
                return LocalDateTime.parse(dateStr, format);
            } catch (Exception ignored) {
                // Try next format
            }
        }

        throw new BubblesException("Unrecognized date format: " + dateStr);
    }

}
