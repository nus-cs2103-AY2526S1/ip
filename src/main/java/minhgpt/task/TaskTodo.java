package minhgpt.task;

import java.util.ArrayList;

/**
 * Encapsulate a todo task.
 */
class TaskTodo extends Task {
    static {
        Task.register("^todo .+", TaskTodo::new);
    }

    /**
     * Construct a basic task.
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

    /**
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
