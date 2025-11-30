package briar.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Briar briar;

    // Source: https://leagueoflegends.fandom.com/wiki/Ezreal/Development?file=Crimson_Cobalt_Sparkle_Emote.png
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Ezreal.png"));
    // Source: https://leagueoflegends.fandom.com/wiki/Briar/Development?file=I_WAS_ROOTING_FOR_YOU_Emote.png
    private Image briarImage = new Image(this.getClass().getResourceAsStream("/images/Briar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Briar instance
     */
    public void setBriar(Briar briar) {
        this.briar = briar;
        String response = briar.greet();
        dialogContainer.getChildren().addAll(DialogBox.getBriarDialog(response, briarImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = briar.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBriarDialog(response, briarImage)
        );
        userInput.clear();
    }
}
