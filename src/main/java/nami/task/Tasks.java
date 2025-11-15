package nami.task;

import java.time.LocalDateTime;

public abstract class Tasks {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Tasks class
     * @param description
     */
    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status whether is it marked or not
     * @return
     */
    public String getStatusIcon() {
        return(isDone ? "X" : "");
    }

    /**
     * Returns the description
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getType() {
        return "";
    }

    public abstract String getResult();

    public abstract String getList();

    public abstract String toStorageFormat();

    public LocalDateTime getSortKey() {
        return null;
    }
}
