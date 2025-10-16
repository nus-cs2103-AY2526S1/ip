package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Event;
import junny.tasks.Task;

/**
 * Represents a command that creates and adds an {@link Event} task.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand.
     *
     * @param description the description of the event
     * @param from        the start date of the event in yyyy-MM-dd format
     * @param to          the end date of the event in yyyy-MM-dd format
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new event task,
     * adding it to the task list, updating the UI, and saving to storage.
     *
     * @param tasks   the current list of tasks
     * @param ui      the UI handler to display messages
     * @param storage the storage handler to save/load tasks
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        ui.addTask(event, tasks.size());
        storage.saveAllTasks(tasks);
    }
}
