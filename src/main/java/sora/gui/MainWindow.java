package sora.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sora.Sora;
import sora.SoraException;

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

    private Sora sora = new Sora("./data/sora.txt");

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the GUI.
     * <p>
     * This method is automatically called by JavaFX after the FXML
     * elements have been loaded. It performs the following:
     * </p>
     * <ul>
     *   <li>Binds the vertical scroll position of the {@code scrollPane} to
     *       the height of the {@code dialogContainer}, ensuring the scroll
     *       automatically moves to show the latest dialog.</li>
     *   <li>Adds an initial welcome message from Sora to the {@code dialogContainer}.</li>
     * </ul>
     * <p>
     * The welcome message is displayed as a {@link DialogBox} with the Sora image.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getSoraDialog("Hello! I'm Sora\nWhat can I do for you today?", dukeImage)
        );
    }

    /** Injects the Duke instance */
    public void setSora(Sora s) {
        sora = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input must not be null";

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        try {
            String response = Sora.getResponse(input);
            assert response != null : "Sora response must not be null";

            dialogContainer.getChildren().add(
                    DialogBox.getSoraDialog(response, dukeImage)
            );
        } catch (SoraException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog("⚠ " + e.getMessage(), dukeImage)
            );
        }

        userInput.clear();
    }
}

