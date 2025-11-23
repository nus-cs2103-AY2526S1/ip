package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a {@link ExitCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the exit command: displays the {@link Ui}'s bye message.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Returns the help message associated with the exit command
     *
     * @return the corresponding help message
     */
    @Override
    public String getHelpMessage() {
        return """
                exit:
                exits the program.
                format: exit""";
    }
}
