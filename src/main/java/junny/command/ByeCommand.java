package junny.command;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;


/**
 * Represents a command that exits the program.
 * Prints a goodbye message and closes the application after a short delay.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command by printing a farewell message
     * and scheduling the program to exit after 3 seconds.
     *
     * @param tasks   the current list of tasks
     * @param ui      the UI handler to display messages
     * @param storage the storage handler to save/load tasks
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printBye();

        // Wait 3 seconds before quitting
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Indicates that this command will exit the program.
     *
     * @return true, since the bye command ends the program
     */
    @Override
    public boolean isExit() {
        return true; // program should stop
    }
}
