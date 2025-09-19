package eureka;

import eureka.command.Command;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main Eureka GUI window defined in {@code MainWindow.fxml}.
 * <p>
 * This class manages user interactions with the GUI:
 * <ul>
 *   <li>Handles user text input from the input field</li>
 *   <li>Displays user and Eureka dialog messages in the dialog container</li>
 *   <li>Delegates command processing to the {@link Eureka} backend</li>
 *   <li>Closes the application when an exit command is issued</li>
 * </ul>
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

    private Eureka eureka;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image eurekaImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window controller.
     * <p>
     * Binds the scroll pane to automatically scroll to the bottom
     * whenever new dialog boxes are added to the container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@link Eureka} instance into this controller.
     * <p>
     * This is called by {@link Main} after loading the FXML.
     *
     * @param d the {@code Eureka} instance to connect with the UI
     */
    public void setEureka(Eureka d) {
        eureka = d;
    }

    /**
     * Handles user input from the text field when the send button is clicked
     * or the Enter key is pressed.
     * <p>
     * The flow is as follows:
     * <ol>
     *   <li>Retrieve the user's input</li>
     *   <li>Parse it into a {@link Command}</li>
     *   <li>Execute the command via the {@link Eureka} backend</li>
     *   <li>Display both the user's message and Eureka's response in dialog boxes</li>
     *   <li>Exit the application if the command is an exit command</li>
     *   <li>Clear the input field</li>
     * </ol>
     * If the input is invalid, an error message is displayed instead of a normal response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }
            String response = c.execute(eureka.getTasks(), eureka.getUi(), eureka.getStorage());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getEurekaDialog(response, eurekaImage)
            );
        } catch (IllegalArgumentException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getEurekaDialog(e.getMessage(), eurekaImage)
            );
        } finally {
            userInput.clear();
        }
    }
}
