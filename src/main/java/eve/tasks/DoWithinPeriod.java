package eve.tasks;

public class DoWithinPeriod extends Task {
    protected String start;
    protected String end;

    public DoWithinPeriod(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTypeIcon() {
        return "P"; // or any icon you like for 'period' tasks
    }

    @Override
    public String toString() {
        return super.toString() + " (between: " + start + " to " + end + ")";
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getPeriodToken() {
        return start + " | " + end;
    }
}
