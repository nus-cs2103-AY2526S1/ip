package charli.command;

import charli.exception.CharliException;
import charli.task.Todo;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;
/**
 * Command to add a new todo task to the taslist.
 * Handles the 'drop' command for adding simple tasks without deadlines.
 */
public class AddTodoCommand implements Command {

    private static final String USAGE = "Use: bop [song name]";

    public String fullCommand;

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {

        String[] parts = fullCommand.split("\\s+");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new CharliException("Please specify the mysterious bop... " + USAGE);
        }

        String description = parts[1].trim(); // safe; guaranteed to exist here
        tasks.add(new Todo(description));

        return "HOT! Added this bop to your rotation:"
            + tasks.getLastTaskString()
            + "\n" + "Now you have " + tasks.size() + " tracks in your rotation!";
    }

    public boolean isExit() {
        return false;
    }
}
