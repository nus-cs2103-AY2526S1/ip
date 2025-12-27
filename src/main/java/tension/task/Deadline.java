package tension.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tension.TensionException;

/**
 * creates Deadline class for tasks with deadline
 */
public class Deadline extends Task {

    protected LocalDate dueDate;

    /**
     * returns new Deadline object
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

    /**
     * returns new Deadline object with tags
     */
    public Deadline(String description, String dueDate, ArrayList<String> tags) {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
        this.tags = tags;
    }

    /**
     * checks that time given is in an acceptable format
     */
    public static void checkFormat(String time) throws TensionException {
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new TensionException("Invalid date format: needs to be yyyy-mm-dd");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeStoreString() {
        return "D|" + super.isDone + "|" + description + "|" + dueDate + "|" + super.tags;
    }

    @Override
    public String toString() {
        String date = dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ") " + getTagsAsString();
    }
}
