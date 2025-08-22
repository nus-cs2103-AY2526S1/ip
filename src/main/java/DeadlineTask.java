public class DeadlineTask extends Task {
    public DeadlineTask(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[D]";
    }
}
