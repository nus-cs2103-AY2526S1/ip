package momo.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import momo.Momo;

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

    private Momo momo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUserCircle.png"));
    private Image momoImage = new Image(this.getClass().getResourceAsStream("/images/DaMomoCircle.png"));

    /**
     * Initializes the controller.
     *
     * <p>Binds the scroll pane to the dialog container height and shows Momo's welcome message.</p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Platform.runLater(() -> {
            dialogContainer.getChildren().add(
                    DialogBox.getMomoDialog(momo.getWelcomeMessage(), momoImage, "Greet")
            );
        });
    }

    /** Injects the Momo instance */
    public void setMomo(Momo m) {
        momo = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Momo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            userInput.clear();
            return;
        }

        boolean isExit = momo.isExit(input);
        String response = momo.getResponse(input);
        String commandType = momo.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMomoDialog(response, momoImage, commandType)
        );
        if (isExit) {
            // Delay a little to show the bye message, then exit
            Platform.runLater(Platform::exit);
        }
        userInput.clear();
    }
}
