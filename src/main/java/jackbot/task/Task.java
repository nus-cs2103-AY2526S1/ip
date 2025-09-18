package jackbot.task;

public abstract class Task {
    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark()   { this.done = true; }
    public void unmark() { this.done = false; }

    public String checkbox() { return done ? "[X]" : "[ ]"; }
    public boolean isDone() { return done; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return checkbox() + " " + description;
    }

    abstract public String serialize();

    public static Task deserialize(String data) throws Exception {
        String[] parts = data.split("\\|");
        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");

        Task task;

        try {
          switch (type) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                task = new Deadline(parts[2].trim(), parts[3].trim());
                break;
            case "E":
                task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new Exception("Unknown task type: " + type);
          }
        } catch (Exception e) {
          throw new Exception("Error while serializing task");
        }

        if (done) task.mark();
        return task;
    }
}
