package tasks;

import static parser.DateHandler.isDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import parser.Constants;
import parser.Helper;
import storage.FileHandler;

/**
 * Event class that extends Task
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event class
     * @param description for task description
     * @param from when event starts
     * @param to when event ends
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Prints out the event task
     * @return String with description, from, to with formatter
     */
    public String toString() {
        if (this.to == null) {
            String formatted = String.format(" (on: %s)",
                    this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            return "[E]" + super.toString() + formatted;
        }
        String formatted = String.format(" (from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[E]" + super.toString() + formatted;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Responds to user input of adding an event task to TASK_LIST
     * @param userInput for user input
     * @return chatbot's respond to adding an event task to TASK_LIST
     */
    public static String respondTo(String userInput) {
        String[] fromSplit = userInput.split("/from", 2);
        String description = fromSplit[0].replaceFirst("event", "").trim();

        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        LocalDate fromDate = isDate(from);
        LocalDate toDate = isDate(to);
        Event newEvent = new Event(description, fromDate, toDate);
        Constants.TASK_LIST.add(newEvent);
        FileHandler.save();
        return (Constants.ADDTASK
                + newEvent + "\n"
                + Helper.tasksLeft(Constants.TASK_LIST.size()));
    }

    /**
     * Write the task to the file when application is closed
     * @return String to be written into file
     */
    public String writeToFile() {
        return "E" + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " + this.from + " | "
                + this.to;
    }
}
