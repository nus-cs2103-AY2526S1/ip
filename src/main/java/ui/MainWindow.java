package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mark.Mark;

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

    private Mark mark;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image markImage = new Image(this.getClass().getResourceAsStream("/images/Mark.jpeg"));

    @FXML
    public void initialize() {
        Message introMessage = Message.normal(Ui.introMessage());
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getMarkDialog(introMessage, markImage)
        );

    }

    /** Injects the Mark instance */
    public void setMark(Mark d) {
        mark = d;
        String input = "remind";
        Message response = mark.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getMarkDialog(response, markImage)
        );
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mark's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Message response = mark.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMarkDialog(response, markImage)
        );
        userInput.clear();
    }

}

