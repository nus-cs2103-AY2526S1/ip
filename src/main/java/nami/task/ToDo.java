package nami.task;

import java.time.LocalDateTime;

public class ToDo extends Tasks {
    /**
     * Constructor for toDO task
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the type and return as a string
     * @return
     */
    @Override
    public String getType() {
        return "T";
    }
    public String getResult() {
        return "[" + this.getType() + "] [" + this.getStatusIcon() + "] " + this.getDescription();
    }
    public String getList() {
        return "[" + this.getType() + "] [" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toStorageFormat() {
        return "T | " + (isDone ? "1" : "0" + " | " + description);
    }

}
