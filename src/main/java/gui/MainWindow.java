package gui;

import frenny.Frenny;
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
import ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final int EXIT_DELAY_SECONDS = 1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Frenny frenny;

    // Image loading from classpath to ensure the images are portable with the JAR file
    private final Image frennyImage = new Image(this.getClass().getResourceAsStream("/images/frenny.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Frenny instance */
    public void setFrenny(Frenny frenny) {
        this.frenny = frenny;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Frenny's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        // Trim input
        input = input.trim();
        boolean isInputEmpty = input.isEmpty();
        boolean isEditCommand = !isInputEmpty
                && input.split(" ")[0].equalsIgnoreCase("edit");
        String response = frenny.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        if (input.trim().equalsIgnoreCase("bye")) {
            handleExit(response);
        } else if (isEditCommand) {
            System.out.println(response);
            handleEditTask(response);
        } else {
            dialogContainer.getChildren().addAll(
                DialogBox.getFrennyDialog(response, frennyImage)
            );
            userInput.clear();
        }
    }

    /**
     * Handles and displays the welcome message from Frenny when the application starts.
     */
    public void handleWelcomeMessage() {
        String intro = Ui.showIntro();
        String showCurrentList = frenny.getResponse("list");
        String response = intro + "\n" + showCurrentList;
        dialogContainer.getChildren().addAll(
                DialogBox.getFrennyDialog(response, frennyImage)
        );
    }

    private void handleEditTask(String taskDetails) {
        String response = """
            Ok edit that task in the text field.
            If it doesn't exist, you will get "Invalid task number".
            After editing, press Send to confirm.
            The old task will be deleted
            and the new task will be added to the end of the list.""";
        dialogContainer.getChildren().add(
                DialogBox.getFrennyDialog(response, frennyImage)
        );
        userInput.setText(taskDetails);
    }

    private void handleExit(String response) {
        dialogContainer.getChildren().add(
                DialogBox.getFrennyDialog(response, frennyImage)
        );
        // Add delay before exit
        PauseTransition delay = new PauseTransition(Duration.seconds(EXIT_DELAY_SECONDS));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
