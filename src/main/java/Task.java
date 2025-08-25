import java.util.Base64;

public abstract class Task {
    private String name;
    private boolean completed;
    protected final String SAVEDELIMITER = "|";

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

    // Idea taken from chatGPT on how to safely design my serialization of strings
    protected String encodeString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    protected String decodeString(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " " ) + "] " + this.name;
    }
}
