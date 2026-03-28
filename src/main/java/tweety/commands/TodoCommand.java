package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.tasks.ToDo;

import tweety.ui.Ui;

/**
 * Command for creating todo tasks in the Tweety application.
 * Creates a new todo task with a description and adds it to the task list.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructs a new TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task newTask = new ToDo(description);
        tasks.addTask(newTask);

        storage.saveTasks(tasks);

        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
