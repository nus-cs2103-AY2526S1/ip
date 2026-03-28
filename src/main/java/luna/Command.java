package luna;

import java.util.ArrayList;

import luna.ui.Ui;
import luna.tasks.Task;

/**
 * Represents a command in the Luna application.
 */
public interface Command {
    /**
     * Executes the command using the given TaskList and Ui.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The UI instance for displaying output.
     * @return
     * @throws LunaException If the command encounters an error during execution.
     */
    String execute(TaskList taskList, Ui ui) throws LunaException;
}

class InvalidCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui) throws LunaException {
        throw new LunaException.InvalidCommandException(" Error: Sorry I don't know what that means :(");
    }
}

class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        ui.exit();
        return "Bye, see you again soon!";
    }
}

class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list!!\n");

        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.getTaskList().get(i)).append("\n");
        }

        return sb.toString();
    }
}

class TagCommand implements Command {
    private final int index;
    private final String[] tags;

    public TagCommand(int index, String [] tags) {
        this.index = index;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task t = tasks.getTask(this.index);
        for (String tag : tags) {
            t.addTag(tag.trim().toLowerCase());
        }
        return "I've added the tags to the task: \n" + " " + t;
    }
}
class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws LunaException {
        if (index < 0 || index >= tasks.getTaskList().size()) {
            throw new LunaException.InvalidTaskNumberException(" Error: Give me a valid number please!!");
        }
        return tasks.markTask(this.index);
    }
}

class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws LunaException {
        if (index < 0 || index >= tasks.getTaskList().size()) {
            throw new LunaException.InvalidTaskNumberException(" Error: Give me a valid number please!!");
        }
        return tasks.unmarkTask(this.index);
    }
}

class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.deleteTask(index);
    }
}

class AddCommand implements Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.addTask(task);
    }
}

class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by listing all tasks whose description contains the keyword.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The UI to display output messages.
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            return (" oops! No matching tasks found :(");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(" Here are the matching tasks in your list:\n ");
            for (int i = 0; i < matches.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(matches.get(i));
            }
            return sb.toString();
        }
    }
}

