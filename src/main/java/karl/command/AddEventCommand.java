package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Event;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Adds an event task to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private final String start;
    private final String end;

    /**
     * Constructs an AddEventCommand from user input.
     *
     * @param input the raw input string starting with "event"
     * @throws KarlException if the input format is invalid
     */
    public AddEventCommand(String input) throws KarlException {
        assert input != null : "Input should not be null";
        String[] parts = input.substring(5).split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty())
            throw new KarlException("Event format: event DESCRIPTION /from DATE /to DATE (yyyy-MM-dd)");
        this.description = parts[0].trim();

        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty())
            throw new KarlException("Event must specify /from and /to dates.");
        this.start = timeParts[0].trim();
        this.end = timeParts[1].trim();
    }

    /**
     * Executes the addition of an event task, saves the task list and displays confirmation.
     *
     * @param tasks the TaskList to add to
     * @param ui the Ui to interact with the user
     * @param storage the Storage to persist data
     * @throws KarlException if date parsing or storage fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        try {
            Task event = new Event(description, start, end);
            tasks.add(event);
            storage.saveTasks(tasks.getTasks());
            ui.showAddedTask(event, tasks.size());
        } catch (Exception e) {
            throw new KarlException("Please enter event dates in yyyy-MM-dd format.");
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
