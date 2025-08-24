public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task toggleDone() {
        this.isDone = (this.isDone) ? false : true;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
            "[%c] %s",
            (this.isDone) ? 'X' : ' ',
            this.description
        );
    }
}
