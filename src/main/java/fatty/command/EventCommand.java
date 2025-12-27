package fatty.command;

import java.time.LocalDateTime;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;
import fatty.task.EventTask;

/**
 * Create Event Task with given from and to times.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Create event Task in tasklist
     * @param description task description
     * @param from time event starts
     * @param to time event ends
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        EventTask event = new EventTask(description, from, to);

        assert event instanceof EventTask : "task should be EventTask";
        taskList.addTask(event);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(event, taskList);
    }
}
