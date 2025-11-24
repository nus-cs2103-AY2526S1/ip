package woop.ui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import woop.logic.WoopLogic;


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

    private WoopLogic woop;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showInitialMessage();
    }

    /** Injects the Woop instance */
    public void setWoop(WoopLogic w) {
        woop = w;
    }

    public void showInitialMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getWoopDialog(Ui.showIntro())
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = woop.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getWoopDialog(response)
        );
        if (response.equals(Ui.showGoodbye())) {
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)
                    .execute(Platform::exit);
        }
        userInput.clear();
    }
}
