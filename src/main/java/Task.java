public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public abstract String taskToString();


    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String checked = this.isDone ? "X" : " ";
        return String.format("[%s] %s", checked, this.name);
    }
}
