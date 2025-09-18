package alune.ui;

import alune.Alune;
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

    private Alune alune;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image aluneImage = new Image(this.getClass().getResourceAsStream("/images/alune.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAlune(Alune a) {
        alune = a;
        String aluneText = alune.getResponse("hi");
        dialogContainer.getChildren().addAll(DialogBox.getAluneDialog(aluneText, aluneImage));
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String aluneText = alune.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getAluneDialog(aluneText, aluneImage));
        userInput.clear();
        if (userText.equals("bye")) {
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(
                    javafx.util.Duration.millis(500));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }
}
