package meo.task;
public class Task {
    protected String description;
    protected boolean isDone;
    protected String[] tags;

    public Task(String text, String[] tags) {
        description = text;
        this.tags = tags;
        isDone = false;
    }

    public void isMarked() {
        isDone = true;
    }

    public void isUnmarked() {
        isDone = false;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + description;
    }
}
