package dino;

import java.io.IOException;
import java.util.ArrayList;

// AI-assisted: Used ChatGPT to draft JavaDoc description for clarity
/**
 * Represents a command in the chatbot system.
 * Commands can be executed with a TaskList, Ui, and Storage.
 */
public interface Command {
    String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DinoException;

    // AI-assisted: Used ChatGPT to draft JavaDoc for this method
    /**
     * Returns whether this command signals the program to exit.
     *
     * @return true if command is exit, false otherwise
     */
    boolean isExit();
}

// AI-assisted: Used ChatGPT to draft JavaDoc description for clarity
/**
 * Base class for all commands.
 */
abstract class CommandBase implements Command {
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Command to add a task.
 */
class AddCommand extends CommandBase {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws DinoException {
        taskList.addTask(task);
        try {
            storage.saveData(taskList.getAllTasks());
        } catch (IOException e) {
            throw new DinoException("Error encountered when saving tasks.");
        }
        ui.showTaskAdded(task, taskList.getTaskListAsArray());
        return ui.getTaskAdded(task, taskList.getTaskListAsArray());
    }
}

/**
 * Command to mark a task as done.
 */
class MarkCommand extends CommandBase {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws DinoException {
        if (taskIndex < 0 || taskIndex >= taskList.getAllTasks().size()) {
            throw new DinoException("This is not a valid task number.");
        }

        Task task = taskList.get(taskIndex);
        task.markAsDone();
        try {
            storage.saveData(taskList.getAllTasks());
        } catch (IOException e) {
            throw new DinoException("Error encountered when saving tasks.");
        }
        ui.showTaskMarked(task);
        return ui.getTaskMarked(task);
    }
}

/**
 * Command to mark a task as undone.
 */
class UnmarkCommand extends CommandBase {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws DinoException {
        if (taskIndex < 0 || taskIndex >= taskList.getAllTasks().size()) {
            throw new DinoException("This is not a valid task number.");
        }

        Task task = taskList.get(taskIndex);
        task.markAsUndone();
        try {
            storage.saveData(taskList.getAllTasks());
        } catch (IOException e) {
            throw new DinoException("Error encountered when saving tasks.");
        }
        ui.showTaskUnmarked(task);
        return ui.getTaskUnmarked(task);
    }
}

/**
 * Command to delete a task.
 */
class DeleteCommand extends CommandBase {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws DinoException {
        if (taskIndex < 0 || taskIndex >= taskList.getAllTasks().size()) {
            throw new DinoException("This is not a valid task number.");
        }

        Task task = taskList.get(taskIndex);
        taskList.removeTask(taskIndex);
        try {
            storage.saveData(taskList.getAllTasks());
        } catch (IOException e) {
            throw new DinoException("Error encountered when saving tasks.");
        }
        ui.showTaskDeleted(task, taskList.getTaskListAsArray());
        return ui.getTaskDeleted(task, taskList.getTaskListAsArray());
    }
}

/**
 * Command to show task list.
 */
class ListCommand extends CommandBase {
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getTaskListAsArray());
        return ui.getTaskList(taskList.getTaskListAsArray());
    }
}

/**
 * Command to exit the chatbot.
 */
class ByeCommand extends CommandBase {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        return ui.getByeMessage();
    }
}

/**
 * Command to find a command.
 */
class FindCommand extends CommandBase {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> searchResults = taskList.findTasks(keyword);
        ui.showFoundResults(searchResults);
        return ui.getFoundResults(searchResults);
    }
}

/**
 * Command to find a command.
 */
class TagCommand extends CommandBase {
    private int taskIndex;
    private String tag;

    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws DinoException {
        if (taskIndex < 0 || taskIndex >= taskList.getAllTasks().size()) {
            throw new DinoException("This is not a valid task number.");
        }

        Task task = taskList.get(taskIndex);
        task.setTag(tag);

        try {
            storage.saveData(taskList.getAllTasks());
        } catch (IOException e) {
            throw new DinoException("Error saving task after tagging.");
        }

        ui.showTaskTagged(task);
        return ui.taskTaggedMsg(task);
    }
}
