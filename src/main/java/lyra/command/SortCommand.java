package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

public class SortCommand extends Command {
    private final String sortCriteria;

    public SortCommand(String sortCriteria) {
        this.sortCriteria = sortCriteria.toLowerCase();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LyraException {
        if (taskList.isEmpty()) {
            ui.showMessage("Your task list is empty. Nothing to sort!");
            return;
        }

        switch (sortCriteria) {
            case "description":
                taskList.sortByDescription();
                ui.showMessage("Tasks sorted by description alphabetically.");
                break;
            case "deadline":
                taskList.sortByDeadline();
                ui.showMessage("Tasks sorted by deadline chronologically.");
                break;
            case "event":
                taskList.sortByEventStart();
                ui.showMessage("Tasks sorted by event start date chronologically.");
                break;
            case "type":
                taskList.sortByType();
                ui.showMessage("Tasks sorted by type (Todo, Deadline, Event).");
                break;
            case "status":
                taskList.sortByStatus();
                ui.showMessage("Tasks sorted by completion status (incomplete first).");
                break;
            default:
                throw new LyraException("Invalid sort criteria. Available options: description, deadline, event, type, status");
        }
    }
}
