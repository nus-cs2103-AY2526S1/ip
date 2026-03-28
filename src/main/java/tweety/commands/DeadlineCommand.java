package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.Deadline;
import tweety.tasks.Task;
import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for creating deadline tasks in the Tweety application.
 * Creates a new deadline task with a description and due date, then adds it to the task list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String by;

    /**
     * Constructs a new DeadlineCommand with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date or time for the deadline.
     */
    public DeadlineCommand(String description, String by) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);

            storage.saveTasks(tasks);

            ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
