package king.task;

import king.KingException;

/**
 * Abstract task that contains description and completion status
 */
public abstract class Task {
    private String description;
    private boolean isComplete;
    private Priority priority;

    /**
     * Enumeration of possible task types
     */
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Enumeration of possible task priorities
     */
    public enum Priority {
        NA(100, "NA", "NA"),
        VERY_LOW(5, "VL", "Very Low"),
        LOW(4, "L", "Low"),
        MEDIUM(3, "M", "Medium"),
        HIGH(2, "H", "High"),
        VERY_HIGH(1, "VH", "Very High");

        private final int priorityLevel;
        private final String databaseText;
        private final String displayText;

        Priority(int priorityLevel, String databaseText, String displayText) {
            this.priorityLevel = priorityLevel;
            this.databaseText = databaseText;
            this.displayText = displayText;
        }

        public int getPriorityLevel() {
            return priorityLevel;
        }

        public String getDatabaseText() {
            return databaseText;
        }

        public String getDisplayText() {
            return displayText;
        }
    }

    /**
     * Instantiates a task based on the description.
     * If no description is provided, throws a missing description exception.
     *
     * @param description Description of the task.
     * @param priority    Priority of the task.
     * @throws KingException Error in creation of task.
     */
    public Task(String description, Priority priority) throws KingException {
        if (description == null || description.isEmpty()) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_DESCRIPTION);
        }
        if (priority == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_PRIORITY);
        }
        this.description = description;
        this.isComplete = false;
        this.priority = priority;
    }

    /**
     * Returns the type of the task.
     *
     * @return king.task.Task type.
     */
    public abstract Type getType();

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return If task is complete return true, else false.
     */
    public boolean getComplete() {
        return this.isComplete;
    }

    /**
     * Returns the priority of the task.
     *
     * @return Priority level of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    public static Priority getPriorityFromString(String priorityText) throws KingException {
        for (Priority p : Priority.values()) {
            if (p.getDatabaseText().equals(priorityText)) {
                return p;
            }
        }
        throw new KingException(KingException.ErrorMessage.INCORRECT_TASK_PRIORITY);
    }

    /**
     * Returns the completion status icon "X" of the task.
     *
     * @return If task is complete return "X", else " ".
     */
    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    /**
     * Sets the description of the task.
     *
     * @param description Description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the task to complete.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Sets the task to incomplete.
     */
    public void unmarkDone() {
        this.isComplete = false;
    }

    /**
     * Sets a new priority to the task.
     *
     * @param priority New priority level to set task to.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]"
                + "[" + getPriority().getDisplayText() + "] "
                + description;
    }
}
