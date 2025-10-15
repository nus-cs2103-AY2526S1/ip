package bro.commands;

import bro.tasks.Tasks;
import bro.tasks.Todo;
import bro.utils.FileIo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Creates a new TodoCommand with the given description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        Todo todo = new Todo(description);
        String output = tasks.addTask(todo);
        FileIo.addEntry(todo.toDataString() + "\n");
        return output;
    }
}
