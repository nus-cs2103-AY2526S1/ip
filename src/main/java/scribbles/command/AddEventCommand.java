package scribbles.command;

import java.time.LocalDateTime;

import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a command to add an event task.
     *
     * @param description Description of the task to add.
     * @param from Event dateTime of the task starting from.
     * @param to Event dateTime of the task ending to.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        assert(!this.description.isEmpty());
        taskList.addEventTask(this.description, this.from, this.to);
        storage.saveFile(taskList);
        return "I have added an event task '%s' for you! :3".formatted(this.description);

    }
}
