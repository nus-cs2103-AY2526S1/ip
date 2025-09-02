package leo.display;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import leo.Leo;

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

    private Leo leo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image leoImage = new Image(this.getClass().getResourceAsStream("/images/Leo.png"));

    /**
     * Binds the vertical scroll value property of a scrollPane to the height property of a dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Leo instance */
    public void setLeo(Leo l) {
        this.leo = l;
        leoGreetings(Messages.greetingsReturnOutput());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Leo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = leo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLeoDialog(response, leoImage)
        );
        if (input.equalsIgnoreCase("bye")) {
            //Solution adapted from
            // https://www.perplexity.ai/search/fxml-what-is-the-use-o-fthis-YMqogRnSR32xgEani0m3_w#4
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit()); // Close app after pause
            pause.play();
        }
        userInput.clear();
    }

    /**
     * Creates a dialog box that greets the user when the application is started.
     *
     * @param greeting The initial message to prompt the user for input.
     */
    @FXML
    public void leoGreetings(String greeting) {
        dialogContainer.getChildren().addAll(
                DialogBox.getLeoDialog(greeting, leoImage)
        );
    }
}

