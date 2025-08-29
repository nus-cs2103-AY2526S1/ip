package command;
import application.TaskList;
import application.Ui;
import application.Storage;

public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exit command doesn't need to do anything special
    }
    
    @Override
    public boolean isBye() {
        return true;
    }
}
