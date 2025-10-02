package com.neokortex.task;

/**
 * Represents a simple todo task without time constraints.
 * <p>
 * A {@code ToDoTask} extends the base {@code Task} class and is the simplest
 * type of task, only requiring a description and completion status. It
 * extends directly from the base {@code Task} class without adding any
 * additional fields or constraints.
 * </p>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * T|isMarked|description
 * </pre>
 *
 * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
 *
 * @see Task
 */
public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public char taskType() {
        return 'T';
    }

    /**
     * Serializes this {@code ToDoTask} to a string representation for storage.
     * <p>
     * Format: {@code T|isMarked|description}
     * </p>
     * <p>
     * This uses the base serialization format since {@code ToDoTask}s don't have
     * additional fields beyond the base Task class.
     * </p>
     *
     * @return a string representation for storage
     * @see Task#baseSerialization()
     */
    @Override
    public String serialize() {
        return super.baseSerialization();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof ToDoTask otherTask) {
            return this.description.equals(otherTask.description)
                    && this.isMarked() == otherTask.isMarked();
        }

        return false;
    }
}
