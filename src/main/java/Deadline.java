import java.time.LocalDateTime;

public class Deadline extends Task{
    private final LocalDateTime deadline;
    public Deadline(String title, LocalDateTime deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatTime(deadline) + ")";
    }

    @Override
    public String serialise() {
        return baseSerialize("D", formatTime(deadline));
    }
}
