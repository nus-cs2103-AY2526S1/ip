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
import objectclasses.exception.LynxException;

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

    // Boolean to indicate application is about to close and TaskLynx should no longer respond to the user.
    private boolean isExiting = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image lynxImage = new Image(this.getClass().getResourceAsStream("/images/Lynx.png"));
    private Image errorImage = new Image(this.getClass().getResourceAsStream("/images/LynxError.png"));
    private Image unknownImage = new Image(this.getClass().getResourceAsStream("/images/NotLynx.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the TaskLynx instance and loads its contents from the data file.
     */
    public void setTaskLynx(TaskLynxGui taskLynx) {
        this.taskLynx = taskLynx;
        try {
            taskLynx.load();
        } catch (LynxException e) {
            dialogContainer.getChildren().addAll(DialogBox.getLynxDialog(e.getMessage(), lynxImage));
        }
        greet();
    }

    /**
     * Greets the user.
     */
    public void greet() {
        for (String greeting : taskLynx.getGreetings()) {
            dialogContainer.getChildren().addAll(DialogBox.getLynxDialog(greeting, lynxImage));
        }
    }

    /**
     * Attempts to save the contents of the task list and close the application.
     * </p>
     * Aborts if save fails.
     */
    public void farewell() {
        for (String farewell : taskLynx.getFarewells()) {
            dialogContainer.getChildren().addAll(DialogBox.getLynxDialog(farewell, lynxImage));
        }
        try {
            taskLynx.save();
        } catch (LynxException e) {
            String warning = String.format("%s%nUse \"bye!\" to put me out of this misery.", e.getMessage());
            dialogContainer.getChildren().addAll(DialogBox.getLynxDialog(warning, lynxImage));
            return;
        }
        cueExit();
    }

    /**
     * Schedules the program to stop reading user inputs and shut down in 5 seconds.
     */
    private void cueExit() {
        isExiting = true;
        dialogContainer.getChildren().add(
                DialogBox.getLynxDialog("* Slowly fades away * (in 5 seconds)", lynxImage));
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lynx's reply and then appends them to
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
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, userImage));
            farewell();
            userInput.clear();
            return;
        }

        if (userText.trim().equals("clear")) {
            dialogContainer.getChildren().clear();
            userInput.clear();
            return;
        }

        assert(!isExiting);

        DialogBox userDialogue = DialogBox.getUserDialog(userText, userImage);
        DialogBox lynxDialogue;

        try {
            String response = taskLynx.getCommandResponse(userInput.getText());
            lynxDialogue = DialogBox.getLynxDialog(response, lynxImage);
        } catch (LynxException e) {
            String response = e.getMessage();
            if (e.isSecret()) {
                lynxDialogue = DialogBox.getErrorDialog(response, unknownImage);
                lynxDialogue.setName("Lynx???");
            } else {
                lynxDialogue = DialogBox.getErrorDialog(response, errorImage);
            }
        }

        if (lynxDialogue.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().addAll(
                userDialogue,
                lynxDialogue
        );
        userInput.clear();
    }
}
