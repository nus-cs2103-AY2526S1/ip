package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.ToDo;
import tinkerton.storage.Save;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {
    /**
     * Constructs a ToDoCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    public String parseToDoName(String fullCommand) {
        return fullCommand.substring(5).trim();
    }

    /**
     * Executes the ToDo command, adding a new ToDo task to the list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String toDoName = parseToDoName(fullCommand);

        if (toDoName.isEmpty()) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        if (tasks.containsTaskName(toDoName)) {
            throw new TinkertonException("This task already exists in your list.");
        }

        int prevSize = tasks.size();
        tasks.add(new ToDo(toDoName, false));
        assert tasks.size() == prevSize + 1 : "TaskList size should increase after adding a ToDo";

        StringBuilder result = new StringBuilder("Got it, I've added this task:\n");
        result.append(tasks.get(tasks.size() - 1).toString()).append("<SPLIT>");
        result.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        save.save(tasks);

        return result.toString();
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as adding a ToDo does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
