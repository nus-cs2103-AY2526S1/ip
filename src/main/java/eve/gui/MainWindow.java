package eve.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import eve.ui.Ui;

/**
 * Controller for the main GUI.
 * <p>
 * This class manages the layout and interactions between the user interface
 * elements (text field, send button, dialog container) and the Eve backend
 * logic.
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

    private eve.Eve eve;

    private Image userImage;
    private Image eveImage;
    private final Ui ui = new Ui();

    /**
     * Initializes the main window after the FXML has been loaded.
     * <p>
     * This method is automatically called by the JavaFX framework. It:
     * <ul>
     * <li>Asserts that FXML elements were properly injected.</li>
     * <li>Binds the scroll pane to always follow the dialog container's height
     * (auto-scrolls to the latest message).</li>
     * <li>Loads user and Eve avatar images from the resources folder.</li>
     * <li>Adds a welcome message from Eve to the dialog container.</li>
     * </ul>
     */
    @FXML
    public void initialize() {

        assert scrollPane != null : "ScrollPane must be injected by FXML";
        assert dialogContainer != null : "Dialog container must be injected by FXML";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Optional debug logs
        System.out.println("Classpath root -> " + getClass().getResource("/"));
        System.out.println("Check /view/DialogBox.fxml -> " + getClass().getResource("/view/DialogBox.fxml"));
        System.out.println("Check /view/MainWindow.fxml -> " + getClass().getResource("/view/MainWindow.fxml"));

        assert getClass().getResourceAsStream("/images/EVE.PNG") != null : "Eve image resource not found";
        assert getClass().getResourceAsStream("/images/USER.PNG") != null : "User image resource not found";

        // Load user and Eve image from resources
        userImage = new Image(this.getClass().getResourceAsStream("/images/USER.PNG"));
        eveImage = new Image(this.getClass().getResourceAsStream("/images/EVE.PNG"));

        dialogContainer.getChildren().add(
                DialogBox.getEveDialog(ui.renderWelcome(), eveImage));
    }

    /**
     * Injects the {@link eve.Eve} instance into this controller.
     * <p>
     * This allows the GUI to communicate with the backend logic for generating
     * responses.
     *
     * @param e the Eve instance to be used for processing user input
     * @throws AssertionError if {@code e} is {@code null}
     */
    public void setEve(eve.Eve e) {
        assert e != null : "Injected Eve instance cannot be null";
        eve = e;
    }

    /**
     * Handles user input when the send button is clicked or Enter is pressed.
     * <p>
     * This method:
     * <ul>
     * <li>Retrieves the text entered by the user.</li>
     * <li>Gets the corresponding response from Eve.</li>
     * <li>Adds both the user's dialog and Eve's dialog to the dialog
     * container.</li>
     * <li>Clears the input field afterwards.</li>
     * </ul>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eve.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEveDialog(response, eveImage));
        userInput.clear();
    }
}
