package peanutbutter.commands;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        ui.byeMessage();
        PauseTransition delay = new PauseTransition(Duration.millis(400));
        delay.setOnFinished(e -> Platform.exit());
        delay.play();
        return true;
    }

    @Override
    public boolean isExit() {
        return true; // used in Juin for UI exit handling
    }
}
