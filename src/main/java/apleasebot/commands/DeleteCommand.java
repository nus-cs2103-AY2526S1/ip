package apleasebot.commands;

import apleasebot.exceptions.IllegalBotArgumentException;
import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;

/**
 * Encapsulates the logic when a user says delete
 */
public class DeleteCommand implements Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        /* Checks for an argument and that the argument is an integer */
        if (input.length() < 7) {
            throw new IllegalBotArgumentException("No argument found!", input);
        }
        if (isNotInt(input, 7)) {
            throw new IllegalBotArgumentException("Argument is not integer!", input);
        }

        int num = Integer.parseInt(input.substring(7));
        if (isOutOfBounds(num, tasks)) {
            throw new IllegalBotArgumentException("Item out of bounds!", input);
        }

        String removed = tasks.getTask(num - 1).toString();
        tasks.removeTask(num - 1);

        return APleaseBot.LINE
                + "Got it. I've removed this task: \n"
                + "  " + removed + "\n"
                + "Now you have " + tasks.getItemCount() + " tasks in the list" + "\n"
                + APleaseBot.LINE;
    }
}
