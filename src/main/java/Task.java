public class Task {
    private boolean completed = false;
    private final String title;

    public Task(String title) {
        this.title = title;
    }

    public void markAsComplete() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }
    @Override
    public String toString() {
        return String.format("[" + this.convert() + "] " + title);
    }

    private String convert() {
        return completed ? "X" : " ";
    }
}
