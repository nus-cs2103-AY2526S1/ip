package arn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
public class MainWindow extends AnchorPane {
    @FXML
    protected ScrollPane scrollPane;
    @FXML
    protected VBox dialogContainer;
    @FXML
    protected TextField userInput;
    @FXML
    protected Button sendButton;

    protected Arn arn;

    protected Image arnImage = new Image(this.getClass().getResourceAsStream("/images/ArnLogo.png"));
    protected Image userImage = new Image(this.getClass().getResourceAsStream("/images/ArnUser.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setArn(Arn a) {
        arn = a;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = arn.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArnDialog(response, arnImage)
        );
        userInput.clear();
    }
}
