public class Task {
    private String name;
    private boolean isDone;

    public Task (String name) {
        this.name = name;
        this.isDone = false;
    }

    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
