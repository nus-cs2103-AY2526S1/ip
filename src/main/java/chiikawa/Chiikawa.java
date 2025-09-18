package chiikawa;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import chiikawa.command.Command;

/**
 * The Chiikawa class represents the main entry point of the program.
 * It takes care of initialising all other related classes and acts as the entry point
 * where the main program loop is ran.
 */
public class Chiikawa {
    private boolean isFirstMessage = true;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a new Chiikawa instance.
     *
     * @param filePath path to the storage file which contains all the saved tasks
     */
    public Chiikawa(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (ChiikawaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output = "";

        if (isFirstMessage) {
            isFirstMessage = false;
            return ui.showWelcome();
        }

        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            output = c.execute(tasks, ui, storage);

            if (c.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                    Platform.exit(); // Closes the JavaFX application
                });
                delay.play();
            }
        } catch (ChiikawaException e) {
            output = ui.showError(e.getMessage());
        }

        return output;
    }
}
