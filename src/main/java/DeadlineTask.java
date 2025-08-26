public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    private DeadlineTask(String name, boolean isCompleted, String deadline) {
        super(name);
        this.deadline = deadline;
        if (isCompleted) {
            markCompleted();
        }
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String serializeTask() {
        return "D" + SAVEDELIMITER + (isCompleted() ? "1" : "0")
                + SAVEDELIMITER + this.encodeString(this.getName())
                + SAVEDELIMITER + this.encodeString(this.deadline);
    }

    public static DeadlineTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVEDELIMITER, -1);
        if (taskData.length != 4) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["D", "0", "NAME", "DEADLINE"]
        String name = decodeString(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");
        String deadline = decodeString(taskData[3]);

        return new DeadlineTask(name, isCompleted, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
