package ming.command;

import java.time.LocalDateTime;
import java.util.List;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final List<String> tags;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to, List<String> tags) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.addEvent(description, from, to, tags);
        storage.save(tasks.getTasks());
        return ui.showAdd(task, tasks.getSize());
    }

    @Override
    public String getType() {
        return "AddCommand";
    }

}
