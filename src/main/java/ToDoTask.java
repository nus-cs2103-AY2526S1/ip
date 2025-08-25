public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    public String serializeTask() {
        return "T" + this.SAVEDELIMITER + (isCompleted() ? "1" : "0")
                + this.SAVEDELIMITER + this.encodeString(this.getName());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
