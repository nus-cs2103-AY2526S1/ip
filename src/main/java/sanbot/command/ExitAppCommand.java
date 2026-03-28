package sanbot.command;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import sanbot.storage.Storage;
import sanbot.task.Task;
import sanbot.ui.Ui;

/**
 * Represents the command to close the chatbot window.
 */
public class ExitAppCommand implements Command {

    /**
     * The time delay before exiting the app.
     */
    private static final double EXIT_DELAY_SECONDS = 4;

    /**
     * Command to exit the app and display exit message to user.
     *
     * @param input the user input string that triggered this command.
     * @param taskList the list of tasks.
     * @return response to inform user that currently exiting chatbot.
     */
    public String execute(String input, ArrayList<Task> taskList) {
        Storage.saveTasks(taskList);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(EXIT_DELAY_SECONDS),
                e -> System.exit(0)));

        timeline.play();

        return Ui.exitApp();
    }
}
