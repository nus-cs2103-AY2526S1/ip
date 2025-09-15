package baymax;

import java.io.IOException;

import baymax.command.Command;
import baymax.exception.BaymaxException;
import baymax.parser.Parser;
import baymax.storage.Storage;
import baymax.task.TaskList;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Baymax {
    protected boolean isError;
    private Storage storage;
    private TaskList tasks;
    private double EXIT_DELAY = 1.5;

    public Baymax(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.isError = false;
    }

    public void start() throws IOException {
        tasks = storage.load();
    }

    private void exit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(EXIT_DELAY));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Generates a response for the user's message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks);

            try {
                storage.save(tasks);
            } catch (IOException e) {
                this.isError = true;
                return "Error saving tasks: " + e.getMessage();
            }

            if (command.isExit()) {
                exit();
            }

            return response;

        } catch (BaymaxException e) {
            this.isError = true;
            return e.getMessage();
        } catch (NumberFormatException e) {
            this.isError = true;
            return "Hmm… that does not appear to be a valid task number. "
                    + "Please provide a whole number so I may help you.";
        }
    }
}
