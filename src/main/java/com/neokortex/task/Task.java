package com.neokortex.task;

import com.neokortex.exceptions.InvalidTaskSerializationException;
import com.neokortex.time.Time;

/**
 * Abstract base class representing a task.
 * <p>
 * The {@code Task} class provides common functionality for all task types,
 * which includes marking/unmarking, description management, keyword search,
 * serialization, and deserialization.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *     <li>Marking/unmarking completion status</li>
 *     <li>Keyword search</li>
 *     <li>Serialization and deserialization support</li>
 * </ul>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * taskType|isMarked|description|[type-specific fields]...
 * </pre>
 *
 * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
 *
 * @see ToDoTask
 * @see DeadlineTask
 * @see EventTask
 * @see InvalidTaskSerializationException
 */
public abstract class Task {
    private static final char markedSymbol = 'X';
    private static final char unmarkedSymbol = ' ';
    protected String description;
    protected boolean isMarked;

    /**
     * Default constructor for a Task with the specified description.
     * The task is initially unmarked (not completed).
     *
     * @param description the task description.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Constructs a Task with the specified description
     * and completion status
     *
     * @param description the task description.
     * @param isMarked the completion status,
     *                 (true for completed, false for incomplete)
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract char taskType();

    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        char markStatus = this.isMarked ? markedSymbol : unmarkedSymbol;
        return String.format("[%c][%c] %s", this.taskType(), markStatus, description);
    }

    public abstract String serialize();

    /**
     * Provides the base serialization of Task objects, only meant to be used by Task
     * subclasses.
     * <p>
     * Serialization format: {@code taskType|isMarked|description|[type-specific fields]...}
     * </p>
     *
     * @return the base string representation of the Task object
     *
     * @see #deserialize(String)
     */
    protected String baseSerialization() {
        int i = this.isMarked ? 1 : 0;
        return String.format("%c|%d|%s", this.taskType(), i, description);
    }

    /**
     * Deserializes a Task from its string representation.
     * <p>
     * This method parses the serialized string and delegates to the appropriate
     * task type's deserialization function based on the type identifier.
     * </p>
     *
     * @param serialization the serialized task string
     * @return a reconstructed Task object
     * @throws InvalidTaskSerializationException if the serialization format is invalid,
     *         the task type is unknown, or the argument count doesn't match the Task type
     * @throws NumberFormatException if numeric fields cannot be parsed into Time
     *
     * @see #serialize()
     * @see TaskType
     * @see Time
     */
    public static Task deserialize(String serialization)
            throws InvalidTaskSerializationException {
        String[] tokens = serialization.split("\\|");

        if (tokens.length == 0
                || tokens[0].length() != 1
                || !TaskType.isValidTaskType(tokens[0].charAt(0))) {
            throw new InvalidTaskSerializationException(serialization);
        }

        TaskType taskType = TaskType.getTaskType(tokens[0].charAt(0));
        if (tokens.length != taskType.getArgCount() + 1) {
            throw new InvalidTaskSerializationException(serialization);
        }

        return switch (taskType) {
        case TODO:
            assert tokens.length == 3;
            yield new ToDoTask(
                    tokens[2],
                    Integer.parseInt(tokens[1]) != 0
            );
        case DEADLINE:
            assert tokens.length == 4;
            yield new DeadlineTask(
                tokens[2],
                Time.deserialize(tokens[3]),
                Integer.parseInt(tokens[1]) != 0
            );
        case EVENT:
            assert tokens.length == 5;
            yield new EventTask(
                tokens[2],
                Time.deserialize(tokens[3]),
                Time.deserialize(tokens[4]),
                Integer.parseInt(tokens[1]) != 0
            );
        };
    }

    @Override
    public abstract boolean equals(Object other);
}
