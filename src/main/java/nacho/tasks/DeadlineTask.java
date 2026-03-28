package nacho.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nacho.exceptions.UserInputException;


/**
 *  Task with a deadline (by) date
 */

public class DeadlineTask extends Task {

    private LocalDateTime byDate;

    /**
     * Creates a Deadline Task Object
     * @param title Task Title
     * @param byDate datetime (dd/MM/yyyy-HH:mm") formatted
     * @throws UserInputException Throws exception when user types something unrecognised
     */
    public DeadlineTask(String title, String byDate) throws UserInputException {
        super(title);

        // Throws java.time.format.DateTimeParseException if invalid input
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
            this.byDate = LocalDateTime.parse(byDate, formatter);
        } catch (DateTimeParseException e) {
            throw new UserInputException("Wrong Date Format! Use dd/MM/yyyy-HH:mm");
        }
    }

    private String getByDateDisplayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM y - hh:mm a");
        return this.byDate.format(formatter);
    }

    private String getByDateStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        return this.byDate.format(formatter);
    }

    @Override
    public String getStorageRepresentation() {
        String[] info = new String[4];
        info[0] = "D";
        info[1] = Integer.toString(this.isCompleted());
        info[2] = this.getTitle();
        info[3] = this.getByDateStorageString();

        return String.join(" | ", info);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.getByDateDisplayString()
                + ")";
    }
}
