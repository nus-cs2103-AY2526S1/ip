import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jerry.Jerry;

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

    private Jerry jerry;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/tom.png"));
    private final Image jerryImage = new Image(this.getClass().getResourceAsStream("/images/jerry.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Jerry instance */
    public void setJerry(Jerry j) {
        this.jerry = j;
        dialogContainer.getChildren().add(
            DialogBox.getJerryDialog(jerry.getWelcomeMessage(), jerryImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jerry.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJerryDialog(response, jerryImage)
        );
        userInput.clear();

        //AI-assisted: to add a pause transition before closing app 
        //instead of directly closing the app after the user enters "bye".
        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(800.00));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
