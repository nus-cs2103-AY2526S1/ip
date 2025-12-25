package leo;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
