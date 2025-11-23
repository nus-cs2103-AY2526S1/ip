package jinwoo.command;


import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * Find command to find tasks with a keyword.
 */
public class FindCommand extends Command {

    public FindCommand(String[] fullCommand) {
        super(fullCommand);
    }

    /**
     * Search description of all tasks with keyword and print.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList foundTasks = new TaskList();
        String keyword = getCommand()[0];
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTasks(i).getJustDescription().contains(keyword)) {
                foundTasks.addTask(tasks.getTasks(i));
            }
        }
        if (foundTasks.getSize() == 0) {
            throw new DukeException("Hidden route ??? : No tasks found.");
        }
        ui.printList(foundTasks);
    }
}
