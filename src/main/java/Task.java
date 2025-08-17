public class Task {
    private static int currId = 0;
    private final int id;
    private String name;
    private String description;
    private boolean completed = false;

    public Task(String name, String description) {
        currId += 1;
        this.id = currId;
        this.name = name;
        this.description = description;
        this.completed = false; // default to not completed
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void resetCompleted() {
        this.completed = false;
    }

    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return String.format("%s %s (id:%d)", status, name, id);
    }
}