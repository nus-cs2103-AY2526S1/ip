package chani.tasks;

/**
 * Represents a factory to create {@link PeriodTask} instances.
 */
public class PeriodTaskFactory implements TaskFactory {
    @Override
    public Task create(String... args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("periodTask requires description, start date and end date");
        }
        String description = args[0];
        String startDate = args[1];
        String endDate = args[2];
        return new PeriodTask(description, startDate, endDate);
    }

}
