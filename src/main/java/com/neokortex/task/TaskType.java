package com.neokortex.task;

import com.neokortex.exceptions.NoSuchTaskException;

/**
 * Represents the different types of tasks supported by this program as an enum.
 * <p>
 * The {@code TaskType} enum associates each unique {@code Task} subclass with
 * a character identifier, an expected argument count for deserialization,
 * and a deserialization function to create task instances from processed arguments.
 * This enum serves as the central registry for all task types and their respective
 * deserialization logic.
 * </p>
 *
 * <p><b>Supported Task Types:</b></p>
 * <ul>
 *     <li>{@code 'T'} - {@link ToDoTask} (To-do tasks)</li>
 *     <li>{@code 'D'} - {@link DeadlineTask} (Tasks with deadlines)</li>
 *     <li>{@code 'E'} - {@link EventTask} (Events with start and end times)</li>
 * </ul>
 *
 * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
 *
 * @see ToDoTask
 * @see DeadlineTask
 * @see EventTask
 * @see NoSuchTaskException
 */
public enum TaskType {
    TODO('T', 2),
    DEADLINE('D', 3),
    EVENT('E', 4);

    private final char taskType;
    private final int argCount;

    TaskType(char taskType, int argCount) {
        this.taskType = taskType;
        this.argCount = argCount;
    }

    /**
     * Matches the given character identifier to the corresponding {@code TaskType}
     *
     * @param taskType the character identifier to match
     * @return the corresponding {@code TaskType}
     * @throws NoSuchTaskException if no TaskType exists with the given character identifier
     *
     * @see #isValidTaskType(char)
     */
    public static TaskType getTaskType(char taskType)
            throws NoSuchTaskException {
        for (TaskType t : TaskType.values()) {
            if (t.taskType == taskType) {
                return t;
            }
        }

        throw new NoSuchTaskException(taskType);
    }

    public int getArgCount() {
        return argCount;
    }

    /**
     * TODO: Scrap this
     * Checks if the given character represents a valid task type.
     *
     * @param taskType the character to validate
     * @return true if the character corresponds to a registered task type, false otherwise
     *
     * @see #getTaskType(char)
     */
    public static boolean isValidTaskType(char taskType) {
        for (TaskType t : TaskType.values()) {
            if (t.taskType == taskType) {
                return true;
            }
        }
        return false;
    }
}
