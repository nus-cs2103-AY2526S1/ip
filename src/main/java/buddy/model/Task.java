package buddy.model;

/**
 * Superclass of all tasks.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert description != null && !description.isBlank() : "Task description cannot be blank";
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public abstract String getType();

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Modifies the task to be stored as a String based on subtype
     */

    public String toDataString() {
        switch (getType()) {
        case "T":
            return "T | " + (isDone() ? 1 : 0) + " | " + getDescription();
        case "D":
            return "D | " + (isDone() ? 1 : 0) + " | " + getDescription()
                    + " | " + ((Deadline) this).getByIso();
        case "E":
            return "E | " + (isDone() ? 1 : 0) + " | " + getDescription()
                    + " | " + ((Event) this).getFrom() + "-" + ((Event) this).getTo();
        default:
            return "";
        }
    }

    /**
     * Modifies the String of data from storage to Task
     */

    public static Task fromDataString(String data) {
        data = data.trim();
        assert data != null : "Input to fromDataString cannot be null";
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Empty task line");
        }

        String[] parts = data.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Need 3 or more fields: " + data);
        }

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();

        switch (type) {
            case "T": {
                Task t = new Todo(desc);
                if (done) t.markAsDone();
                return t;
            }
            case "D": {
                if (parts.length < 4)
                    throw new IllegalArgumentException("buddy.model.Deadline missing date: " + data);
                Task t = new Deadline(desc, parts[3].trim());
                if (done) t.markAsDone();
                return t;
            }
            case "E": {
                if (parts.length < 4)
                    throw new IllegalArgumentException("buddy.model.Event missing date range: " + data);
                String[] times = parts[3].trim().split("-", 2);
                if (times.length < 2)
                    throw new IllegalArgumentException("Bad event range (need from-to): " + data);
                Task t = new Event(desc, times[0].trim(), times[1].trim());
                if (done) t.markAsDone();
                return t;
            }
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }




}