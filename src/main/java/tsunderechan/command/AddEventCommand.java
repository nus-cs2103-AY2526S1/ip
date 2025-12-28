package tsunderechan.command;

import java.io.IOException;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to add an event task when executed.
 */
public class AddEventCommand extends AddCommand {
    private String from;
    private String to;

    /**
     * Instantiates an AddEventCommand object.
     *
     * @param description Description of the Event Task.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String s = tasks.addEventTask(description, from, to);
        assert s != null : "string for adding event task should not be null";
        storage.save(tasks);
        return s;
    }
}
