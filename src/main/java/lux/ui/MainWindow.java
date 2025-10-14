package lux.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lux.Lux;

/**
 * Controller for the main JavaFX window. Handles user input and displays
 * dialog boxes containing user messages and Lux responses.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lux lux;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/akchtually.jpg"));
    private Image luxImage = new Image(this.getClass().getResourceAsStream("/images/chill_cat.jpg"));

    /**
     * Initializes the controller after the root element has been completely
     * processed by the FXML loader.
     *
     * This method binds the vertical scroll value of the {@link #scrollPane}
     * to the height of the {@link #dialogContainer} so the view automatically
     * stays scrolled to the bottom when new dialog nodes are added. It also
     * enables width-expansion behaviour so dialog content fills the available
     * horizontal space when the window is resized or maximized.
     */
    @FXML
    public void initialize() {
        // Keep the scroll always scrolled to the bottom when new messages arrive
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Make the scroll pane content and dialog boxes expand to the window width
        scrollPane.setFitToWidth(true);
        dialogContainer.setFillWidth(true);
    }

    /**
     * Inject the {@link Lux} application instance into the controller and
     * display the initial welcome message.
     *
     * @param l Lux application instance
     */
    public void setLux(Lux l) {
        lux = l;
        dialogContainer.getChildren().addAll(DialogBox.getLuxDialog(lux.showWelcome(), luxImage));
    }

    /**
     * Handle the user clicking the send button / pressing Enter. This method
     * sends the input to {@link Lux#getResponse}, displays both user and
     * Lux dialog boxes and clears the input field. If the application
     * should exit, it schedules the window to close after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lux.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLuxDialog(response, luxImage));
        userInput.clear();

        if (lux.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}
