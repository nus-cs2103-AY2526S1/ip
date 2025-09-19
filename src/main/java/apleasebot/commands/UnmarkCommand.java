package apleasebot.commands;

import apleasebot.exceptions.IllegalBotArgumentException;
import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;

/**
 * Encapsulates the logic for when a user says unmark
 */
public class UnmarkCommand implements Command {
    // fields
    private final String input;


    // constructor
    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        /* Checks if argument was inputted */
        if (input.length() < 7) {
            throw new IllegalBotArgumentException("No argument found!", input);
        }
        if (isNotInt(input, 7)) {
            throw new IllegalBotArgumentException("Argument is not integer!" , input);
        }

        int num = Integer.parseInt(input.substring(7));

        if (isOutOfBounds(num, tasks)) {
            throw new IllegalBotArgumentException("Item out of bounds!", input);
        }
        tasks.getTask(num - 1).markUndone();

        return APleaseBot.LINE
                + "Ok! I've marked this task as not done yet:\n"
                + tasks.getTask(num - 1).toString() + "\n"
                + APleaseBot.LINE;
    }
}
