package nami.command;

import java.time.LocalDateTime;

import nami.task.Events;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.task.Tasks;
import nami.exception.DukeException;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor
     * @param description
     * @param start
     * @param end
     */
    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command and return a String to eventually print
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Tasks t = new Events(description, start, end);
        tasks.add(t);
        storage.save(tasks.asList());

        return
        "______________________________________ \n" +
        "Got it. I've added this task: \n" +
        t.getResult() +
        "\n Now you have " + tasks.size() + " tasks in this list. \n" +
        "______________________________________";
    }
}
