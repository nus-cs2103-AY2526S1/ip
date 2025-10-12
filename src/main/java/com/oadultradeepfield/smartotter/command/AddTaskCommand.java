package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Base command for adding a {@link Task} to the task list.
 */
public abstract class AddTaskCommand implements Executable {
    private final Task task;

    /**
     * Creates an {@code AddTaskCommand} for the given task.
     *
     * @param task the task to be added
     */
    protected AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(CommandContext context) {
        assert context != null : "Context cannot be null";

        context.addTask(task);
        String message =
            """
                Got it! I have added the task:
                    %s
                Now you have %d tasks. It could be tough, but good luck!"""
                .formatted(task, context.tasks().size());

        CustomIO.printPretty(message);
        return message;
    }
}
