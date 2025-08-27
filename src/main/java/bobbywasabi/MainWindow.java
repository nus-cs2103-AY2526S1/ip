package bobbywasabi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Main extends AnchorPane {
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private BobbyWasabi bobbywasabi;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/andrew gar.jepg"));
    private Image bobbywasabiImage = new Image(getClass().getResourceAsStream("/images/BobbyWasabi.jpeg"));

    @FXML
    public void initialise() {
        scrollpane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBobbyWasabi(BobbyWasabi bobbywasabi) {
        this.bobbywasabi = bobbywasabi;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobbywasabi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(this.userImage, input),
                DialogBox.getBobbyWasabiDialog(this.bobbywasabiImage, response)
        );
        userInput.clear();
    }
}
