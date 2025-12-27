package ip.ui;

import ip.Squiddy;
import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DefaultUser.jpg"),
            80, 80, true, true);
    private Image squiddyImage = new Image(this.getClass().getResourceAsStream("/images/Squiddy.jpg"),
            80, 80, true, true);

    private Squiddy squiddy = new Squiddy();

    //Used Deepseek to add scrolling
    @FXML
    public void initialize() {
        // Use this instead of binding for better control
        dialogContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            Platform.runLater(() -> {
                scrollPane.setVvalue(1.0);
            });
        });

        // Enable mouse wheel scrolling
        dialogContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            if (deltaY != 0) {
                double current = scrollPane.getVvalue();
                scrollPane.setVvalue(current - deltaY / scrollPane.getHeight());
            }
        });

        // Make scroll pane focusable
        scrollPane.setFocusTraversable(true);

        // Add initial content
        dialogContainer.getChildren().add(
                DialogBox.getSquiddyDialog(squiddy.getWelcomeMsg(), squiddyImage)
        );

        // Request focus after UI is shown
        Platform.runLater(() -> scrollPane.requestFocus());
    }

    public void setSquiddy(Squiddy s) {
        squiddy = s;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String squiddyText = squiddy.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getSquiddyDialog(squiddyText, squiddyImage)
        );
        userInput.clear();
        if (squiddy.getIsExit()) {
            Platform.exit();
        }
    }
}
