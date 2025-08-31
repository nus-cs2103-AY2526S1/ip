package sora.task;

public class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(TaskType type,String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        String typeIcon = switch (type) {
            case TODO -> "T";
            case DEADLINE -> "D";
            case EVENT -> "E";
        };
        return "[" + typeIcon + "]" + "[" + this.getStatusIcon() + "] " + description;
    }

    public String toFormat() throws UnsupportedOperationException{
        String isDoneNumber = isDone ? "1" : "0";
        switch (type) {
        case TODO :
            return "T " + "| " + isDoneNumber + " | " + description;
        case DEADLINE:
            if (!(this instanceof Deadline)) {
                throw new UnsupportedOperationException("Not a deadline type");
            }
            String by = ((Deadline)this).byToFormat();
            return "D " + "| " + isDoneNumber + " | " + description + " | " + by;
        case EVENT:
            if (!(this instanceof Event)) {
                throw new UnsupportedOperationException("Not an event type");
            }
            String from = ((Event)this).fromToFormat();
            String to = ((Event)this).toToFormat();
            return "E " + "| " + isDoneNumber + " | " + description + " | " + from + " | " + to;
        default:
            throw new UnsupportedOperationException("Unknown type");
        }

    }
}
