package chani.tasks;

/**
 * Represents a factory to create {@link DeadlineTask} instances.
 */
public class DeadlineTaskFactory implements TaskFactory {
    @Override
    public Task create(String... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("DeadlineTask requires description and date");
        }
        String description = args[0];
        String dueDate = args[1];
        return new DeadlineTask(description, dueDate);
    }
}
