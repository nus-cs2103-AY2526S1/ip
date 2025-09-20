package ming.command;

import java.time.LocalDateTime;
import java.util.List;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;


/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;
    private final List<String> tags;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time.
     */
    public DeadlineCommand(String description, LocalDateTime by, List<String> tags) {
        this.description = description;
        this.by = by;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.addDeadline(description, by, tags);
        storage.save(tasks.getTasks());
        return ui.showAdd(task, tasks.getSize());
    }

    @Override
    public String getType() {
        return "AddCommand";
    }
}
