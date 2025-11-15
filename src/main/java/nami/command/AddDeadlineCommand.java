package nami.command;
import java.time.LocalDateTime;

import nami.task.Deadlines;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.task.Tasks;
import nami.exception.DukeException;

/**
 * public Class to add Deadline into the list
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Execute command to get the String of the command
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Tasks t = new Deadlines(description, by);
        tasks.add(t);
        storage.save(tasks.asList());

        return
        "_____________________________________\n" +
        "Got it. I've added this task: \n" +
        t.getResult() +
        " \n Now you have " + tasks.size() + " tasks in this list.\n" +
        "_____________________________________";
    }
}
//