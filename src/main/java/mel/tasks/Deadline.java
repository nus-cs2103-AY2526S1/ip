package mel.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor for deadline task
     *
     * @param description
     * @param date
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;

    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

    }

    @Override
    public String toSaveString() {
        return "D" + super.toSaveString() + " | " + date;

    }

    /**
     * Converts the savedString into a deadline task
     *
     * @param savedString
     * @return Task
     */
    public static Task fromSavedString(String savedString) {
        String[] saved = savedString.split(" \\| ");
        Task task = new Deadline(saved[2], LocalDate.parse(saved[3]));
        if (saved[1].equals("1")) {
            task.markAsDone();

        }
        return task;

    }

    /**
     * Changes the date for this task
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;

    }
}
