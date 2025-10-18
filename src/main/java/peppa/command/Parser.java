package peppa.command;

import peppa.task.TaskList;
// import peppa.ui.Ui; (no longer needed)

/**
 * Parses a raw user command and triggers the matching TaskList / Storage / Ui action.
 */
public class Parser {
    private TaskList tasks;
    private final Storage storage;

    /**
     * Creates a parser bound to the given collaborators.
     *
     * @param tasks    mutable task catalogue to operate on
     * @param storage  persistence layer used for serialising {@code tasks}
     * @param ui       user-interface helper for printing separators / prompts
     */
    public Parser(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Analyses a single command line and execute the matching action.
     *
     * @param input raw command string typed by the user
     * @return {@code false} if the user typed {@literal "bye"} (caller should terminate);
     *         {@code true} otherwise
     */
    public String parse(String input) {
        assert input != null : "Input command should not be null";
        input = input.trim();
        assert !input.isEmpty() : "Input command should not be empty after trimming";
        String command = input.split("\\s+", 2)[0];
        // Delegate to a command object
        Command cmd;
        switch (command) {
            case "bye":
                cmd = new Bye();
                break;
            case "list":
                cmd = new ListTasks(tasks);
                break;
            case "unmark":
                cmd = new Unmark(tasks, storage, input);
                break;
            case "mark":
                cmd = new Mark(tasks, storage, input);
                break;
            case "delete":
                cmd = new Delete(tasks, storage, input);
                break;
            case "find":
                cmd = new Find(tasks, input);
                break;
            case "todo":
                cmd = new Todo(tasks, storage, input);
                break;
            case "deadline":
                cmd = new Deadline(tasks, storage, input);
                break;
            case "event":
                cmd = new Event(tasks, storage, input);
                break;
            default:
                cmd = new UnknownCommand();
        }

        return cmd.execute();
    }
}
