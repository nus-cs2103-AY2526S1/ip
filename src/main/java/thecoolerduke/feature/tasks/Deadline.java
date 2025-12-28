package thecoolerduke.feature.tasks;

import java.time.LocalDateTime;

import thecoolerduke.feature.Task;
import thecoolerduke.main.Priority;

/**
 * Subclass of taskFeature.Task with additional completion time field.
 */
public class Deadline extends Task {
    private final LocalDateTime completeBy;

    /**
     * Initialises a Deadline task with a name, isDone flag and completeBy datetime.
     *
     * @param name Name of the deadline task
     * @param isDone Boolean for if the Deadline task is done
     * @param priority Priority level of the task
     * @param completeBy Deadline as a LocalDateTime object
     */
    public Deadline(String name, boolean isDone, Priority priority, LocalDateTime completeBy) {
        super(name, isDone, priority);
        this.completeBy = completeBy;
    }

    @Override
    public String showTask() {
        return String.format("[D] %s (by: %s)",
                super.showTask(),
                this.completeBy.format(DATETIME_OUTPUT_FORMAT)
        );
    }

    @Override
    public String saveTask() {
        return String.format(
                "D,%s,%s",
                super.saveTask(),
                this.completeBy.format(DATETIME_INPUT_FORMAT)
        );
    }
}
