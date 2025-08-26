import java.util.Base64;

public abstract class Task {
    private String name;
    private boolean completed;
    protected static String SAVEDELIMITER = " ";

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    // SerialiseTask declared abstract for ensure child classes implement it,
    // but deserializeTask cannot be declared as an abstract method here
    // due to its nature of being static
    public abstract String serializeTask();

    public static Task deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // Each instance of deserializeTask in the child classes
        // also throw InvalidSeralizedTaskDataException
        // If any of them throw it here, just let the callee
        // of this method handle it
        if (taskStr.startsWith("T")) {
            return ToDoTask.deserializeTask(taskStr);
        } else if (taskStr.startsWith("D")) {
            return DeadlineTask.deserializeTask(taskStr);
        } else if (taskStr.startsWith("E")) {
            return EventTask.deserializeTask(taskStr);
        }
        throw new InvalidSerializedTaskDataException();
    }

    // Idea taken from chatGPT on how to safely design my serialization of strings
    protected String encodeString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    protected static String decodeString(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " " ) + "] " + this.name;
    }
}
