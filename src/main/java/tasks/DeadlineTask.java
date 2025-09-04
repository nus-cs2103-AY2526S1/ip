package tasks;
import java.time.LocalDate;

import exception.RomidasException;

/**
 * Represents a deadline task with a specific due date.
 * Extends the base Task class to provide deadline-specific functionality.
 */
public class DeadlineTask extends Task {
    LocalDate deadline;

    /**
     * Constructs a new DeadlineTask with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline date in yyyy-MM-dd format.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Converts a string array representation back to a DeadlineTask.
     * Parses the task data from storage format and reconstructs the DeadlineTask object.
     * Extracts the base description and deadline from the formatted string.
     *
     * @param parts Array containing task type, completion status, and formatted description.
     * @return The reconstructed DeadlineTask.
     * @throws RomidasException If the input format is invalid or missing required parts.
     */
    public static Task toTask(String[] parts) throws RomidasException {
        if (parts.length != 3) {
            throw new RomidasException("Invalid number of arguments. Expected 3 but got "
                    + parts.length);
        }
        // Extract the base description and deadline from parts[2]
        String fullDescription = parts[2];
        String baseDescription;
        String deadline;
        
        if (fullDescription.contains(" (by: ")) {
            int byIndex = fullDescription.indexOf(" (by: ");
            baseDescription = fullDescription.substring(0, byIndex);
            
            // Find the closing ")" after "(by: "
            int closingParen = fullDescription.indexOf(")", byIndex);
            if (closingParen == -1) {
                throw new RomidasException("Invalid deadline format. Expected closing ')' after "
                        + "'(by: date)' in: " + fullDescription);
            }
            
            // Extract the date between "(by: " and ")"
            deadline = fullDescription.substring(byIndex + 6, closingParen);
            
        } else {
            throw new RomidasException("Invalid deadline format. Expected '(by: date)' but got: "
                    + fullDescription);
        }
        
        DeadlineTask task = new DeadlineTask(baseDescription, deadline);
        if (parts[1].equals("1")) {
            task.setIsDone(true);
        }
        return task;
    }

    @Override
    public String toText() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.getDescription()
                + " (by: " + deadline + ")" + deadline;
    }

    @Override
    public String getStatus() {
        return "[D]";
    }

    /**
     * Returns a string representation of this deadline task including its status,
     * completion icon, description, and deadline date.
     *
     * @return The complete string representation of this deadline task.
     */
    @Override
    public String toString() {
        return this.getStatus() + this.getStatusIcon() + " " + this.getDescription() 
                + " (by: " + this.deadline + ")";
    }
}
