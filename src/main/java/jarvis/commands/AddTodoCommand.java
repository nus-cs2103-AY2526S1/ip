package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.tasks.ToDoTask;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private final String desc;
    private String message; // what the GUI will show

    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DarrenAssistantException, IOException {
        if (desc.isBlank()) {
            throw new DarrenAssistantException("Todo needs a description!");
        }

        Task t = new ToDoTask(desc);
        tasks.add(t);

        // Build a message using Ui (instance method that RETURNS text)
        // e.g., in Ui.java: public String formatAdded(Task t, int total) { ... }
        message = ui.formatAdded(t, tasks.size());

        // Persist
        storage.save(tasks.asList());
    }

    @Override
    public String getString() {
        // Duke.getResponse(...) should return this to the GUI
        return message != null ? message : "Added: " + desc;
    }
}
