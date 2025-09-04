public abstract class Command {
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) throws ChashException;
    @Override
    public boolean isExit() {
        //Default implementation is false
        return false;
    }
}
