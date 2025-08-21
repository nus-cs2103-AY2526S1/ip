package task;

public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
