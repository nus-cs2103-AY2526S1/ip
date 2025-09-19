package apleasebot.commands;

import apleasebot.exceptions.DataException;
import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;

/**
 * Encapsulates the logic for when the user says list
 */
public class ListCommand implements Command {

    @Override
    public String execute(TaskList tasks) {
        if (tasks.getItemCount() < 1) {
            throw new DataException("No items loaded/stored yet");
        }
        tasks.sort();
        return APleaseBot.LINE + tasks.list() + APleaseBot.LINE;
    }
}
