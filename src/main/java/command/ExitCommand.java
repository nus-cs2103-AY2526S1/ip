package command;

import task.Event;
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
     * Executes the exit command: displays the {@link Ui}'s bye message, before
     * running the {@link Ui}'s closing sequence.
     *
     * @param tasklist the {@link TaskList} where tasks are stored
     * @param ui       the {@link Ui} that the user interacts with
     * @param storage  the {@link Storage} that retrieves and updates the save file
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.bye();
        ui.close();

    }

    /**
     * Returns true if the command exits the program.
     * Since ExitCommand is, by definition, a command that exits the program,
     * this method always returns {@code true}.
     *
     * @return {@code true}, as this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
