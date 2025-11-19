package Coffee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to delete one or more tasks from the task list.
 * The command parses the user input for space-separated task indices,
 * removes the corresponding tasks, saves the updated list,
 * and displays confirmation.
 */
public class DeleteCommand extends Command {

    private final List<Integer> indices;

    /**
     * Constructs a {@code DeleteCommand} with the given task indices.
     *
     * @param args User input string containing space-separated indices
     *             of the tasks to delete.
     */
    public DeleteCommand(String args) {
        // this is done with AI help to better catch index errors
        this.indices = Arrays.stream(args.trim().split("\\s+"))
                .map(s -> {
                    try {
                        int idx = Integer.parseInt(s);
                        if (idx <= 0) {
                            throw new IllegalArgumentException("Index must be positive: " + s);
                        }
                        return idx;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid index: " + s);
                    }
                })
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }


    /**
     * Executes the command by deleting the specified task from the task list.
     * Saves the updated list to storage and displays confirmation messages to the user.
     *
     * @param tasks Task list from which the task will be deleted.
     * @param ui User interface for displaying messages.
     * @param storage Storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Collect results of deletions
        List<String> results = indices.stream()
                .map(idx -> {
                    try {
                        Task removed = tasks.deleteTask(idx);
                        return "Noted. I've removed this task:\n" + removed;
                    } catch (IndexOutOfBoundsException e) {
                        return "Invalid index: " + idx;
                    }
                })
                .collect(Collectors.toList());

        storage.save(tasks.view());
        results.forEach(ui::showMessage);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
