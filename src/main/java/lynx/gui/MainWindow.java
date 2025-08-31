package lynx.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
import lynx.TaskLynxGui;
import lynx.exception.LynxException;

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

    private TaskLynxGui taskLynx;
    private boolean isExiting = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image lynxImage = new Image(this.getClass().getResourceAsStream("/images/Lynx.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setTaskLynx(TaskLynxGui taskLynx) {
        this.taskLynx = taskLynx;
        try {
            taskLynx.load();
        } catch (LynxException e) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(e.getMessage(), lynxImage));
        }
        greet();
    }

    public void greet() {
        for (String greeting : taskLynx.getGreetings()) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, lynxImage));
        }
    }

    public void farewell() {
        for (String farewell : taskLynx.getFarewells()) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(farewell, lynxImage));
        }
        try {
            taskLynx.save();
        } catch (LynxException e) {
            String warning = String.format("%s%nUse \"save\" to resave or \"bye!\" to force quit.", e.getMessage());
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(warning, lynxImage));
            return;
        }
        isExiting = true;
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText.isEmpty() || isExiting) {
            return;
        }
        if (userText.trim().equals("bye!")) {
            Platform.exit();
        }
        if (userText.trim().equals("bye")) {
            farewell();
            userInput.clear();
            return;
        }
        String lynxText = taskLynx.getCommandResponse(userInput.getText());
        if (lynxText.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(lynxText, lynxImage)
        );
        userInput.clear();
    }
}