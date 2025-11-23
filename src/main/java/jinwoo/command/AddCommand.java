package jinwoo.command;

import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.Deadline;
import jinwoo.tasks.Event;
import jinwoo.tasks.Task;
import jinwoo.tasks.TaskList;
import jinwoo.tasks.Todo;
import jinwoo.util.Ui;

/**
 * Add command for all task types.
 */
public class AddCommand extends Command {

    public AddCommand(String[] fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the add command based on the task type.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String description;
        Task task;
        String by;
        String from;
        String to;
        switch (getCommand()[0]) {
        case "todo":
            assert getCommand().length == 2 : "The length of todo command should be 2";
            description = getCommand()[1];
            task = new Todo(description);
            tasks.addTask(task);
            ui.printAdd(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
            break;
        case "deadline":
            assert getCommand().length == 3 : "The length of deadline command should be 3";
            description = getCommand()[1];
            by = getCommand()[2];
            task = new Deadline(description, by);
            tasks.addTask(task);
            ui.printAdd(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
            break;
        case "event":
            assert getCommand().length == 4 : "The length of event command should be 4";
            description = getCommand()[1];
            from = getCommand()[2];
            to = getCommand()[3];
            task = new Event(description, from, to);
            tasks.addTask(task);
            ui.printAdd(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
            break;
        default:
            throw new DukeException("Warning ??? : Invalid add type.");
        }
    }
}
