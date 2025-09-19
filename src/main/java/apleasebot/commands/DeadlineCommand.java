package apleasebot.commands;

import apleasebot.exceptions.IllegalBotArgumentException;
import apleasebot.tasks.Deadline;
import apleasebot.tasks.Task;
import apleasebot.tasks.TaskList;
import apleasebot.ui.APleaseBot;
import apleasebot.ui.TimeFormatter;

/**
 * Encapsulates the logic when a user says deadline ...
 */
public class DeadlineCommand implements Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        /* Checks if the user has input an argument */
        if (input.length() < 9) {
            throw new IllegalBotArgumentException("No argument found!", input);
        }

        /* Split the substring around the \by syntax */
        /* Solution of using 4 '\' was found by searching on Google */
        String[] sub = input.substring(9).split("\\\\by");
        /* Sanity check to ensure that there are 2 arguments */
        if (sub.length != 2) {
            throw new IllegalBotArgumentException("Wrong number of arguments!", input);
        }
        String task = sub[0];
        String deadline = sub[1].stripLeading().stripTrailing();

        /* Checks each argument for valid argument */
        if (task.isEmpty()) {
            throw new IllegalBotArgumentException("No Task stated:", input);
        }
        if (deadline.isEmpty()) {
            throw new IllegalBotArgumentException("No Deadline stated:", input);
        }

        Task newTask = new Deadline(task, false, TimeFormatter.getStandard(deadline));
        tasks.addTask(newTask);
        tasks.sort();


        return APleaseBot.LINE
                + "Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + "Now you have " + tasks.getItemCount() + " tasks in the list" + "\n"
                + APleaseBot.LINE;

    }
}
