public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException;
    public boolean isExit() {
        return false;
    }
}
