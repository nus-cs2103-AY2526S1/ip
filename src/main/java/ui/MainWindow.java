package ui;

import app.Boop;
import app.Boop.BoopResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Boop boop;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image boopImage = new Image(this.getClass().getResourceAsStream("/images/DaBoop.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBoop(Boop b) {
        boop = b;

        sendMessage(boop.initialize());
        sendMessage(boop.loadTasks());
    }

    @FXML
    private void sendMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, boopImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        BoopResponse response = boop.getResponse(input);

        if (response.isExit()) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
            return;
        }

        String boopMessage = response.getMessage();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(boopMessage, boopImage));
        userInput.clear();
    }
}
