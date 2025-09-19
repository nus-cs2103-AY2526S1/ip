package sheares.task;

/**
 * abstract representation of a task, exact behaviour determined by subclasses
 */
public abstract class Task {
    protected String des;
    protected boolean isDone;

    /**
     * constuctor for a basic Task
     * @param des
     */
    public Task(String des) {
        this.des = des;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * helper that returns X if task is done, space otherwise
     * @return
     */
    public String getIcon() {
        return isDone ? "X" : " ";
    }


    public String getDescription() {
        return this.des;
    }

    public boolean isMarked() {
        return isDone;
    }

    /**
     * returns String representation that we write to data file
     */
    public abstract String taskToStr();

    @Override
    public String toString() {
        return "[" + this.getIcon() + "] " + this.des;
    }
}
