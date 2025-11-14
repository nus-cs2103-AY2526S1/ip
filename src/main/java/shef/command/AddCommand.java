package shef.command;

/**
 * Encapsulates commands that add a task.
 */
public abstract class AddCommand extends Command {
    public AddCommand(String args) {
        super(args);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
