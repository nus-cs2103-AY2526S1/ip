public class EventTask extends Task {
    private String startDate;
    private String endDate;

    public EventTask(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private EventTask(String name, boolean isCompleted, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        if (isCompleted) {
            markCompleted();
        }
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
        return "E" + SAVEDELIMITER + (isCompleted() ? "1" : "0")
                + SAVEDELIMITER + this.encodeString(this.getName())
                + SAVEDELIMITER + this.encodeString(this.startDate)
                + SAVEDELIMITER + this.encodeString(this.endDate);
    }

    public static EventTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVEDELIMITER, -1);
        if (taskData.length != 5) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["E", "0", "NAME", "START", "END]
        String name = decodeString(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");
        String startDate = decodeString(taskData[3]);
        String endDate = decodeString(taskData[4]);

        return new EventTask(name, isCompleted, startDate, endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to " + this.endDate + ")";
    }
}
