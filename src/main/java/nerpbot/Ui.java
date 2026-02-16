package nerpbot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Ui class handles all interactions with the user.
 */
public class Ui {
    private Stage helpStage;

    /**
     * Returns the welcome message for the chatbot.
     *
     * @return The welcome message string.
     */
    public String showWelcome() {
        return "Hello! I'm NerpBot :)\nWhat can I do for you?\nType 'help' if you're unsure what to say :D";
    }

    /**
     * Returns a message indicating the help window is opening.
     *
     * @return The help window opening message.
     */
    public String showHelp() {
        return "Opening help window... Type any message to continue chatting.";
    }

    /**
     * Shows the help window in the GUI.
     */
    public void showHelpWindow() {
        // This method would open a GUI help window in a full application.
        try {
            // Create help window if it doesn't exist
            if (helpStage == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HelpWindow.fxml"));
                Parent root = loader.load();

                helpStage = new Stage();
                helpStage.setTitle("NerpBot Help");
                helpStage.setScene(new Scene(root));
                helpStage.setResizable(false);
                helpStage.initModality(Modality.NONE); // Non-modal so user can interact with main window
            }

            if (!helpStage.isShowing()) {
                helpStage.show();
            } else {
                helpStage.toFront();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an error message formatted for the chatbot.
     *
     * @param message The error message to display.
     * @return The formatted error message.
     */
    public String showError(String message) {
        return "OOPSIE POOPSIE!!! " + message;
    }

    /**
     * Returns the exit message for the chatbot.
     *
     * @return The exit message string.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }
}
