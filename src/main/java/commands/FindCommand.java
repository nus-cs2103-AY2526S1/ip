package commands;

import java.util.ArrayList;

import exception.RainyException;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents a command to find tasks that contain a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching tasks for the keyword
     * and displaying matching tasks.
     */
    @Override
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];

        ArrayList<Task> allTasks = tasks.getAllTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            throw new RainyException("oh no!!! no matching tasks found for: " + keyword);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        message = sb.toString().trim();

        assert !message.isBlank() : "Message should be non-empty if matches exist";
    }

    /**
     * The find command never exits the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}

