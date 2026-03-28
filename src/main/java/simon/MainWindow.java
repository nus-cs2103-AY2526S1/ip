package simon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import simon.ui.Ui;

/**
 * Controller for the main GUI window of the Simon chatbot application.
 * A <code>MainWindow</code> handles user interactions and manages the chat interface.
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

    private Simon simon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image simonImage = new Image(this.getClass().getResourceAsStream("/images/DaSimon.png"));

    /**
     * Initializes the controller by setting up scroll behavior and displaying welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getSimonDialog(new Ui().showWelcome(), simonImage, "welcome")
        );
    }

    /**
     * Injects the Simon chatbot instance into this controller.
     *
     * @param d The Simon instance to use for processing commands.
     */
    public void setSimon(Simon d) {
        simon = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Simon's reply,
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            if (simon == null) {
                throw new IllegalStateException("Please give a valid command, its all given in the welcome message man...");
            }
            String response = simon.getResponse(input);
            String commandType = simon.getCommandType();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSimonDialog(response, simonImage, commandType)
            );
            if (commandType.equals("ExitCommand")) {
                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                        javafx.application.Platform.exit();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userInput.getText(), userImage),
                    DialogBox.getSimonDialog("Please give a valid command, its all in the welcome message man...", simonImage, "error")
            );
        } finally {
            userInput.clear();
        }
    }
}
