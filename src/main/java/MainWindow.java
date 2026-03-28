import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dume.Dume;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dume dume;


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getDumeDialog("Hello! I'm DUM-E\nWhat can I do for you?")
        );
    }

    public void setDume(Dume d) {
        dume = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dume.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDumeDialog(response)
        );
        userInput.clear();
        
        // Check if user said bye and terminate the application
        if (input.trim().toLowerCase().equals("bye")) {
            Platform.exit();
        }
    }
}