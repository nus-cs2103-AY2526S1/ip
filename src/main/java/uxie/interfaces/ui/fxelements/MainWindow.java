package uxie.interfaces.ui.fxelements;

import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import uxie.Uxie;
import uxie.interfaces.ui.GuiMain;
import uxie.interfaces.ui.Ui;

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

    private Uxie uxie;

    private Image userImage = new Image(this.getClass().getResourceAsStream(
            "/images/mel1.jpg"));
    private Image uxieImage = new Image(this.getClass().getResourceAsStream(
            "/images/uxie1.jpg"));

    /**
     * Initializes MainWindow.
     * Shows welcome DialogBox from Uxie.
     */
    @FXML
    public void initialize() {
        // ensure images are not null
        assert uxieImage != null : "MainWindow: uxieImage is null";
        assert userImage != null : "MainWindow: userImage is null";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getUxieDialog(Ui.WELCOME, uxieImage)
        );

        Optional<Font> textFieldFont = GuiMain.getFont();
        // to verify it is actually obtained
        assert textFieldFont.isPresent() : "MainWindow: textFieldFont is empty";
        textFieldFont.ifPresent(font -> {
            userInput.setFont(font);
            sendButton.setFont(font);
        });
    }

    /** Injects the Uxie instance */
    public void setUxie(Uxie uxie) {
        this.uxie = uxie;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Uxie's response, then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = uxie.getResponse(input);
        if (response.equals(Ui.GOODBYE)) {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getUxieDialog(response, uxieImage)
        );
        userInput.clear();
    }
}
