package cortana.gui;

import cortana.core.Cortana;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private Cortana cortana;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MasterChief.png"));
    private Image cortanaImage = new Image(this.getClass().getResourceAsStream("/images/Cortana.png"));
    /**
     * Initializes the controller after all FXML fields have been injected and the UI components are ready.
     * <p>
     * Binds the vertical scroll value property of the scrollPane to the height property of the dialogContainer,
     * ensuring that the scroll pane automatically scrolls to the bottom as new dialog boxes are added.
     * </p>
     * <p>
     * Adds an initial dialog box containing the output of the {@code cortana.initialize()} method along with
     * the associated Cortana image to the dialogContainer, effectively displaying the introductory message
     * when the application UI loads.
     * </p>
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /**
     * Injects the Cortana instance and shows introductory message
     */
    public void setCortana(Cortana cortana) {
        this.cortana = cortana;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(cortana.initialize(), cortanaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Also handles user request to exit the app
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cortana.getResponse(input);
        if (response.equals("Exit")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Goodbye");
            alert.setHeaderText(null);
            alert.setContentText("Logging off. See you soon!");
            // Show alert and wait for user to close it before exiting
            alert.showAndWait();
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, cortanaImage)
        );
        userInput.clear();
    }
}
