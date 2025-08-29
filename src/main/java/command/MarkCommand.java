package command;
import application.Storage;
import application.TaskList;
import application.Ui;
import exception.RomidasException;
import tasks.Task;

public class MarkCommand extends Command {
    private int index;
    private boolean isMark;
    
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.setIsDone(isMark);
        
        if (isMark) {
            ui.showMessage("Nice! I've marked this task as done:");
        } else {
            ui.showMessage("OK, I've marked this task as not done yet:");
        }
        ui.showMessage("  " + task.toString());
        
        try {
            storage.saveTasks("romidas.txt", tasks.retreive());
        } catch (RomidasException e) {
            ui.showError(e.getMessage());
        }
    }
    
    @Override
    public boolean isBye() {
        return false;
    }
}
