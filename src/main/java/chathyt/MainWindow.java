package chathyt;

import chathyt.command.Command;
import chathyt.exception.InvalidCommandException;
import chathyt.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static chathyt.ChatHYT.storage;
import static chathyt.ChatHYT.tasks;

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

    private ChatHYT chatHYT;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/Images/ChatHYT6.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/Images/chatHYT_image3.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnAction(event -> handleUserInput());
        sendButton.setOnAction(event -> handleUserInput());
    }

    /** Injects the ChatHYT instance */
    public void setChatHYT(ChatHYT d) {
        chatHYT = d;

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            Command command = Parser.parse(input); // 🔑 Use Parser here
            response = command.execute(tasks, storage, new Ui());
        } catch (InvalidCommandException e) {
            response = Ui.showError(e.getMessage());
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.toLowerCase().contains("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}