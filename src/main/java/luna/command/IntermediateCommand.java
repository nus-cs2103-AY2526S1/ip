package luna.command;

/**
 * Represents a {@code Command} that will not result in the program exiting.
 */
public abstract class IntermediateCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }
}
