package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Deadline;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Adds a deadline task to the task list.
 */
public class AddDeadlineCommand implements Command {
    private final String description;
    private final String dueDate;

    /**
     * Constructs an AddDeadlineCommand from user input.
     *
     * @param input the raw input string starting with "deadline"
     * @throws KarlException if the input format is invalid
     */
    public AddDeadlineCommand(String input) throws KarlException {
        assert input != null : "Input string should not be null";
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty())
            throw new KarlException("Deadline format: deadline DESCRIPTION /by DATE (yyyy-MM-dd)");
        this.description = parts[0].trim();
        this.dueDate = parts[1].trim();
    }

    /**
     * Executes the addition of a deadline task, saves the task list and displays confirmation.
     *
     * @param tasks the TaskList to add to
     * @param ui the Ui to interact with the user
     * @param storage the Storage to persist data
     * @throws KarlException if date parsing or storage fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        assert tasks != null : "tasks should never be null";
        assert ui != null : "ui should never be null";
        assert storage != null : "storage should never be null";
        try {
            Task deadline = new Deadline(description, dueDate);
            tasks.add(deadline);
            storage.saveTasks(tasks.getTasks());
            ui.showAddedTask(deadline, tasks.size());
        } catch (Exception e) {
            throw new KarlException("Please enter the deadline date in yyyy-MM-dd format.");
        }
    }

    /**
     * Indicates this command does not terminate the program.
     *
     * @return false as this command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
