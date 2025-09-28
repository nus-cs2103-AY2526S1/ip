package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;
import alpaca.task.Deadline;
import alpaca.task.Event;
import alpaca.task.Task;
import alpaca.task.Todo;
import alpaca.task.DoWithinPeriodTask;

/**
 * Command to add a new task (Todo, Deadline, or Event) to the task list.
 * Handles parsing of input arguments and validates the required format for each task type.
 */
public class AddCommand extends Command {

    private final String type;
    private final String args;

    /**
     * Constructs an AddCommand with the given type and arguments.
     *
     * @param type The type of task ("todo", "deadline", "event").
     * @param args The description and timing information for the task.
     */
    public AddCommand(String type, String args) {
        assert type != null && !type.isEmpty() : "Task type cannot be null or empty";
        assert args != null : "Arguments cannot be null";

        this.type = type;
        this.args = args;
    }

    /**
     * Executes the AddCommand: creates the appropriate Task, adds it to the task list,
     * saves the task list to storage, and returns a message for the user.
     *
     * If the input is invalid, returns a helpful message showing the correct format.
     *
     * @param tasks   The TaskList to add the task to.
     * @param ui      The UI object for displaying messages.
     * @param storage The Storage object for saving tasks.
     * @return A message indicating success or the correct format if invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t;

            switch (type.toLowerCase()) {
            case "todo":
                if (args.trim().isEmpty()) {
                    throw new IllegalArgumentException("Todo description cannot be empty.");
                }
                t = new Todo(args.trim());
                break;

            case "deadline": {
                String[] parts = args.split("\\s*/by\\s*", 2);
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Deadline format: deadline <description> /by <yyyy-MM-dd>");
                }
                t = new Deadline(parts[0].trim(), parts[1].trim());
                break;
            }

            case "event": {
                String[] parts = args.split("\\s*/from\\s*", 2);
                if (parts.length < 2 || parts[0].trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Event format: event <description> /from <start> /to <end>");
                }
                String name = parts[0].trim();
                String[] times = parts[1].split("\\s*/to\\s*", 2);
                if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Event format: event <description> /from <start> /to <end>");
                }
                t = new Event(name, times[0].trim(), times[1].trim());
                break;
            }

            case "within": {
                String[] periodParts = args.split("\\s*/to\\s*", 2);
                if (periodParts.length < 2 || periodParts[0].trim().isEmpty() || periodParts[1].trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Within format: within <description> /from <start> /to <end>");
                }

                String[] dates = periodParts[0].split("\\s*/from\\s*", 2);
                if (dates.length < 2 || dates[0].trim().isEmpty() || dates[1].trim().isEmpty()) {
                    throw new IllegalArgumentException(
                            "Within format: within <description> /from <start> /to <end>");
                }

                String description = dates[0].trim();
                String startDate = dates[1].trim();
                String endDate = periodParts[1].trim();

                t = new DoWithinPeriodTask(description, startDate, endDate);
                break;
            }

            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
            }

            tasks.add(t);
            storage.save(tasks.getTasks());

            String msg = "Got it. I've added this task:\n  " + t +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
            ui.showMessage(msg);
            return msg;

        } catch (IllegalArgumentException e) {
            String msg = "Invalid input: " + e.getMessage();
            ui.showMessage(msg);
            return msg;
        } catch (Exception e) {
            String msg = "An unexpected error occurred while adding the task.";
            ui.showMessage(msg);
            return msg;
        }
    }
}
