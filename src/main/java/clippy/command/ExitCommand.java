package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Represents a command to exit the application.
 * When executed, it displays a goodbye message and exits the application after a short delay.
 * The delay was created with the help of Copilot AI
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (Platform.isFxApplicationThread()) {
            ui.goodbye();
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        } else {
            ui.goodbye();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                // do nothing
            }
            Platform.exit();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
