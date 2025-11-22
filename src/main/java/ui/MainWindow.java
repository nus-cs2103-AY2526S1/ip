package ui;

import java.util.Objects;

import app.GenieWeenie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private GenieWeenie genie;

    private final Image userImage;
    private final Image genieImage;

    {
        userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/alaadin.jpg")));
        genieImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/genie.jpg")));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setPepe(GenieWeenie genie) {
        this.genie = genie;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = genie.getResponse(input);

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        // Determine if the response is an error
        String commandType = response.startsWith("Error") ? "error" : "normal";

        dialogContainer.getChildren().add(
                DialogBox.getGenieDialog(response, genieImage, commandType)
        );

        userInput.clear();
    }
}
