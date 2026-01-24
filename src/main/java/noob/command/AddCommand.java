package noob.command;

import noob.exception.NoobException;
import noob.storage.Storage;
import noob.task.Task;
import noob.task.TaskList;
import noob.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        int num = tasks.addTask(task);
        String s = "Got it. I've added this task:\n";
        String numTasks = String.format("Now you have %d tasks in the list.", num);

        try {
            storage.writeTasksToFile(tasks);
            return ui.displayMessage(s + "  " + task.toString() + "\n" + numTasks);
        } catch (NoobException e) {
            return ui.displayError(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddCommand c) {
            return this.task.equals(c.task);
        }
        return false;
    }
}
