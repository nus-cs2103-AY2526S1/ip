public class EventTask extends Task {
    private String startDate;
    private String endDate;

    public EventTask(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String serializeTask() {
        return "E" + this.SAVEDELIMITER + (isCompleted() ? "1" : "0")
                + this.SAVEDELIMITER + this.encodeString(this.getName())
                + this.SAVEDELIMITER + this.encodeString(this.startDate)
                + this.SAVEDELIMITER + this.encodeString(this.endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to " + this.endDate + ")";
    }
}
