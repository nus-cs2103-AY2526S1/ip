public class Deadline extends Task {
    String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }

    @Override
    public String saveTaskName() {
        return "D" + super.saveTaskName() + " | " + this.deadlineDate;
    }
}
