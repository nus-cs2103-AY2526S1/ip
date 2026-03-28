package ackermann.codewords;

import ackermann.exceptions.task.EmptyNameException;
import ackermann.functions.TaskList;
import ackermann.task.Task;
import ackermann.task.ToDo;

/**
 * Handles Todo Codeword
 */
public class TodoCodeword extends Codeword {
    private TaskList tasks;
    private String name;

    /**
     * Constructor for TodoCodeword
     * @param tasks task list to append
     * @param name name of task
     */
    public TodoCodeword(TaskList tasks, String name) {
        this.tasks = tasks;
        this.name = name;
    }

    @Override
    public String execute() throws EmptyNameException {
        StringBuilder output = new StringBuilder();
        Task toDo = new ToDo(this.name);
        this.tasks.add(toDo);
        return String.valueOf(
                output.append("Got it bro. I've added this ToDo:\n")
                        .append(toDo)
                        .append("\nNow you have ")
                        .append(this.tasks.size())
                        .append(" tasks in the list.")
        );
    }
}
