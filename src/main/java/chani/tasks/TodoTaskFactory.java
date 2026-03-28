package chani.tasks;

/**
 * Represents a factory to create {@link ToDoTask} instances.
 */
public class TodoTaskFactory implements TaskFactory {
    @Override
    public Task create(String... args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("TodoTask requires description");
        }
        return new ToDoTask(args[0]);
    }
}
