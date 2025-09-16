package apleasebot.commands;

import apleasebot.exceptions.DataException;
import apleasebot.exceptions.IllegalBotArgumentException;
import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;

/**
 * Encapsulates the logic for when the user says find ...
 */
public class FindCommand implements Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        if (input.length() < 5) {
            throw new IllegalBotArgumentException("No argument found!", input); // no argument
        }
        String keyphrase = input.substring(5);
        TaskList filteredTasks = tasks.search(keyphrase);
        filteredTasks.sort();

        if (tasks.getItemCount() < 1) {
            throw new DataException("No items loaded/stored yet");
        }
        return APleaseBot.LINE + filteredTasks.list() + APleaseBot.LINE;
    }
}
