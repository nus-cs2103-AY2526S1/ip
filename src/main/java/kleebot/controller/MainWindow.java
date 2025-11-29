package kleebot.controller;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import kleebot.KleeBot;
import kleebot.view.DialogBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private KleeBot kleeBot;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Lumine.jpeg")));
    private Image kleeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Klee.jpeg")));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Klee instance */
    public void setKlee(KleeBot k) {
        kleeBot = k;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String userText = userInput.getText();
        String kleeText = kleeBot.getResponse(userText);

        assert !kleeText.isEmpty();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getKleeDialog(kleeText, kleeImage)
        );
        userInput.clear();

        if (userText.equals("bye")) { // Exits the controller

            // Runs the shutdown sequence asynchronously
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                // After delay, exit the application
                Platform.runLater(Platform::exit);
            }).start();

        }
    }


}
