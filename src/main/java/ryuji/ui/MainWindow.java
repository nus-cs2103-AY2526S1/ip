package ryuji.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for the main GUI window of the Ryuji application.
 * <p>This class manages the user interface, handling user input and displaying dialog boxes with user interactions
 * and Ryuji's responses. It binds the scroll pane to the dialog container, and allows sending messages back and forth
 * between the user and the Ryuji chatbot.</p>
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;  // Scroll pane for displaying chat history
    @FXML
    private VBox dialogContainer;  // VBox to hold the dialog boxes
    @FXML
    private TextField userInput;   // Text field for entering user input
    @FXML
    private Button sendButton;     // Button for sending user input

    private Ui ui = new Ui();

    private Ryuji ryuji;  // Instance of the Ryuji chatbot to handle responses

    private final Image userImage = new Image(this
            .getClass().getResourceAsStream("/images/UserPFP.png"));  // User profile picture
    private final Image ryujiImage = new Image(this.getClass()
            .getResourceAsStream("/images/RyujiPFP.png"));  // Ryuji profile picture

    /**
     * Initializes the main window.
     * Binds the vertical value of the scroll pane to the height of the dialog container,
     * and adds the welcome message from Ryuji to the dialog container.
     */
    @FXML
    public void initialize() {
        // Ensures the scroll pane follows the content as it grows
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Add a welcome message from Ryuji to the dialog container
        dialogContainer.getChildren().addAll(DialogBox.getRyujiDialog(Ui.showWelcome(), ryujiImage));
    }

    /**
     * Injects the Ryuji instance to be used for processing user inputs.
     *
     * @param ryuji the instance of the Ryuji chatbot to be used
     */
    public void setRyuji(Ryuji ryuji) {
        this.ryuji = ryuji;
    }

    /**
     * Handles the user's input, processes it using the Ryuji chatbot, and displays both the user's input and Ryuji's
     * response in the dialog container. Clears the input field after processing.
     * If the user types "bye", the application will exit after a 5-second delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();  // Retrieve user input
        String response = ryuji.getResponse(input);  // Get Ryuji's response

        // Add user input and Ryuji's response to the dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRyujiDialog(response, ryujiImage)
        );

        // Clear the input field
        userInput.clear();

        // Exit the application if the user types "bye"
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getRyujiDialog("here is your task list: " + ryuji.getFilePathForCurrentStorage()
                            , ryujiImage)
            );
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(1);  // Exit after 5 seconds
                    timer.cancel();
                }
            }, 5000);
        }
    }
}
