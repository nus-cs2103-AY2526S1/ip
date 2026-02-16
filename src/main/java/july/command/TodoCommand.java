package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.tasks.Task;
import july.tasks.Todo;
import july.ui.Ui;

/**
 * Command to create and add a new todo task to the task list.
 * This command creates a basic todo task with a description and adds it
 * to the current task collection without any specific deadline or time constraints.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description the description text for the new todo task
     */
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Executes the todo command to create and add a new todo task.
     * Creates a new Todo object with the provided description, adds it to the
     * task list, and displays a confirmation message to the user.
     *
     * @param tasks the ArrayList of tasks to add the new todo to
     * @param ui the user interface component for displaying output
     * @param storage the storage component for data persistence
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks);

        addResponses(
            "Got it. I've added this todo:", "  " + todo.toString(),
            String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks")
        );
    }
}
