package scribbles.command;

import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to add to do task.
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a command to add a to do task.
     *
     * @param description Description of the task to add.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        assert(!this.description.isEmpty());
        taskList.addToDoTask(this.description);
        storage.saveFile(taskList);
        return "I have added a todo task '%s' for you! :3".formatted(this.description);
    }
}
