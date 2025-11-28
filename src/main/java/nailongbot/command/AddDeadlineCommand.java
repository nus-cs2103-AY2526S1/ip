package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;

/**
 * Command to add a new deadline task.
 */
public class AddDeadlineCommand implements Command {
    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addDeadline(input);
        storage.saveTasks(tasks.getTasks());
        assert !tasks.isEmpty();

        String message = "Got it. I've added this deadline:\n"
                + tasks.getTask(tasks.size() - 1).toString()
                + "\nNow you have " + tasks.size() + " tasks in the list\n";

        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
