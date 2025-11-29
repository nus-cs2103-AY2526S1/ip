package kleebot.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int priority = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean getStatus() { return isDone? true : false; }

    public String getDescription() {
        return this.description;
    }
    
    public abstract String getType();

    public int getPriority() {
        return priority;
    }

    public void setPriority(int val) {
        this.priority = val;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }
}





