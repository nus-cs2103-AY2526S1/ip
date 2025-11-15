package shaniqua.command;

public abstract class ModifyCommand extends Command {
    protected int idx;

    /**
     * Constructs a ModifyCommand for the task at the specified index.
     *
     * @param idx the index of the task to be modified (0-based)
     */
    public ModifyCommand(int idx) {
        this.idx = idx;
    }
}
