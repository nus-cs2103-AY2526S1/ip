package command;

import exceptions.EventMissingDescriptionException;
import exceptions.EventMissingFromException;
import exceptions.EventMissingToException;
import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;
import task.Event;
import task.Task;

/**
 * Command to save task as event.
 */
public class EventCommand extends Command {
    private final String arg;

    public EventCommand(String arg) {
        this.arg = arg.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert arg != null : "Input cannot be null";
        String[] parts = arg.split("/from", 2);

        if (parts[0].isBlank()) {
            throw new EventMissingDescriptionException();
        }

        if (parts.length < 2) {
            throw new EventMissingFromException();
        }

        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new EventMissingToException();
        }

        Task task = new Event(parts[0], timeParts[0], timeParts[1], false);
        taskList.add(task, storage);
        System.out.println(task.getAddMessage(taskList.size()));
    }
}
