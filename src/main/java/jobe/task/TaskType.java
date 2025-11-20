package jobe.task;

import jobe.exception.JobeException;

/**
 * Represents the types of tasks stored in files and recognised by Jobe.
 */
public enum TaskType {
    D,
    E,
    T;
    
    /**
     * Returns a task type which corresponds to the input string.
     *
     * @param input Input task when parsing file.
     * @return Task type corresponding to user's input.
     * @throws JobeException if input task does not match any valid task types.
     */
    public static TaskType stringToCommand(String input) throws JobeException {
        try {
            return TaskType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JobeException("OOPS!!!! There is an error with the TaskType of this input");
        }
    }
}
