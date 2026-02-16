package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.datetime.StringTime;
import july.error.JulyException;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Command to check for tasks that are not completed on a specific date.
 * This command allows users to query for incomplete tasks on a given date
 * and displays them to the user.
 */
public class CheckCommand extends Command {
    private final String argument;

    /**
     * Constructs a CheckCommand with the specified date argument.
     *
     * @param argument the date string to check for incomplete tasks
     */
    public CheckCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes the check command to find and display incomplete tasks on the specified date.
     * Check the date format and find tasks that are not completed on the given date.
     *
     * @param tasks the ArrayList of tasks to search through
     * @param ui the user interface component for displaying output
     * @param storage the storage component for data persistence
     * @throws JulyException if the date format is invalid
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws JulyException {
        StringTime parsedDate = new StringTime(argument);
        if (parsedDate.isString() || parsedDate.isTimeOnly()) {
            throw new JulyException("Please give a valid date: example dd/mm/yyyy");
        }

        ArrayList<String> responses = new ArrayList<>();
        responses.add(String.format("Okay let me check if you have any task not done yet on %s", parsedDate));

        for (Task task : tasks) {
            if (task.check(parsedDate)) {
                responses.add(task.toString());
            }
        }
        
        addResponses(responses.toArray(new String[0]));
    }
}
