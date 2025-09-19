package apleasebot.commands;

import apleasebot.exceptions.IllegalBotArgumentException;
import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;

/**
 * Encapsulates the logic for when the user says mark
 */
public class MarkCommand implements Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        /* Checks for argument */
        if (input.length() < 5) {
            throw new IllegalBotArgumentException("No argument found!", input);
        }
        if (isNotInt(input, 5)) {
            throw new IllegalBotArgumentException("Argument is not integer!", input);
        }

        int num = Integer.parseInt(input.substring(5));

        if (isOutOfBounds(num, tasks)) {
            throw new IllegalBotArgumentException("Item out of bounds!", input);
        }

        tasks.getTask(num - 1).markDone();

        return APleaseBot.LINE
                + "Nice! I've marked this task as done:\n"
                + tasks.getTask(num - 1).toString() + "\n"
                + APleaseBot.LINE;
    }
}
