public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String serializeTask() {
        return "D" + this.SAVEDELIMITER + (isCompleted() ? "1" : "0")
                + this.SAVEDELIMITER + this.encodeString(this.getName())
                + this.SAVEDELIMITER + this.encodeString(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
