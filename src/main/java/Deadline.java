public class Deadline extends Task {
    private static final String TASKTYPE = "D";
    private final String endtime;

    public Deadline(String description, String endtime) {
        super(description);
        this.endtime = endtime;
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s | %s",
            Deadline.TASKTYPE,
            super.exportString(),
            this.endtime
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (by: %s)",
            Deadline.TASKTYPE,
            super.toString(),
            this.endtime
        );
    }
}
