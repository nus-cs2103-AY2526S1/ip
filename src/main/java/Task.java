public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]": "[ ]";
    }

    public abstract String getStatus();

    public abstract String toText();

    @Override
    public String toString() {
        return this.getStatus() + this.getStatusIcon() + " " + this.getDescription();
    }
}