package bobby.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import bobby.main.Bobby;
import bobby.ui.Ui;
import javafx.util.Duration;

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

    private Bobby bobby;

    private final Image userImage;
    private final Image bobbyImage;

    public MainWindow() {
        // Load images with null checks to avoid NullPointerException
        var userStream = this.getClass().getResourceAsStream("/images/131DC1C4-04BE-41DB-B15D-5D55A1F44DA3_4_5005_c.jpeg");
        if (userStream == null) {
            throw new IllegalStateException("User image resource not found");
        }
        userImage = new Image(userStream);
        var bobbyStream = this.getClass().getResourceAsStream("/images/D72925AD-1ED8-4147-8382-714C9825A3CD.jpeg");
        if (bobbyStream == null) {
            throw new IllegalStateException("Duke image resource not found");
        }
        bobbyImage = new Image(bobbyStream);
    }

    /**
     * Initializes the main window. Binds the scroll pane to the dialog container height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bobby instance and displays the welcome message, today's schedule, and todo list.
     *
     * @param d The Bobby instance to use for responses.
     */
    public void setBobby(Bobby d) {
        bobby = d;
        // Show welcome message in GUI using the constant from Ui
        dialogContainer.getChildren().add(
            DialogBox.getBobbyDialog(Ui.WELCOME_MESSAGE, bobbyImage)
        );
        // Show today's schedule
        dialogContainer.getChildren().add(
            DialogBox.getBobbyDialog(bobby.getTodaySchedule(), bobbyImage)
        );
        // Show todo list
        dialogContainer.getChildren().add(
            DialogBox.getBobbyDialog(bobby.getTodoList(), bobbyImage)
        );
    }

    /**
     * Handles user input: displays user and bot dialogs, processes commands, and handles exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobby.getResponse(input);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        // If response indicates an error, use error dialog
        if (response != null && (response.startsWith("Error:") || response.startsWith("Whatdatmean") || response.toLowerCase().contains("error"))) {
            dialogContainer.getChildren().add(
                DialogBox.getErrorDialog(response, bobbyImage)
            );
        } else {
            dialogContainer.getChildren().add(
                DialogBox.getBobbyDialog(response, bobbyImage)
            );
        }
        userInput.clear();
        // Delay and show goodbye message if the user types 'bye'
        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                javafx.stage.Stage stage = (javafx.stage.Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}