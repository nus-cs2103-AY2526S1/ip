package commands;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that terminates the program.
 * The {@code ByeCommand} displays a farewell message
 * and signals that the program should exit.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        message = ui.showBye();
        ui.showLine();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
