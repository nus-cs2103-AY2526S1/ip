public abstract class Command {
    public abstract void execute(TaskList tasks, ChashUi ui, ChashDb db) throws ChashException;
    public boolean isExit() {
        //Default implementation is false
        return false;
    }
}
