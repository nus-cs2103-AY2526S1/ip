package bobbywasabi;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the BobbyWasabi JavaFX application.
 * <p>
 * Handles the UI elements, user input, and interaction with the {@link BobbyWasabi} backend.
 * It manages the dialog container, scroll behavior, and sending/receiving messages.
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

    private BobbyWasabi bobbywasabi;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/adam.jpeg"));
    private Image bobbywasabiImage = new Image(getClass().getResourceAsStream("/images/BobbyWasabi.jpeg"));

    /**
     * Initializes the main window.
     * <p>
     * Binds the vertical scroll of the ScrollPane to the height of the dialog container,
     * and sets the preferred width of the dialog container to match the ScrollPane's viewport width.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(
                Bindings.createDoubleBinding(() -> scrollPane.getViewportBounds()
                                .getWidth(),
                        scrollPane.viewportBoundsProperty()
                )
        );
    }

    /**
     * Sets the {@link BobbyWasabi} instance used to process user commands.
     *
     * @param bobbywasabi The main BobbyWasabi instance.
     */
    public void setBobbyWasabi(BobbyWasabi bobbywasabi) {
        this.bobbywasabi = bobbywasabi;
    }

    /**
     * Handles user input from the TextField.
     * <p>
     * Retrieves the text entered by the user, sends it to the BobbyWasabi backend for processing,
     * and adds the user and bot dialog boxes to the dialog container. Clears the input field after sending.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = bobbywasabi.getResponse(input);
        String commandType = bobbywasabi.getCommandType();

        assert !response.trim().isEmpty()
                : "Bot response cannot be empty!";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(this.userImage, input),
                DialogBox.getBobbyWasabiDialog(this.bobbywasabiImage, response, commandType)
        );
        userInput.clear();
    }
}
