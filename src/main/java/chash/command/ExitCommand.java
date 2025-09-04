public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        ui.printMsg("Bye. Hope to see you again soon!");
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
