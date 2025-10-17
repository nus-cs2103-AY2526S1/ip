package cuteowl.command;

import cuteowl.note.NoteList;
import cuteowl.storage.Storage;
import cuteowl.task.Task;
import cuteowl.task.TaskList;
import cuteowl.ui.Ui;

public class FindCommand extends Command {
    public final String description;
    private String output;

    public FindCommand(String description) {
        assert !description.trim().isEmpty() : "Search description must not be empty";
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, NoteList notes) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks.getAll()) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }
        ui.showTaskList(matchingTasks);
        output = ui.showTaskListGUI(matchingTasks);
    }

    @Override
    public String getString() {
        return output;
    }

}
