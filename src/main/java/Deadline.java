public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, boolean isMarked, String deadline) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return "(by:" + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDeadline();
    }

    @Override
    public String getData() {
        return String.format("D|%s|%s|%s",
                super.getDescription(), super.getIsMarked(), this.deadline);
    }

}
