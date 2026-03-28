package sanbot.command;

import java.util.ArrayList;

import sanbot.task.Task;

/**
 * Interface for command objects following the Command pattern.
 */
public interface Command {

    /**
     * Executes the command with given input and task list.
     *
     * @param input the user input string that triggered this command.
     * @param taskList the list of tasks that may be modified by the command execution.
     * @return the message to be displayed to user upon execution of command.
     */
    String execute(String input, ArrayList<Task> taskList);
}
