package tasks;
import exception.RomidasException;
import java.time.LocalDate;

public class DeadlineTask extends Task {
    LocalDate deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public static Task toTask(String[] parts) throws RomidasException {
        if (parts.length != 3) {
            throw new RomidasException("Invalid number of arguments. Expected 3 but got " + parts.length);
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
                throw new RomidasException("Invalid deadline format. Expected closing ')' after '(by: date)' in: " + fullDescription);
            }
            
            // Extract the date between "(by: " and ")"
            deadline = fullDescription.substring(byIndex + 6, closingParen);
            
        } else {
            throw new RomidasException("Invalid deadline format. Expected '(by: date)' but got: " + fullDescription);
        }
        
        DeadlineTask task = new DeadlineTask(baseDescription, deadline);
        if (parts[1].equals("1")) {
            task.setIsDone(true);
        }
        return task;
    }

    @Override
    public String toText() {
        return "D | " + (this.isDone ? "1 | ": "0 | ") + this.getDescription() + " (by: " + deadline + ")" + deadline;
    }

    @Override
    public String getStatus() {
        return "[D]";
    }
}
