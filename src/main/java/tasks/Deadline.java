package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Stores the deadline as a LocalDate and provides formatted output.
 */
public class Deadline extends Task {

    protected LocalDate by; // Store the deadline as a LocalDate

    // Constructor that accepts description and deadline as strings
    public Deadline(String description, String by)  {
        super(description);
        assert by != null && !by.isBlank() : "Deadline must not be empty";
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // assume it's valid
    }


    public LocalDate getDeadline() {
        return this.by; // Return the LocalDate object
    }

    @Override
    public String toString() {
        // Format the deadline into the "MMM dd yyyy" format for display (e.g., "Oct 15 2023")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
