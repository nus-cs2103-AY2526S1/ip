package chani.tasks;

import java.util.HashMap;

/**
 * Maintains a registry of task factories and provides methods to create
 * tasks based on a string identifier. Supports internal and CLI identifiers.
 */
public class TaskRegistry {
    private static final HashMap<String, TaskFactory> registry = new HashMap<>();

    static {
        DeadlineTaskFactory deadlineFactory = new DeadlineTaskFactory();
        TodoTaskFactory toDoFactory = new TodoTaskFactory();
        EventTaskFactory eventFactory = new EventTaskFactory();
        PeriodTaskFactory periodFactory = new PeriodTaskFactory();

        // chani.Storage identifiers
        registry.put("d", deadlineFactory);
        registry.put("t", toDoFactory);
        registry.put("e", eventFactory);
        registry.put("p", periodFactory);

        // CLI identifiers
        registry.put("deadline", deadlineFactory);
        registry.put("todo", toDoFactory);
        registry.put("event", eventFactory);
        registry.put("period", periodFactory);

    }

    public TaskRegistry() {

    }

    /**
     * Creates a new task based on the specified identifier.
     *
     * @param identifier The task type identifier (e.g., "d", "todo").
     * @param args Arguments required to create the task.
     * @return A new {@link Task} created by the corresponding factory.
     * @throws IllegalArgumentException If the identifier is unknown (no factory exists).
     */
    public static Task createTask(String identifier, String... args) {
        TaskFactory factory = getFactory(identifier);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown task type: " + identifier);
        }
        return factory.create(args);
    }

    /**
     * Retrieves the task factory corresponding to the given identifier.
     *
     * @param identifier The task type identifier.
     * @return The corresponding {@link TaskFactory}, never null if assertions enabled.
     */
    private static TaskFactory getFactory(String identifier) {
        TaskFactory factory = registry.get(identifier.toLowerCase());
        assert factory != null : "There should exist a Factory for" + identifier;
        return factory;
    }
}
