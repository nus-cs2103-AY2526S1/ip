package hachiware;
//AI helped to generate javadoc comments
/**
 * Represents a command that creates and adds a {@link Deadline} task to the task list.
 * <p>
 * The {@code DeadlineCommand} parses user input to extract a task description and a due date,
 * validates the input, and then adds the deadline task to the list. The updated list is saved
 * to persistent storage and a confirmation message is returned.
 * </p>
 */
public class DeadlineCommand extends Command {
    /** The description of the deadline task. */
    private final String description;
    /** The due date of the deadline task (in {@code yyyy-MM-dd} format). */
    private final String by;

    /**
     * Constructs a {@code DeadlineCommand} by parsing the provided input string.
     * <p>
     * The input must contain a description and a due date separated by {@code /by}.
     * For example: {@code deadline submit report /by 2025-09-30}.
     * </p>
     *
     * @param args the raw user input containing the task description and due date
     * @throws HachiwareException if the input does not contain a description or a valid {@code /by} section
     */
    public DeadlineCommand(String args) throws HachiwareException {
        //Split input into description and due date
        String[] parts = args.split("/by", 2);

        //Check if there are 2 arguments
        if (parts.length < 2) {
            throw new HachiwareException("MEOW! Hachiware.Hachiware.Deadline must have a /by date.");
        } else if (parts[0].trim().isEmpty()) { //Check if description exist
            throw new HachiwareException("MEOW! Hachiware.Hachiware.Deadline must have a description.");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    /**
     * Executes the deadline command by creating a {@link Deadline} task with the given
     * description and due date, adding it to the task list, saving the updated list,
     * and returning a confirmation message.
     *
     * @param tasks   the task list to add the deadline task to
     * @param ui      the user interface that formats the confirmation message
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message indicating the deadline task has been added
     * @throws HachiwareException if the due date format is invalid or if saving fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "StoreFile cannot be null";

        Task task;
        try {
            task = new Deadline(description, by);
        } catch (Exception e) {
            throw new HachiwareException("Invalid date format. Use yyyy-MM-dd");
        }
        tasks.add(task);
        storage.save(tasks.getAll());
        return ui.showAddTaskMessage(task, tasks.size());
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since adding a deadline does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
