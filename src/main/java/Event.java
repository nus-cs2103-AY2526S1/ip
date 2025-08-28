public class Event extends Task {
    private static final String TASKTYPE = "E";
    private final String starttime;
    private final String endtime;

    public Event(String description, String starttime, String endtime) {
        super(description);
        this.starttime = starttime;
        this.endtime = endtime;
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s | %s | %s",
            Event.TASKTYPE,
            super.exportString(),
            this.starttime,
            this.endtime
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s (from: %s to: %s)",
            Event.TASKTYPE,
            super.toString(),
            this.starttime,
            this.endtime
        );
    }
}
