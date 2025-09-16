package minhgpt.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates a todo task.
 */
class TaskTodo extends Task {
    static {
        Task.register("^todo .+", TaskTodo::new);
    }

    /**
     * Constructs an Event task.
     *
     * @param input Input from user to create a todo task.
     */
    public TaskTodo(String input) {
        super(input.substring(5));
    }

    @Override
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = super.toCommands();
        commands.set(0, String.format("todo %s", commands.get(0)));
        return commands;
    }

    @Override
    public LocalDate getSortingDate() {
        return LocalDate.parse("3000-01-01");
    }

    /**
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
