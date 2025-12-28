package tsunderechan.command;

import java.io.IOException;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to add a deadline task when executed.
 */
public class AddDeadlineCommand extends AddCommand {
    private String by;

    /**
     * Instantiates an AddDeadlineCommand object.
     *
     * @param description Description of the Deadline Task.
     * @param by The time the Deadline Task should be completed by.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String s = tasks.addDeadlineTask(description, by);
        assert s != null : "string for adding deadline task should not be null";
        storage.save(tasks);
        return s;
    }
}
