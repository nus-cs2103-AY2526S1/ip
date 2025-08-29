package command;
import application.TaskList;
import application.Ui;
import application.Storage;
import tasks.Task;
import exception.RomidasException;

public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + deletedTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
        
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
