//Although there is no abstract methods, this class is meant to be inherited from
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Subclasses can override this to return a more specific Task (same for setDone)
    public Task toggleDone() {
        this.isDone = (this.isDone) ? false : true;
        return this;
    }

    public Task setDone(boolean status) {
        if (this.isDone == status) {
            //can raise an error if necessary
        } else {
            this.isDone = status;
        }
        return this;
    }

    public String exportString() {
        return String.format(
            "%d | %s",
            (this.isDone) ? 1 : 0,
            this.description
        );
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
