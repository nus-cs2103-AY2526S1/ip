package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tsunderechan.TsundereChan;
import tsunderechan.ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TsundereChan tsundereChan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Okabe.jpg"));
    private Image tsundereImage = new Image(this.getClass().getResourceAsStream("/images/Kurisu.jpg"));

    /**
     * Initializes the app with GUI and welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getTsundereDialog(Ui.showWelcome(), tsundereImage));
    }

    /** Injects the TsundereChan instance */
    public void setTsundereChan(TsundereChan tc) {
        tsundereChan = tc;
        if (tc.getLoadErrorMessage() != null) {
            showLoadingError(tc.getLoadErrorMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TsundereChan's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tsundereChan.getResponse(input);
        String commandType = tsundereChan.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, commandType),
                DialogBox.getTsundereDialog(response, tsundereImage, commandType)
        );
        userInput.clear();

        if ("ExitCommand".equals(commandType)) {
            this.onExitCommand();
        }
    }

    /**
     * Creates a TsundereChan dialog box with loading error dialog message when there is a loading error.
     */
    @FXML
    public void showLoadingError(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getTsundereDialog(message, tsundereImage, "InvalidCommand")
        );
    }

    /**
     * Delays exit of app so the goodbye message is visible
     * Method written with help of ChatGPT
     */
    private void onExitCommand() {
        javafx.animation.PauseTransition delay =
                new javafx.animation.PauseTransition(javafx.util.Duration.seconds(2));
        delay.setOnFinished(event -> javafx.application.Platform.exit());
        delay.play();
    }
}
