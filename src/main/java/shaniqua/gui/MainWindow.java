package shaniqua.gui;

import java.util.concurrent.CompletableFuture;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import shaniqua.Shaniqua;
import shaniqua.ui.Ui;


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

    private Shaniqua shaniqua;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-avatar.jpg"));
    private Image shanImage = new Image(this.getClass().getResourceAsStream("/images/shaniqua-avatar.jpg"));

    @FXML
    public void initialize() {
        String greetingString = "Kia Ora! I'm Shaniqua! What can I do you for?";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getOutputDialog(greetingString, shanImage)
        );
    }

    /**
     * Injects the Duke instance
     */
    public void setBot(Shaniqua bot) {
        shaniqua = bot;
        ui = shaniqua.getUi();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CompletableFuture.supplyAsync(() -> {
            return ui.handle(input, shaniqua);
        }).thenAccept(response -> {
            Platform.runLater(() -> {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getOutputDialog(response, shanImage)
                );
                userInput.clear();
            });
        }).thenRun(() -> {
            if (ui.shouldExit()) {
                handleExit();
            }
        });
    }

    private void handleExit() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                    Platform.exit();
                    System.exit(0);
                })
        );
        timeline.play();
    }
}