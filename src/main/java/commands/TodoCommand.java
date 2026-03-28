package commands;

import exceptions.DawaeException;
import exceptions.DawaeMissingArgumentException;
import storage.Storage;
import taskstuff.Task;
import taskstuff.TaskList;
import taskstuff.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    private final String args;
    
    /**
     * Creates a todo-adding command with the provided description.
     *
     * @param args the todo description as entered by the user.
     */
    public TodoCommand(String args) {
        this.args = args;
    }
    
    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DawaeException {
        if (args.isBlank()) throw new DawaeMissingArgumentException("Brah, give me ur task description...\uD83D\uDE44");
        Task t = new Todo(args.trim());
        tasks.addTask(t);
        storage.save(tasks);
        return ui.showAdded(t, tasks.size());
    }
}