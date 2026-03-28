package ozil.command;

import ozil.main.TaskList;

/**
 * The command to list all current tasks.
 */
public class ListTasksCommand extends Command {
    private String userInput;

    /**
     * Constructor for list tasks command
     * @param userInput String input by user
     */
    public ListTasksCommand(String userInput) {
        assert !userInput.isEmpty();
        this.userInput = userInput;
    }

    @Override
    public String run(TaskList tasks) {
        return tasks.printlist();
    }
}
