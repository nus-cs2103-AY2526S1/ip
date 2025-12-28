package thecoolerduke.feature.tasks;

import thecoolerduke.feature.Task;
import thecoolerduke.main.Priority;

/**
 * Subclass of taskFeature.Task for clarity in naming.
 * Does not have additional functionality on top of the identifier when displaying task.
 */
public class ToDo extends Task {
    /**
     * Initialises a ToDo task.
     *
     * @param name Name of the task
     * @param isDone Boolean for if the task is done
     * @param priority Priority level of the task
     */
    public ToDo(String name, boolean isDone, Priority priority) {
        super(name, isDone, priority);
    }

    @Override
    public String showTask() {
        return String.format("[T] %s", super.showTask());
    }

    @Override
    public String saveTask() {
        return String.format("T,%s", super.saveTask());
    }
}
