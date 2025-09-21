package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents the command to display the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs a {@link ListCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Executes the list command: displays the list of tasks.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.chatbotPrint(tasklist.toString());
    }
}
