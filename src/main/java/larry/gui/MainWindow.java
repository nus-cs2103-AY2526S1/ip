package larry.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import larry.LarryCore;

/** Controller for MainWindow.fxml. */
public class MainWindow {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private LarryCore larry;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image larryImage = new Image(this.getClass().getResourceAsStream("/images/Larry.png"));

    /** Bind autoscroll to the bottom when new content is added. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Inject Larry core logic. Called by Main after loading FXML. */
    public void setLarry(LarryCore core) {
        this.larry = core;

        dialogContainer.getChildren().add(
            DialogBox.getLarryDialog("Hello! I'm Larry.\nType 'help' to see what I can do.", larryImage)
        );
    }

    /** Handles Enter and Send. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }
        String reply = larry.getResponse(input);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getLarryDialog(reply, larryImage)
        );

        userInput.clear();

        if (larry.shouldExit()) {
            sendButton.setDisable(true);
            userInput.setDisable(true);
        }
    }
}
