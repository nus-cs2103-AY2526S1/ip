package klalopz.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import klalopz.Klalopz;
import klalopz.ui.TextUi;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image klalopzImage = new Image(this.getClass().getResourceAsStream("/images/Klalopz.jpg"));
    private Klalopz klalopz;
    private TextUi textUi = new TextUi();

    @FXML
    public void initialize() {
        textUi.sayOpening();
        String fullOpeningMessage = String.join("\n", textUi.getMessages());
        dialogContainer.getChildren().add(DialogBox.getKlalopzDialog(fullOpeningMessage, klalopzImage));

        textUi.clearMessages();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setKlalopz(Klalopz klalopz) {
        this.klalopz = klalopz;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = klalopz.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKlalopzDialog(response, klalopzImage)
        );
        userInput.clear();
    }
}
