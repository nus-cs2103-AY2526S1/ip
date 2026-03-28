package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.ui.Ui;

/**
 * Command for editing tasks in the Tweety application.
 */
public class EditCommand extends Command {
    private int taskNumber;
    private String field;
    private String newValue;

    /**
     * Constructs a new EditCommand with the specified task number, field and newValue
     *
     * @param taskNumber The 1-based index of the task to delete.
     * @param field The field that user wants to edit
     * @param newValue The new value for the field that is edited
     *
     */
    public EditCommand(int taskNumber, String field, String newValue) {
        super(CommandType.EDIT);
        this.taskNumber = taskNumber;
        this.field = field;
        this.newValue = newValue;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task editedTask = tasks.editTask(taskNumber, field, newValue);

        storage.saveTasks(tasks);

        ui.printEditedTask(editedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

