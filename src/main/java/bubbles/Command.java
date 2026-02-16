package bubbles;

/**
 * Represents an abstract command in the Bubbles chatbot.
 * Subclasses need to implement the {@code execute} method to define how the command behaves.
 */

public abstract class Command {
    private String command;

    /**
     * Constructs a {@code Command} with the given command.
     *
     * @param command the user's input
     */

    public Command(String command){
        this.command = command;
    }

    /**
     * Executes the command.
     * Subclasses need to override this method to define the behaviour of the command.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
