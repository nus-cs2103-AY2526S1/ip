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
        if (parts.length != 4) {
            throw new RomidasException("Invalid number of arguments. Expected 4 but got " + parts.length);
        }
        // Extract the base description by removing the "(by: ...)" part
        String baseDescription = parts[2];
        if (baseDescription.contains(" (by: ")) {
            baseDescription = baseDescription.substring(0, baseDescription.indexOf(" (by: "));
        }
        DeadlineTask task = new DeadlineTask(baseDescription, parts[3]);
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
