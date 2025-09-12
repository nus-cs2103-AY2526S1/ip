package bobbywasabi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private BobbyWasabi bobbywasabi;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/adam.jpeg"));
    private Image bobbywasabiImage = new Image(getClass().getResourceAsStream("/images/BobbyWasabi.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.viewportBoundsProperty().map(bounds -> bounds.getWidth()));
    }

    public void setBobbyWasabi(BobbyWasabi bobbywasabi) {
        this.bobbywasabi = bobbywasabi;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = bobbywasabi.getResponse(input);
        String commandType = bobbywasabi.getCommandType();

        assert !response.trim().isEmpty()
                : "Bot response cannot be empty!";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(this.userImage, input),
                DialogBox.getBobbyWasabiDialog(this.bobbywasabiImage, response, commandType)
        );
        userInput.clear();
    }
}
