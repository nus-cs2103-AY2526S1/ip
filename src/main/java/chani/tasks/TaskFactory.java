package chani.tasks;

/**
 * Represents a factory for creating {@link Task} instances.
 * Implementations produce tasks based on provided arguments.
 */
public interface TaskFactory {
    Task create(String... args);
}
