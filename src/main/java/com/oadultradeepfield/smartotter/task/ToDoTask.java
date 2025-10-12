package com.oadultradeepfield.smartotter.task;

/**
 * A {@link Task} representing a to-do item.
 */
public class ToDoTask extends Task {
    /**
     * Creates a to-do task with the given name.
     *
     * @param taskName the name of the task
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIcon() {
        return "[T] %s".formatted(super.getIcon());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToLine() {
        return "T | %s".formatted(super.convertToLine());
    }
}
