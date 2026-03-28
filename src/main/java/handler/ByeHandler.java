package handler;

import gui.Main;
import hachi.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Handles the {@link CommandCode#BYE} command.
 *
 * <p>This handler ensures the current tasks are persisted to storage,
 * then schedules the application window to close after a short delay.
 * A farewell message is returned for display in the UI.</p>
 */
public class ByeHandler implements Manager {
    private final Storage storage;

    /**
     * Constructs a {@code ByeHandler} with the given storage dependency.
     *
     * @param storage the storage mechanism used to save tasks before exiting
     */
    public ByeHandler(Storage storage) {
        this.storage = storage;
    }

    /**
     * Saves the current task list to storage, initiates application shutdown
     * after a 2-second delay, and returns the farewell message.
     *
     * @param tasks the task list to save before exiting
     * @return a goodbye message to be shown to the user
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            storage.write(tasks.getTasks());
        } catch (IOException e) {
            Ui.failure(CommandCode.BYE);
        }
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Main.getPrimaryStage().close());
        delay.play();
        return Ui.end();
    }
}
