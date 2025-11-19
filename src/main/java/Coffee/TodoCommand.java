package Coffee;

/**
 * Represents the {@code todo} command.
 * Creates a {@link ToDo} task from the provided description, saves the updated task list,
 * and acknowledges the addition to the user.
 */
public class TodoCommand extends Command {

    /** Raw description string used to construct the to-do task. */
    private final String description;

    /**
     * Constructs a {@code TodoCommand} with the given arguments.
     * Leading and trailing whitespace is trimmed before use.
     *
     * @param args argument string containing the to-do description.
     */
    public TodoCommand(String args) {
        this.description = args.trim();
    }

    /**
     * Executes this command by adding the to-do task, persisting the updated list,
     * and emitting confirmation messages via the {@link Ui}.
     *
     * @param tasks   task list to mutate.
     * @param ui      UI used to display confirmation messages.
     * @param storage storage used to persist tasks after the mutation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        CommandUtil.addSaveAndAck(description, tasks, ui, storage);
    }

}
