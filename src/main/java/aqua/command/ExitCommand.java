package aqua.command;

import aqua.task.TaskList;

/**
 * Command to exit the Aquapplication.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message.
     */
    @Override
    public String execute(TaskList taskList) {
        System.exit(0);
        
        // This line will never be reached, but is required for compilation.
        return "Bye bye! Hope to see you again soon!";
    }

    /**
     * @return True. Command will exit chatbot
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
