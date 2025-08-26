public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    private ToDoTask(String name, boolean isCompleted) {
        super(name);
        if (isCompleted) {
            markCompleted();
        }
    }

    public String serializeTask() {
        return "T" + SAVEDELIMITER + (isCompleted() ? "1" : "0")
                + SAVEDELIMITER + this.encodeString(this.getName());
    }

    public static ToDoTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(SAVEDELIMITER, -1);
        if (taskData.length != 3) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["T", "0", "NAME"]
        String name = decodeString(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");

        return new ToDoTask(name, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
