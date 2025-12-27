package tension.helper;

import java.util.ArrayList;

import tension.TensionException;
import tension.task.Task;

/**
 * Stores list of all tasks and commands to execute
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private ArrayList<Command> commands;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises the task list with the necessary tools
     * @param tasks list of all tasks
     * @param commands list of commands to be done
     * @param storage storage object that links taskList to storage
     * @param ui outputs text to the UI
     */
    public TaskList(ArrayList<Task> tasks, ArrayList<Command> commands, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.commands = commands;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Inserts command into command list
     */
    public void insertCommand(Command command) {
        assert command != null;
        this.commands.add(command);
    }

    /**
     * Executes all the commands in the command list
     * and adds them to the task list
     */
    public String executesAndReturnString() throws TensionException {
        int counter = tasks.size();
        assert !commands.isEmpty();
        Command command = commands.remove(0);
        int index = command.getIndex();
        switch (command.getCommandName()) {
        case "tag":
            Task taggedTask = tasks.get(index);
            taggedTask.addTag(command.getTag());
            storage.rewriteLine(index, taggedTask.makeStoreString());
            return ui.listTaggedTaskAsString(taggedTask);
        case "find":
            ArrayList<Task> foundTasks = new ArrayList<>();
            Task.findTasksWithKeyword(tasks, foundTasks, command.getFullWord());
            return ui.listMatchingTasksAsString(foundTasks);
        case "delete":
            Task removed = tasks.remove(index);
            storage.deleteLine(index);
            counter--;
            return ui.displayDeletedTaskAsString(removed, counter);
        case "event": // Fallthrough
        case "deadline": // Fallthrough
        case "todo":
            Task currTask;
            try {
                currTask = Task.makeTask(command.getFullWord());
            } catch (Exception e) {
                return e.getMessage();
            }
            tasks.add(currTask);
            storage.writeFile(currTask);
            counter++;
            return ui.displayAddedTaskAsString(currTask, counter);
        case "mark": // Fallthrough
        case "unmark":
            if (index < 0 || index >= counter) {
                return "Invalid index input";
            } else {
                Task task = tasks.get(index);
                String s = task.getStatus(command.getIsMarked());
                storage.rewriteLine(index, task.makeStoreString());
                return s;
            }
        case "list":
            return ui.listTasksAsString(tasks, counter);
        case "bye":
            return "Bye. Hope to see you again soon!";
        default:
            return "Invalid command, please start with todo, deadline, or event";
        }
    }
}
