package snowy.task;

public abstract class Task {
    private String task;

    private boolean isDone;

    public Task(String task) {
        this.task = task;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.task;
    }

    public boolean checkStatus() {
        return this.isDone;
    }

    /**
     * Changes the boolean attribute done of the task to true to mark it as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the boolean attribute done of the task to false as marking it as not done.
     */
    public void unmark() {
        this.isDone = false;
    }
}
