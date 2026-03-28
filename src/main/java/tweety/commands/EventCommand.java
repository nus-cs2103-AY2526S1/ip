package tweety.commands;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.Event;
import tweety.tasks.Task;
import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Command for creating event tasks in the Tweety application.
 * Creates a new event task with a description, start time, and end time, then adds it to the task list.
 */
public class EventCommand extends Command {

    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time or date of the event.
     * @param to The end time or date of the event.
     */
    public EventCommand(String description, String from, String to) {
        super(CommandType.EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task newTask = new Event(description, from, to);
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
