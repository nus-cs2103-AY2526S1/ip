package com.oadultradeepfield.smartotter.command;

import com.oadultradeepfield.smartotter.task.ToDoTask;

/**
 * A command that adds a new todo task to the task list.
 */
public final class AddTodoCommand extends AddTaskCommand {
    /**
     * Creates an {@code AddTodoCommand} with the specified task name.
     *
     * @param taskName the name of the task to add
     */
    public AddTodoCommand(String taskName) {
        super(new ToDoTask(taskName));
    }

    /**
     * Parses input to create a {@code AddTodoCommand}.
     *
     * @param taskName string representing the task name
     * @return a new {@code AddTodoCommand} instance
     */
    public static Executable fromInput(String taskName) {
        return new AddTodoCommand(taskName);
    }
}
