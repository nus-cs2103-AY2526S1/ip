package scribbles.command;

import java.time.LocalDateTime;

import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Constructs a command to add a deadline task.
     *
     * @param description Description of the task to add.
     * @param by Deadline of the task to complete by.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        assert(!this.description.isEmpty());
        taskList.addDeadlineTask(this.description, this.by);
        storage.saveFile(taskList);
        return "I have added a deadline task '%s' for you! :3".formatted(this.description);
    }
}
