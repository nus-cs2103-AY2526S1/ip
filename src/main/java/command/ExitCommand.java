package command;

import java.util.List;

import exception.GenieweenieException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents the exit command.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GenieweenieException {
        // Store goodbye message in response
        response = ui.showGoodbye();

        // Save tasks to storage
        try {
            storage.save(List.of(tasks.getTasks().toArray(new Task[0])));
        } catch (Exception e) {
            throw new GenieweenieException("Failed to save tasks on exit: " + e.getMessage());
        }

        // Return the stored response
        return response;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
