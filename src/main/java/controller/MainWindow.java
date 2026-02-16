package controller;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.Ui;

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

    private ineffa.Ineffa ineffa = new ineffa.Ineffa("data/ineffa.txt");

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aether.jpg"));
    private Image ineffaImage = new Image(this.getClass().getResourceAsStream("/images/ineffa.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ineffa instance */
    public void setIneffa(ineffa.Ineffa b) {
        ineffa = b;
    }

    /**
     * Display greeting message when bot starts
     * */
    public void addGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getIneffaDialog(Ui.showWelcome(), ineffaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ineffa's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits program if user enters "bye".
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String ineffaText = ineffa.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getIneffaDialog(ineffaText, ineffaImage)
        );
        userInput.clear();
        if (Objects.equals(ineffaText, Ui.getExitMessage())) {
            Platform.exit();
        }
    }
}

