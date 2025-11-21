package david.task;

import david.exception.InvalidTypeException;

/**
 * Enumerates three task types: todo, deadline, and event.
 */
public enum TaskType {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private String name;

    TaskType(String name) {
        this.name = name;
    }

    /**
     * Creates a TaskType that matches the command.
     *
     * @param s First word of the add command.
     * @return A task type that matches the command.
     * @throws InvalidTypeException If no types match the command.
     */
    public static TaskType of(String s) throws InvalidTypeException {
        for (TaskType t : TaskType.values()) {
            if (s.equals(t.name)) {
                return t;
            }
        }
        throw new InvalidTypeException(s);
    }
}
