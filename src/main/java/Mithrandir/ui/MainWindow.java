package Mithrandir.ui;

import java.util.Objects;

import Mithrandir.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Application mithrandir;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images" +
            "/Frodo" +
            ".png")));
    private final Image mithrandirImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/Gandalf.png")));

    /**
     * Initializes the main window.
     * Binds the scroll pane's vertical value to the dialog container's height
     * to enable automatic scrolling to the bottom when new messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input by processing the text and displaying both the user's input
     * and Mithrandir's response in the dialog container.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = this.mithrandir.getGUiResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        dialogContainer.getChildren().add(DialogBox.getMithrandirDialog(response, mithrandirImage));
        userInput.clear();
    }

    /**
     * Displays Mithrandir's greeting message in the dialog container.
     */
    @FXML
    public void greet() {
        dialogContainer.getChildren().add(DialogBox.getMithrandirDialog(this.mithrandir.greet(), mithrandirImage));
    }

    /**
     * Sets the Mithrandir application instance for this window.
     *
     * @param mithrandir The Mithrandir application instance to be used for processing user input
     */
    public void setMithrandir(Application mithrandir) {
        this.mithrandir = mithrandir;
    }
}
