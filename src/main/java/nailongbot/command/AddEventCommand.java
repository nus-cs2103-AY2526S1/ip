package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Command to add a new event task.
 */
public class AddEventCommand implements Command {
    private String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    public String getArguments() {
        return arguments;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        tasks.addEvent(arguments);
        storage.saveTasks(tasks.getTasks());

        String message = "Got it. I've added this event:\n"
                + tasks.getTask(tasks.size() - 1).toString()
                + "\nNow you have " + tasks.size() + " tasks in the list\n";

        return Ui.LINE + message + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
