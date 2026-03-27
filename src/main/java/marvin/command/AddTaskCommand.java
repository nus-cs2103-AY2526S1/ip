package marvin.command;

import marvin.Personality;
import marvin.task.Task;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for the Add Task command in Marvin.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Instantiate the AddTaskCommand with the task to be added to the list.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.addToList(this.task);
        return new CommandResult(() -> Ui.printGeneric(
                        Personality.getItemAddedText(task.getDescription(), taskList.getCount())
                    ),
                // use the colorless version for GUI
                Personality.getItemAddedTextColorless(task.getDescription(), taskList.getCount())
        );
    }
}
