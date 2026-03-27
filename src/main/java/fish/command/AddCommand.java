package fish.command;
import fish.FishException;
import fish.storage.Storage;
import fish.task.Deadline;
import fish.task.Event;
import fish.task.Task;
import fish.task.TaskList;
import fish.task.Todo;
import fish.ui.Ui;

/**
 * Adds a new task of the specified type to the task list.
 */
public class AddCommand extends Command {
    private final String type;
    private final String description;
    private final String by;
    private final String from;
    private final String to;

    private AddCommand(String type, String description, String by, String from, String to) {
        this.type = type;
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    // todo
    public AddCommand(String type, String description) {
        this(type, description, null, null, null);
    }

    // deadline
    public AddCommand(String type, String description, String by) {
        this(type, description, by, null, null);
    }

    // event
    public AddCommand(String type, String description, String from, String to) {
        this(type, description, null, from, to);
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FishException {
        Task t;
        switch (type) {
        case "todo":
            if (description.isBlank()) {
                throw new FishException("The description of a todo cannot be empty.");
            }
            t = new Todo(description);
            break;
        case "deadline":
            if (by == null) {
                throw new FishException("Please specify a /by in the format of <YYYY-MM-DD>!");
            }
            t = new Deadline(description, by);
            break;
        case "event":
            if (from == null || to == null) {
                throw new FishException("Please specify a /from and a /to both in the format of <YYYY-MM-DD HH:mm>!");
            }
            t = new Event(description, from, to);
            break;
        default:
            throw new FishException("Please specific the task type rather than a/an " + type);
        }
        tasks.add(t);
        storage.save(tasks.getTasks());

        ui.printIn("Great! I've added the task:  " + t);
        ui.printIn("Right now fish is taking " + tasks.size() + " tasks.");
        ui.printIn("Try <sort deadlines> to sort them in ascending order.");
    }
}
