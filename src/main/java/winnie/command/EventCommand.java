package winnie.command;

import winnie.storage.Storage;
import winnie.task.Event;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            ui.showTaskAdded(event, tasks.getTaskCount());
            storage.saveTasksToFile(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
