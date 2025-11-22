package command;

import java.util.List;

import exception.GenieweenieException;
import storage.Storage;
import task.Deadline;
import task.Events;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Adds a new task (Todo, Deadline, Event) to the task list.
 */
public class AddCommand extends Command {

    private final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GenieweenieException {
        Task task;

        if (description.startsWith("todo ")) {
            String desc = description.substring(5).trim();
            if (desc.isEmpty()) {
                throw new GenieweenieException("Todo description cannot be empty!");
            }
            task = new Todo(desc);

        } else if (description.startsWith("deadline ")) {
            String[] parts = description.substring(9).split(" /by ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new GenieweenieException("Deadline must have a description and /by date!");
            }
            task = new Deadline(parts[0].trim(), parts[1].trim());

        } else if (description.startsWith("event ")) {
            String[] parts = description.substring(6).split(" /from | /to ", 3);
            if (parts.length < 3
                    || parts[0].trim().isEmpty()
                    || parts[1].trim().isEmpty()
                    || parts[2].trim().isEmpty()) {
                throw new GenieweenieException("Event must have a description, /from start and /to end time!");
            }
            task = new Events(parts[0].trim(), parts[1].trim(), parts[2].trim());

        } else {
            throw new GenieweenieException("Unknown task type! Use todo, deadline, or event.");
        }

        tasks.add(task);

        String response = ui.showAddTask(task, tasks.size());

        try {
            storage.save(List.of(tasks.getTasks().toArray(new Task[0])));
        } catch (Exception e) {
            throw new GenieweenieException("Failed to save task: " + e.getMessage());
        }

        return response;
    }
}
